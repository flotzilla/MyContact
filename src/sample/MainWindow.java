package sample;

import entity.Contacts;
import entity.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Controller;
import utils.ModalDialog;
import utils.OkCancelDialog;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Controller controller;
    @FXML
    TableView<Person> table;
    @FXML
    TableColumn fnameCol;
    @FXML
    TableColumn fullnameCol;
    @FXML
    TableColumn nicknameCol;
    @FXML
    TableColumn phoneNumberCol;
    @FXML
    TableColumn emailCol;


    public MainWindow() {
        this.controller = Controller.getInstance();
    }

    public void openFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Contact files (*.cnt)", "*.cnt");
        fc.getExtensionFilters().add(extFilter);

        File file = fc.showOpenDialog(null);
        if (file == null) {
            new ModalDialog(controller.getMainStage(), "Choose your cnt file");
            return;
        }

        List<Person> personList = null;
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            personList = (List<Person>) ois.readObject();
            controller.setContactsFileOpened(true);
            controller.getContacts().setPersonList(personList);
            table.setItems(controller.getContacts().getObsPersonList());
        } catch (FileNotFoundException e) {
            new ModalDialog(controller.getMainStage(), "Cannot find file");
        } catch (IOException e) {
            new ModalDialog(controller.getMainStage(), "IOException");
        } catch (ClassNotFoundException e) {
            new ModalDialog(controller.getMainStage(), "ClassNotFindException");
        } finally {
            if (personList != null) {
                controller.getContacts().setMainFilePath(file.getAbsolutePath());
            } else {
                new ModalDialog(controller.getMainStage(), "Cannot read file");
            }
        }
    }

    public void saveFile(ActionEvent actionEvent) {

        if (controller.isContactsFileOpened()) {
            try (FileOutputStream fos = new FileOutputStream(controller.getContacts().getMainFilePath());
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(controller.getContacts().getPersonList());
                oos.flush();
                oos.close();
                controller.setContactsFileOpened(true);
            } catch (FileNotFoundException e) {
                new ModalDialog(controller.getMainStage(), "Cannot save to " +
                        controller.getContacts().getMainFilePath());
            } catch (IOException e) {
                e.printStackTrace();
                new ModalDialog(controller.getMainStage(), "IO Error");
            }
        } else {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Contact files (*.cnt)", "*.cnt");
            fc.getExtensionFilters().add(extFilter);
            File file = new File (fc.showSaveDialog(controller.getMainStage()) + ".cnt");
            if (file != null) {
                controller.setContactsFileOpened(true);
                controller.getContacts().setMainFilePath(file.getAbsolutePath());
                saveFile(null);
            }
        }


    }

    public void quitAction(ActionEvent actionEvent) {
        closeNSaveAction(null);
        controller.getMainStage().close();
    }

    public void closeNSaveAction(ActionEvent actionEvent) {

        if (controller.isContactsFileOpened() ||
                controller.getContacts().getObsPersonList().size()!= 0) {

            EventHandler<ActionEvent> okHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    saveFile(null);
                    controller.getContacts().getObsPersonList().clear();
                    controller.setContactsFileOpened(false);
                    controller.getContacts().setMainFilePath("");
                    Node source = (Node) actionEvent.getSource();
                    Stage st = (Stage) source.getScene().getWindow();
                    st.close();
                }
            };

            EventHandler<ActionEvent> cancelhandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Node source = (Node) actionEvent.getSource();
                    controller.getContacts().getObsPersonList().clear();
                    controller.setContactsFileOpened(false);
                    controller.getContacts().setMainFilePath("");
                    Stage st = (Stage) source.getScene().getWindow();
                    st.close();
                }
            };

            OkCancelDialog okCancelDialog = new OkCancelDialog("Save before closing?");
            okCancelDialog.setConfirmHandler(okHandler);
            okCancelDialog.setCancelHandler(cancelhandler);
            okCancelDialog.show();


        }


    }

    public void addPerson(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage addStage = new Stage();
            addStage.setTitle("Add new person");
            addStage.initStyle(StageStyle.UTILITY);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.setScene(new Scene(parent));
            addStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void deletePerson(ActionEvent actionEvent) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            controller.getContacts().getObsPersonList().remove(selectedIndex);
        }
    }

    public TableView getTable() {
        return table;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(controller.getContacts().getObsPersonList());
        fnameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName")
        );
        fnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                        t.getTableView().getItems()
                                .get(t.getTablePosition().getRow())
                                .setFirstName(t.getNewValue());
                    }
                }
        );
        fullnameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("fullName")
        );
        fullnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fullnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                        t.getTableView().getItems()
                                .get(t.getTablePosition().getRow())
                                .setFullName(t.getNewValue());
                    }
                }
        );
        nicknameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("nickName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email")
        );
        phoneNumberCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("phoneNumber")
        );

    }
}
