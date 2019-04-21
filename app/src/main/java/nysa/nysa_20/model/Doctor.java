package nysa.nysa_20.model;

public class Doctor {
    private String nume;
    private String telefon;
    private String email;
    private String id;

    public Doctor(String nume, String telefon, String email, String id) {
        this.nume = nume;
        this.telefon = telefon;
        this.email = email;
        this.id = id;
    }

    public Doctor() {
    }

    public Doctor(String nume, String telefon, String email) {
        this.nume = nume;
        this.telefon = telefon;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
