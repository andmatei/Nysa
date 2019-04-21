package nysa.nysa_20.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Account implements Serializable {

    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private String id;
    private HashMap<String,Boolean> allergyMap;
    private HashMap<java.time.LocalDate,SymptomEntry> historyMap;
    private Doctor doctor;


    public Account(RegistrationFormular formular){
        this.lastName = formular.getLastName();
        this.firstName = formular.getFirstName();
        this.username = formular.getUsername();
        this.email = formular.getEmail();
        historyMap = new HashMap<>();


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

    public HashMap<LocalDate, SymptomEntry> getHistoryMap() {
        return historyMap;
    }

    public HashMap<String, Boolean> getAllergyMap() {
        return allergyMap;
    }

    public void setAllergyMap(HashMap<String, Boolean> allergyMap) {
        this.allergyMap = allergyMap;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Account{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", allergyMap=" + allergyMap +
                ", historyMap=" + historyMap +
                '}';
    }
}

