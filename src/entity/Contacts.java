package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 19.07.13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Contacts {

    private String mainFilePath;
    private List<Person> personList;
    private ObservableList<Person> observablePersonList;

    public Contacts() {
        personList = new ArrayList<>();
        observablePersonList = FXCollections.observableList(personList);
    }

    public String getMainFilePath() {
        return mainFilePath;
    }

    public void setMainFilePath(String mainFilePath) {
        this.mainFilePath = mainFilePath;
    }

    public ObservableList<Person> getObsPersonList() {
        return observablePersonList;
    }

    public List<Person> getPersonList(){
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        observablePersonList.clear();
        observablePersonList = FXCollections.observableList(personList);
    }


}
