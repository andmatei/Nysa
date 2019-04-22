package nysa.nysa_20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorAccount implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String id;

    private List<Account> patients;

    public DoctorAccount(RegistrationFormular formular) {
        firstName = formular.getFirstName();
        lastName = formular.getLastName();
        phone = formular.getPhone();
        email = formular.getEmail();
        patients = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getPatients() {
        return patients;
    }

    public void setPatients(List<Account> patients) {
        this.patients = patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DoctorAccount{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", patients=" + patients +
                '}';
    }

}
