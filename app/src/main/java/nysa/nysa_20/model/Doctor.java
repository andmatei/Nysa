package nysa.nysa_20.model;

public class Doctor {
    private String nume;
    private String phone;
    private String email;
    private String id;

    public Doctor(String nume, String phone, String email, String id) {
        this.nume = nume;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

    public Doctor() {
    }

    public Doctor(String nume, String phone, String email) {
        this.nume = nume;
        this.phone = phone;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
