package utils;

import entity.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 02.08.13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class AddFormDialog implements Initializable{

    private Controller controller;

    private Person person;
    @FXML private Label fNamelabel;
    @FXML private TextField fNameTextField;
    @FXML private Label fullNamelabel;
    @FXML private TextField fullNameTextField;
    @FXML private Label nikNamelabel;
    @FXML private TextField nickNameTextField;
    @FXML private Label phoneNumberNamelabel;
    @FXML private TextField phoneNumberTextField;
    @FXML private Label emailNamelabel;
    @FXML private TextField emailNameTextField;

    @FXML private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller = Controller.getInstance();
    }


    public void addNewPerson(ActionEvent actionEvent) {
        Person person = new Person(fNameTextField.getText(), fullNameTextField.getText(), nickNameTextField.getText(),
                phoneNumberTextField.getText(), emailNameTextField.getText());
        controller.getContacts().getObsPersonList().add(person);

        ObservableList<Person> personList = controller.getContacts().getObsPersonList();
        for (int i=0; i< personList.size(); i++){
            System.out.println(personList.get(i));
        }

        Stage stage= (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
