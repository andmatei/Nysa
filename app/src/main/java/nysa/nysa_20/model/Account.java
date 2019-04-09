package nysa.nysa_20.model;

import java.util.HashMap;

public class Account {

    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private String password;
    private String id;
    private HashMap<String,Boolean> allergyMap;



    public Account(RegistrationFormular formular){
        this.lastName = formular.getLastName();
        this.firstName = formular.getFirstName();
        this.username = formular.getUsername();
        this.email = formular.getEmail();
        this.password = formular.getPassword();
    }

    public Account(){

    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, Boolean> getAllergyMap() {
        return allergyMap;
    }

    public void setAllergyMap(HashMap<String, Boolean> allergyMap) {
        this.allergyMap = allergyMap;
    }

    @Override
    public String toString() {
        return "Account{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", allergyMap=" + allergyMap +
                '}';
    }
}

