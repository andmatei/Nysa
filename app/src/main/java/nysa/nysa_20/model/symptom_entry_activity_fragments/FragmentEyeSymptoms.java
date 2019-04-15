package nysa.nysa_20.model.symptom_entry_activity_fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.SymptomEntryActivity;
import nysa.nysa_20.model.AccountHolder;

public class FragmentEyeSymptoms extends Fragment {
    View view;

    TextView q1_redEyesTextView;
    TextView q1_itchinessTextView;
    TextView q1_tearingTextView;
    TextView q1_swollenTextView;
    TextView q1_sorenessTextView;
    TextView q1_sensitivityTextView;
    EditText q1_editText;
    SeekBar q1_seekBar;
    public static List<String> eyesightSymptoms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.eye_symptom_entry, container, false);



        initComponents();



        return view;


    }

    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();

        eyesightSymptoms = new ArrayList<>();
    }

    private void assignComponentReferences() {


        assignQ1ComponentReferences();
    }

    private void assignComponentFunctionalities() {
        assignQ1ComponentFunctionalities();
    }

    private void assignQ1ComponentFunctionalities() {
        q1_itchinessTextView.setOnClickListener(ev -> textViewOnClick("Itchiness",R.id.q1_itchinessSymptom_eyesightTextView));
        q1_redEyesTextView.setOnClickListener(rv -> textViewOnClick("Red eyes",R.id.q1_redEyesSymptom_eyesightTextView));
        q1_sensitivityTextView.setOnClickListener(ev -> textViewOnClick("Sensitivity to light",R.id.q1_sensitivitytolightEyelidsSymptom_eyesightTextView));
        q1_sorenessTextView.setOnClickListener(ev -> textViewOnClick("Soreness or burning",R.id.q1_sorenessEyelidsSymptom_eyesightTextView));
        q1_swollenTextView.setOnClickListener(ev -> textViewOnClick("Swollen eyelids",R.id.q1_swollenEyelidsSymptom_eyesightTextView));
        q1_tearingTextView.setOnClickListener(ev-> textViewOnClick("Tearing eyes",R.id.q1_tearingEyesSymptom_eyesightTextView));
    }

    private void textViewOnClick(String symptom, int symptomId) {
        TextView textView = view.findViewById(symptomId);
        if(eyesightSymptoms.contains(symptom)){
            eyesightSymptoms.remove(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_unchecked,null));
            textView.setTextColor(getResources().getColor(R.color.greenText,null));
        }
        else{
            eyesightSymptoms.add(symptom);
            textView.setBackground(view.getResources().getDrawable(R.drawable.symptoms_buttons_checked,null));
            textView.setTextColor(getResources().getColor(R.color.whiteText,null));
        }
    }


    private void assignQ1ComponentReferences() {
        q1_editText = view.findViewById(R.id.q1_eyesightEditText);
        q1_itchinessTextView = view.findViewById(R.id.q1_itchinessSymptom_eyesightTextView);
        q1_redEyesTextView = view.findViewById(R.id.q1_redEyesSymptom_eyesightTextView);
        q1_seekBar = view.findViewById(R.id.q1_eyesightSeekbar);
        q1_sensitivityTextView = view.findViewById(R.id.q1_sensitivitytolightEyelidsSymptom_eyesightTextView);
        q1_sorenessTextView = view.findViewById(R.id.q1_sorenessEyelidsSymptom_eyesightTextView);
        q1_swollenTextView = view.findViewById(R.id.q1_swollenEyelidsSymptom_eyesightTextView);
        q1_tearingTextView = view.findViewById(R.id.q1_tearingEyesSymptom_eyesightTextView);
    }

    public static List<String> getEyesightSymptoms(){
        return eyesightSymptoms;
    }
}
