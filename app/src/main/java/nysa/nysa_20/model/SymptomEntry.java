package nysa.nysa_20.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nysa.nysa_20.R;

public class SymptomEntry implements Serializable {
    private List<Symptom> symptomsSightEntry;
    private List<Symptom> symptomsPainEntry;
    private List<Symptom> symptomsRespirationEntry;
    private List<Symptom> symptomsSkinEntry;

    public SymptomEntry() {
    }

    public List<Symptom> getSymptomsSightEntry() {
        return symptomsSightEntry;
    }

    public void setSymptomsSightEntry(List<Symptom> symptomsSightEntry) {
        this.symptomsSightEntry = symptomsSightEntry;
    }

    public List<Symptom> getSymptomsPainEntry() {
        return symptomsPainEntry;
    }

    public void setSymptomsPainEntry(List<Symptom> symptomsPainEntry) {
        this.symptomsPainEntry = symptomsPainEntry;
    }

    public List<Symptom> getSymptomsRespirationEntry() {
        return symptomsRespirationEntry;
    }

    public void setSymptomsRespirationEntry(List<Symptom> symptomsRespirationEntry) {
        this.symptomsRespirationEntry = symptomsRespirationEntry;
    }

    public List<Symptom> getSymptomsSkinEntry() {
        return symptomsSkinEntry;
    }

    public void setSymptomsSkinEntry(List<Symptom> symptomsSkinEntry) {
        this.symptomsSkinEntry = symptomsSkinEntry;
    }

    @Override
    public String toString() {
        return "SymptomEntry{" +
                "symptomsSightEntry=" + symptomsSightEntry +
                ", symptomsPainEntry=" + symptomsPainEntry +
                ", symptomsRespirationEntry=" + symptomsRespirationEntry +
                ", symptomsSkinEntry=" + symptomsSkinEntry +
                '}';
    }
}
