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
import nysa.nysa_20.activity.SymptomEntryActivity;
import nysa.nysa_20.model.Symptom;
import nysa.nysa_20.service.utilitary.TimeUtilitaryClass;
import nysa.nysa_20.service.utilitary.SymptomEntryService;

public class FragmentEyeSymptoms extends Fragment {
    private static View view;

    private static TextView q1_redEyesTextView;
    private static TextView q1_itchinessTextView;
    private static TextView q1_tearingTextView;
    private static TextView q1_swollenTextView;
    private static TextView q1_sorenessTextView;
    private static TextView q1_sensitivityTextView;
    private static TextView q1_conjunctivitisTextView;
    private static EditText q1_editText;
    private static SeekBar q1_seekBar;
    private static TextView eyesight_date_TextView;
    private static List<String> q1_eyesightSymptoms;
    private static HashMap<Integer,String> idSymptoms;
    private static List<Symptom> eyesightSymptoms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.eye_symptom_entry, container, false);



            initComponents();


        if(eyesightSymptoms!=null){
            eyesightSymptoms.forEach(s -> retrieveIndividualData(s));
        }


        return view;


    }

    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();
        prepareIdMap();

        q1_eyesightSymptoms = new ArrayList<>();
    }



    private void assignComponentReferences() {
        eyesight_date_TextView = view.findViewById(R.id.eyesight_date_TextView);
        assignQ1ComponentReferences();
    }

    private void assignComponentFunctionalities() {
        eyesight_date_TextView.setText(TimeUtilitaryClass.getCurrentTimeFormatSymptomEntryActivity());
        assignQ1ComponentFunctionalities();
    }

    private void assignQ1ComponentFunctionalities() {
        q1_itchinessTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_itchinessSymptom_eyesightTextView));
        q1_redEyesTextView.setOnClickListener(rv -> q1_textViewOnClick(R.id.q1_redEyesSymptom_eyesightTextView));
        q1_sensitivityTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_sensitivitytolightSymptom_eyesightTextView));
        q1_swollenTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_swollenEyelidsSymptom_eyesightTextView));
        q1_sorenessTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_sorenessSymptom_eyesightTextView));
        q1_tearingTextView.setOnClickListener(ev-> q1_textViewOnClick(R.id.q1_tearingEyesSymptom_eyesightTextView));
        q1_conjunctivitisTextView.setOnClickListener(ev -> q1_textViewOnClick(R.id.q1_conjunctivitisSymptom_eyesightTextView));
    }
    private void prepareIdMap() {
        idSymptoms = new HashMap<>();
        idSymptoms.put(R.id.q1_itchinessSymptom_eyesightTextView,"Itchiness");
        idSymptoms.put(R.id.q1_redEyesSymptom_eyesightTextView,"Red eyes");
        idSymptoms.put(R.id.q1_sensitivitytolightSymptom_eyesightTextView,"Sensitivity to light");
        idSymptoms.put(R.id.q1_swollenEyelidsSymptom_eyesightTextView,"Soreness or burning");
        idSymptoms.put(R.id.q1_sorenessSymptom_eyesightTextView,"Swollen eyelids");
        idSymptoms.put(R.id.q1_tearingEyesSymptom_eyesightTextView,"Tearing eyes");
        idSymptoms.put(R.id.q1_conjunctivitisSymptom_eyesightTextView,"Conjunctivitis");

    }
    private void q1_textViewOnClick(Integer id) {


        TextView textView = view.findViewById(id);
        String symptom = idSymptoms.get(id);

        if(q1_eyesightSymptoms.contains(symptom)){
            q1_eyesightSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked,null));
            textView.setTextColor(getResources().getColor(R.color.greenText,null));
        }
        else{
            q1_eyesightSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked,null));
            textView.setTextColor(getResources().getColor(R.color.whiteText,null));
        }
    }


    private void assignQ1ComponentReferences() {
        q1_editText = view.findViewById(R.id.q1_eyesightEditText);
        q1_itchinessTextView = view.findViewById(R.id.q1_itchinessSymptom_eyesightTextView);
        q1_redEyesTextView = view.findViewById(R.id.q1_redEyesSymptom_eyesightTextView);
        q1_seekBar = view.findViewById(R.id.q1_eyesightSeekbar);
        q1_sensitivityTextView = view.findViewById(R.id.q1_sensitivitytolightSymptom_eyesightTextView);
        q1_sorenessTextView = view.findViewById(R.id.q1_sorenessSymptom_eyesightTextView);
        q1_swollenTextView = view.findViewById(R.id.q1_swollenEyelidsSymptom_eyesightTextView);
        q1_tearingTextView = view.findViewById(R.id.q1_tearingEyesSymptom_eyesightTextView);
        q1_conjunctivitisTextView = view.findViewById(R.id.q1_conjunctivitisSymptom_eyesightTextView);

    }

    public static List<Symptom> getSymptoms(){
        List<Symptom> sightSymptoms = new ArrayList<>();

        sightSymptoms.add(getQ1Symptom());

        return sightSymptoms;
    }

    private static Symptom getQ1Symptom() {
        return new Symptom(q1_eyesightSymptoms,q1_seekBar.getProgress(), q1_editText.getEditableText().toString().trim(),"q1_eyesightSymptom");
    }


    public static void retrievedData( List<Symptom> symptoms){
       eyesightSymptoms =symptoms;


    }

    private static void retrieveIndividualData(@NotNull Symptom symptom) {
        String symptomName = symptom.getSymptomName();
        switch(symptomName){
            case "q1_eyesightSymptom":
                q1_editText.setText(symptom.getSymptomDeclaration());
                q1_seekBar.setProgress(symptom.getSymptomRating());
                q1_eyesightSymptoms = symptom.getSymptoms();
                prepareAllButtons(q1_eyesightSymptoms);
                return;
        }

    }

    private static void prepareAllButtons(List<String> eyesightSymptoms) {
        eyesightSymptoms.forEach(s -> prepareButton(s));
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
