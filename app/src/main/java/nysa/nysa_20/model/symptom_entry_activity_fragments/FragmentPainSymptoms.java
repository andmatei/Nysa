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

public class FragmentPainSymptoms extends Fragment {
    private static View view;

    private static TextView q1_migraineTextView;
    private static TextView q1_tensionTextView;
    private static TextView q1_chestPainTextView;
    private static TextView q1_sharpHeadacheTextView;
    private static TextView q1_abdominalPainTextView;
    private static TextView q1_clusterHeadacheTextView;
    private static TextView q1_nauseaTextView;
    private static EditText q1_editText;
    private static SeekBar q1_seekBar;
    private static TextView pain_date_TextView;
    private static List<String> q1_painSymptoms;
    private static HashMap<Integer,String> idSymptoms;
    private static List<Symptom> painSymptoms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pain_symptom_entry, container, false);



        initComponents();


        if(painSymptoms!=null){
            painSymptoms.forEach(s -> retrieveIndividualData(s));
        }


        return view;


    }

    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();
        prepareIdMap();

        q1_painSymptoms = new ArrayList<>();
    }



    private void assignComponentReferences() {
        pain_date_TextView = view.findViewById(R.id.pain_date_TextView);
        assignQ1ComponentReferences();
    }

    private void assignComponentFunctionalities() {
        pain_date_TextView.setText(TimeUtilitaryClass.getCurrentTimeFormatSymptomEntryActivity());
        assignQ1ComponentFunctionalities();
    }

    private void assignQ1ComponentFunctionalities() {
        q1_abdominalPainTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_abdominalPainSymptom_painTextView));
        q1_chestPainTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_chestPainSymptom_painTextView));
        q1_clusterHeadacheTextView.setOnClickListener(ev-> q1_textViewOnClick(R.id.q1_clusterHeadacheSymptom_painTextView));
        q1_migraineTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_migraineSymptom_painTextView));
        q1_nauseaTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_nauseaSymptom_painTextView));
        q1_sharpHeadacheTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_sharpHeadacheSymptom_painTextView));
        q1_tensionTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_tensionSymptom_painTextView));

    }
    private void prepareIdMap() {
        idSymptoms = new HashMap<>();
        idSymptoms.put(R.id.q1_migraineSymptom_painTextView,"Migraine");
        idSymptoms.put(R.id.q1_tensionSymptom_painTextView,"Tension");
        idSymptoms.put(R.id.q1_chestPainSymptom_painTextView,"Chest pain");
        idSymptoms.put(R.id.q1_sharpHeadacheSymptom_painTextView,"Sharp headache");
        idSymptoms.put(R.id.q1_abdominalPainSymptom_painTextView,"Abdominal pain");
        idSymptoms.put(R.id.q1_clusterHeadacheSymptom_painTextView,"Cluster headache");
        idSymptoms.put(R.id.q1_nauseaSymptom_painTextView,"Nause");


    }
    private void q1_textViewOnClick(Integer id) {
        TextView textView = view.findViewById(id);
        String symptom = idSymptoms.get(id);

        if(q1_painSymptoms.contains(symptom)){
            q1_painSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked,null));
            textView.setTextColor(getResources().getColor(R.color.greenText,null));
        }
        else{
            q1_painSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked,null));
            textView.setTextColor(getResources().getColor(R.color.whiteText,null));
        }
    }


    private void assignQ1ComponentReferences() {
        q1_editText = view.findViewById(R.id.q1_painEditText);
        q1_seekBar = view.findViewById(R.id.q1_painSeekbar);
        q1_migraineTextView = view.findViewById(R.id.q1_migraineSymptom_painTextView);
        q1_tensionTextView = view.findViewById(R.id.q1_tensionSymptom_painTextView);
        q1_chestPainTextView = view.findViewById(R.id.q1_chestPainSymptom_painTextView);
        q1_abdominalPainTextView = view.findViewById(R.id.q1_abdominalPainSymptom_painTextView);
        q1_clusterHeadacheTextView = view.findViewById(R.id.q1_clusterHeadacheSymptom_painTextView);
        q1_nauseaTextView = view.findViewById(R.id.q1_nauseaSymptom_painTextView);
        q1_sharpHeadacheTextView = view.findViewById(R.id.q1_sharpHeadacheSymptom_painTextView);

    }

    public static List<Symptom> getSymptoms(){
        List<Symptom> painSymptoms = new ArrayList<>();

        painSymptoms.add(getQ1Symptom());

        return painSymptoms;
    }

    private static Symptom getQ1Symptom() {
        return new Symptom(q1_painSymptoms,q1_seekBar.getProgress(), q1_editText.getEditableText().toString().trim(),"q1_painSymptom");
    }


    public static void retrievedData( List<Symptom> symptoms){
        painSymptoms =symptoms;


    }

    private static void retrieveIndividualData(@NotNull Symptom symptom) {
        String symptomName = symptom.getSymptomName();
        switch(symptomName){
            case "q1_painSymptom":
                q1_editText.setText(symptom.getSymptomDeclaration());
                q1_seekBar.setProgress(symptom.getSymptomRating());
                q1_painSymptoms = symptom.getSymptoms();
                prepareAllButtons(q1_painSymptoms);
                return;
        }

    }

    private static void prepareAllButtons(List<String> painSymptoms) {
        painSymptoms.forEach(s -> prepareButton(s));
    }

    private static void prepareButton(String s) {
        int id = SymptomEntryService.getKeyByValue(s,idSymptoms);
        if(id==0){
            System.out.println("Nu exista valoarea data");
        }
        else
        {
            TextView textView = view.findViewById(id);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked,null));
            textView.setTextColor(view.getResources().getColor(R.color.whiteText,null));
        }
    }

}