package entity;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Date: 19.07.13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Serializable{
    private String firstName;
    private String  fullName;
    private String  nickName;
    private String phoneNumber;
    private String  email;

    public Person(String firstName, String fullName, String nickName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getFirstName() + " " +
                getFullName() + " " +
                getNickName()+ " " +
                getPhoneNumber()+ " " +
                getEmail();
    }
}
