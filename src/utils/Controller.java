package utils;

import entity.Contacts;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 19.07.13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private static Controller controller = null;
    private Contacts contacts;
    private Stage mainStage;
    private boolean isContactsFileOpened = false;

    private Controller(){
        this.contacts= new Contacts();
    }

    public static Controller getInstance(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public void addMainStage(Stage primaryStage) {
        mainStage = primaryStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public boolean isContactsFileOpened() {
        return isContactsFileOpened;
    }

    public void setContactsFileOpened(boolean contactsFileOpened) {
        isContactsFileOpened = contactsFileOpened;
    }
}
