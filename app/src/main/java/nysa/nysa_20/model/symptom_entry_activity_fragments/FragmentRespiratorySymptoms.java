package nysa.nysa_20.model.symptom_entry_activity_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Symptom;
import nysa.nysa_20.service.utilitary.SymptomEntryService;
import nysa.nysa_20.service.utilitary.TimeUtilitaryClass;

public class FragmentRespiratorySymptoms extends Fragment {
    private static View view;

    private static TextView q1_coughingTextView;
    private static TextView q1_itchiThroatTextView;
    private static TextView q1_wheezingTextView;
    private static TextView q1_chestTightnessTextView;
    private static TextView q1_sneezingTextView;
    private static TextView q1_shortnessOfBreathTextView;
    private static EditText q1_editText;
    private static SeekBar q1_seekBar;
    private static List<String> q1_respirationSymptoms;

    private static TextView q2_stuffyNoseTextView;
    private static TextView q2_runnyNoseTextView;
    private static TextView q2_itchiNoseTextView;
    private static TextView q2_nasalCongestionTextView;
    private static TextView q2_postnasalDripTextView;
    private static EditText q2_editText;
    private static SeekBar q2_seekBar;
    private static List<String> q2_respirationSymptoms;

    private static TextView respiration_date_TextView;

    private static HashMap<Integer, String> idSymptoms;
    private static List<Symptom> respirationSymptoms;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.respiration_symptom_entry, container, false);


        initComponents();


        if (respirationSymptoms != null) {
            respirationSymptoms.forEach(s -> retrieveIndividualData(s));
        }


        return view;


    }

    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();
        prepareIdMap();

        q1_respirationSymptoms = new ArrayList<>();
        q2_respirationSymptoms = new ArrayList<>();
    }


    private void assignComponentReferences() {
        respiration_date_TextView = view.findViewById(R.id.respiration_date_TextView);
        assignQ1ComponentReferences();
        assignQ2ComponentReferences();
    }

    private void assignComponentFunctionalities() {
        respiration_date_TextView.setText(TimeUtilitaryClass.getCurrentTimeFormatSymptomEntryActivity());
        assignQ1ComponentFunctionalities();
        assignQ2ComponentFunctionalities();
    }

    private void assignQ1ComponentFunctionalities() {
        q1_chestTightnessTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_chestTightnessSymptom_respirationTextView));
        q1_coughingTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_coughingSymptom_respirationTextView));
        q1_itchiThroatTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_itchiThroatSymptom_respirationTextView));
        q1_shortnessOfBreathTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_shortnessOfBreathSymptom_respirationTextView));
        q1_sneezingTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_sneezingSymptom_respirationTextView));
        q1_wheezingTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_wheezingSymptom_respirationTextView));
    }

    private void assignQ2ComponentFunctionalities() {
        q2_itchiNoseTextView.setOnClickListener(ev -> q2_textViewOnClick(R.id.q2_itchiNoseSymptom_respirationTextView));
        q2_nasalCongestionTextView.setOnClickListener(ev -> q2_textViewOnClick(R.id.q2_nasalCongestionSymptom_respirationTextView));
        q2_postnasalDripTextView.setOnClickListener(ev -> q2_textViewOnClick(R.id.q2_postnasalDripSymptom_respirationTextView));
        q2_runnyNoseTextView.setOnClickListener(ev -> q2_textViewOnClick(R.id.q2_runnyNoseSymptom_respirationTextView));
        q2_stuffyNoseTextView.setOnClickListener(ev -> q2_textViewOnClick(R.id.q2_stuffyNoseSymptom_respirationTextView));
    }

    private void prepareIdMap() {
        idSymptoms = new HashMap<>();
        idSymptoms.put(R.id.q1_coughingSymptom_respirationTextView, "Coughing");
        idSymptoms.put(R.id.q1_itchiThroatSymptom_respirationTextView, "Itchi throat / roof of the mouth");
        idSymptoms.put(R.id.q1_wheezingSymptom_respirationTextView, "Wheezing");
        idSymptoms.put(R.id.q1_shortnessOfBreathSymptom_respirationTextView, "Shortness of breath");
        idSymptoms.put(R.id.q1_chestTightnessSymptom_respirationTextView, "Chest tightness");
        idSymptoms.put(R.id.q1_sneezingSymptom_respirationTextView, "Sneezing");
        idSymptoms.put(R.id.q2_stuffyNoseSymptom_respirationTextView, "Stuffy nose");
        idSymptoms.put(R.id.q2_runnyNoseSymptom_respirationTextView, "Runny nose");
        idSymptoms.put(R.id.q2_itchiNoseSymptom_respirationTextView, "Itchy nose");
        idSymptoms.put(R.id.q2_nasalCongestionSymptom_respirationTextView, "Nasal congestion");
        idSymptoms.put(R.id.q2_postnasalDripSymptom_respirationTextView, "Postnasal drip");


    }

    private void q1_textViewOnClick(Integer id) {
        TextView textView = view.findViewById(id);
        String symptom = idSymptoms.get(id);

        if (q1_respirationSymptoms.contains(symptom)) {
            q1_respirationSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked, null));
            textView.setTextColor(getResources().getColor(R.color.greenText, null));
        } else {
            q1_respirationSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked, null));
            textView.setTextColor(getResources().getColor(R.color.whiteText, null));
        }
    }

    private void q2_textViewOnClick(Integer id) {
        TextView textView = view.findViewById(id);
        String symptom = idSymptoms.get(id);

        if (q2_respirationSymptoms.contains(symptom)) {
            q2_respirationSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked, null));
            textView.setTextColor(getResources().getColor(R.color.greenText, null));
        } else {
            q2_respirationSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked, null));
            textView.setTextColor(getResources().getColor(R.color.whiteText, null));
        }
    }


    private void assignQ1ComponentReferences() {
        q1_editText = view.findViewById(R.id.q1_respirationEditText);
        q1_seekBar = view.findViewById(R.id.q1_respirationSeekbar);
        q1_chestTightnessTextView = view.findViewById(R.id.q1_chestTightnessSymptom_respirationTextView);
        q1_coughingTextView = view.findViewById(R.id.q1_coughingSymptom_respirationTextView);
        q1_itchiThroatTextView = view.findViewById(R.id.q1_itchiThroatSymptom_respirationTextView);
        q1_shortnessOfBreathTextView = view.findViewById(R.id.q1_shortnessOfBreathSymptom_respirationTextView);
        q1_sneezingTextView = view.findViewById(R.id.q1_sneezingSymptom_respirationTextView);
        q1_wheezingTextView = view.findViewById(R.id.q1_wheezingSymptom_respirationTextView);

    }

    private void assignQ2ComponentReferences() {
        q2_editText = view.findViewById(R.id.q2_respirationEditText);
        q2_seekBar = view.findViewById(R.id.q2_respirationSeekbar);
        q2_itchiNoseTextView = view.findViewById(R.id.q2_itchiNoseSymptom_respirationTextView);
        q2_nasalCongestionTextView = view.findViewById(R.id.q2_nasalCongestionSymptom_respirationTextView);
        q2_postnasalDripTextView = view.findViewById(R.id.q2_postnasalDripSymptom_respirationTextView);
        q2_runnyNoseTextView = view.findViewById(R.id.q2_runnyNoseSymptom_respirationTextView);
        q2_stuffyNoseTextView = view.findViewById(R.id.q2_stuffyNoseSymptom_respirationTextView);

    }

    public static List<Symptom> getSymptoms() {
        List<Symptom> respiratorySymptoms = new ArrayList<>();

        respiratorySymptoms.add(getQ1Symptom());
        respiratorySymptoms.add(getQ2Symptom());

        return respiratorySymptoms;
    }

    private static Symptom getQ1Symptom() {
        return new Symptom(q1_respirationSymptoms, q1_seekBar.getProgress(), q1_editText.getEditableText().toString().trim(), "q1_respirationSymptom");
    }

    private static Symptom getQ2Symptom() {
        return new Symptom(q2_respirationSymptoms, q2_seekBar.getProgress(), q2_editText.getEditableText().toString().trim(), "q2_respirationSymptom");
    }


    public static void retrievedData(List<Symptom> symptoms) {
        respirationSymptoms = symptoms;


    }

    private static void retrieveIndividualData(@NotNull Symptom symptom) {
        String symptomName = symptom.getSymptomName();
        switch (symptomName) {
            case "q1_respirationSymptom":
                q1_editText.setText(symptom.getSymptomDeclaration());
                q1_seekBar.setProgress(symptom.getSymptomRating());
                q1_respirationSymptoms = symptom.getSymptoms();
                prepareAllButtons(q1_respirationSymptoms);
                return;
            case "q2_respirationSymptom":
                q2_editText.setText(symptom.getSymptomDeclaration());
                q2_seekBar.setProgress(symptom.getSymptomRating());
                q2_respirationSymptoms = symptom.getSymptoms();
                prepareAllButtons(q2_respirationSymptoms);
                return;
        }

    }

    private static void prepareAllButtons(List<String> respirationSymptoms) {
        respirationSymptoms.forEach(s -> prepareButton(s));
    }

    private static void prepareButton(String s) {
        int id = SymptomEntryService.getKeyByValue(s, idSymptoms);
        if (id == 0) {
            System.out.println("Nu exista valoarea data");
        } else {
            TextView textView = view.findViewById(id);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked, null));
            textView.setTextColor(view.getResources().getColor(R.color.whiteText, null));
        }
    }
}