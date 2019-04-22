package nysa.nysa_20.model;

import java.io.Serializable;
import java.util.List;

public class Symptom implements Serializable {
    private List<String> symptoms;
    private int symptomRating;
    private String symptomDeclaration;
    private String symptomName;

    public Symptom() {
    }



    public Symptom(List<String> symptoms, int symptomRating, String symptomDeclaration,String symptomName) {
        this.symptoms = symptoms;
        this.symptomRating = symptomRating;
        this.symptomDeclaration = symptomDeclaration;
        this.symptomName = symptomName;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public int getSymptomRating() {
        return symptomRating;
    }

    public void setSymptomRating(int symptomRating) {
        this.symptomRating = symptomRating;
    }

    public String getSymptomDeclaration() {
        return symptomDeclaration;
    }

    public void setSymptomDeclaration(String symptomDeclaration) {
        this.symptomDeclaration = symptomDeclaration;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "symptoms=" + symptoms +
                ", symptomRating=" + symptomRating +
                ", symptomDeclaration=" + symptomDeclaration +
                '}';
    }


}
