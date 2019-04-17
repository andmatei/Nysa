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

public class FragmentSkinSymptoms extends Fragment {
    private static View view;

    private static TextView q1_rashSymptom_skinTextView;
    private static TextView q1_eczemaSymptom_skinTextView;
    private static TextView q1_rednessSymptom_skinTextView;
    private static TextView q1_dermatitisSymptom_skinTextView;
    private static TextView q1_swellingSymptom_skinTextView;
    private static TextView q1_hivesSymptom_skinTextView;
    private static TextView q1_itchingSymptom_skinTextView;
    private static EditText q1_editText;
    private static SeekBar q1_seekBar;
    private static TextView skin_date_TextView;
    private static List<String> q1_skinSymptoms;
    private static HashMap<Integer,String> idSymptoms;
    private static List<Symptom> skinSymptoms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.skin_symptom_entry, container, false);
        initComponents();


        if(skinSymptoms!=null){
            skinSymptoms.forEach(s -> retrieveIndividualData(s));
        }


        return view;


    }

    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();
        prepareIdMap();

        q1_skinSymptoms = new ArrayList<>();
    }



    private void assignComponentReferences() {
        skin_date_TextView = view.findViewById(R.id.skin_date_TextView);
        assignQ1ComponentReferences();
    }

    private void assignComponentFunctionalities() {
        skin_date_TextView.setText(TimeUtilitaryClass.getCurrentTimeFormatSymptomEntryActivity());
        assignQ1ComponentFunctionalities();
    }

    private void assignQ1ComponentFunctionalities() {
        q1_dermatitisSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_dermatitisSymptom_skinTextView));
        q1_eczemaSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_eczemaSymptom_skinTextView));
        q1_hivesSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_hivesSymptom_skinTextView));
        q1_itchingSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_itchingSymptom_skinTextView));
        q1_rashSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_rashSymptom_skinTextView));
        q1_rednessSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_rednessSymptom_skinTextView));
        q1_swellingSymptom_skinTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_swellingSymptom_skinTextView));
    }
    private void prepareIdMap() {
        idSymptoms = new HashMap<>();
        idSymptoms.put(R.id.q1_rashSymptom_skinTextView,"Rash");
        idSymptoms.put(R.id.q1_eczemaSymptom_skinTextView,"Eczema");
        idSymptoms.put(R.id.q1_rednessSymptom_skinTextView,"Redness");
        idSymptoms.put(R.id.q1_dermatitisSymptom_skinTextView,"Dermatitis");
        idSymptoms.put(R.id.q1_swellingSymptom_skinTextView,"Swelling");
        idSymptoms.put(R.id.q1_hivesSymptom_skinTextView,"Hives");
        idSymptoms.put(R.id.q1_itchingSymptom_skinTextView,"Itching");


    }
    private void q1_textViewOnClick(Integer id) {
        TextView textView = view.findViewById(id);
        String symptom = idSymptoms.get(id);

        if(q1_skinSymptoms.contains(symptom)){
            q1_skinSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked,null));
            textView.setTextColor(getResources().getColor(R.color.greenText,null));
        }
        else{
            q1_skinSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked,null));
            textView.setTextColor(getResources().getColor(R.color.whiteText,null));
        }
    }


    private void assignQ1ComponentReferences() {
        q1_editText = view.findViewById(R.id.q1_skinEditText);
        q1_seekBar = view.findViewById(R.id.q1_skinSeekbar);
        q1_dermatitisSymptom_skinTextView = view.findViewById(R.id.q1_dermatitisSymptom_skinTextView);
        q1_eczemaSymptom_skinTextView = view.findViewById(R.id.q1_eczemaSymptom_skinTextView);
        q1_hivesSymptom_skinTextView = view.findViewById(R.id.q1_hivesSymptom_skinTextView);
        q1_itchingSymptom_skinTextView = view.findViewById(R.id.q1_itchingSymptom_skinTextView);
        q1_rashSymptom_skinTextView = view.findViewById(R.id.q1_rashSymptom_skinTextView);
        q1_rednessSymptom_skinTextView = view.findViewById(R.id.q1_rednessSymptom_skinTextView);
        q1_swellingSymptom_skinTextView = view.findViewById(R.id.q1_swellingSymptom_skinTextView);

    }

    public static List<Symptom> getSymptoms(){
        List<Symptom> skinSymptoms = new ArrayList<>();

        skinSymptoms.add(getQ1Symptom());

        return skinSymptoms;
    }

    private static Symptom getQ1Symptom() {
        return new Symptom(q1_skinSymptoms,q1_seekBar.getProgress(), q1_editText.getEditableText().toString().trim(),"q1_skinSymptom");
    }


    public static void retrievedData( List<Symptom> symptoms){
        skinSymptoms =symptoms;


    }

    private static void retrieveIndividualData(@NotNull Symptom symptom) {
        String symptomName = symptom.getSymptomName();
        switch(symptomName){
            case "q1_skinSymptom":
                q1_editText.setText(symptom.getSymptomDeclaration());
                q1_seekBar.setProgress(symptom.getSymptomRating());
                q1_skinSymptoms = symptom.getSymptoms();
                prepareAllButtons(q1_skinSymptoms);
                return;
        }

    }

    private static void prepareAllButtons(List<String> skinSymptoms) {
        skinSymptoms.forEach(s -> prepareButton(s));
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