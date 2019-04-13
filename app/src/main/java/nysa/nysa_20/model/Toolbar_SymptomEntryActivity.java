package nysa.nysa_20.model;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.SymptomEntryActivity;

public class Toolbar_SymptomEntryActivity extends ConstraintLayout {

    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ImageButton eyeSymptomsButton;
    private ImageButton painSymptomsButton;
    private ImageButton respiratorySymptomsButton;
    private ImageButton skinSymptomsButton;


    public Toolbar_SymptomEntryActivity(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        context = mContext;
       // activity = (Activity) context;
        Inflate();
        BindViews();
        ToolBarSetup();
    }

    private void ToolBarSetup() {
        eyeSymptomsButton.setImageResource(R.drawable.eyes);

        eyeSymptomsButton.setOnClickListener(ev -> SymptomEntryActivity.setCurrentTab(0));

        painSymptomsButton.setOnClickListener(ev -> SymptomEntryActivity.setCurrentTab(1));

        respiratorySymptomsButton.setOnClickListener(ev -> SymptomEntryActivity.setCurrentTab(2));

        skinSymptomsButton.setOnClickListener(ev -> SymptomEntryActivity.setCurrentTab(3));

    }

    private void BindViews() {
        eyeSymptomsButton = findViewById(R.id.eyeSymptomsButton);
        painSymptomsButton = findViewById(R.id.painSymptomsButton);
        respiratorySymptomsButton = findViewById(R.id.respiratorySymptomsButton);
        skinSymptomsButton = findViewById(R.id.skinSymptomsButton);

    }

    private void Inflate() {
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.toolbar_symptom_entry_activity, this, true);
    }


    public void updateToolbarImageResources(int position){

        switch (position){
            case 0:  eyeSymptomsButton.setImageResource(R.drawable.eyes);
                     painSymptomsButton.setImageResource(R.drawable.nopain);
                     respiratorySymptomsButton.setImageResource(R.drawable.nolungs);
                     skinSymptomsButton.setImageResource(R.drawable.noskin);

                return;

            case 1:   eyeSymptomsButton.setImageResource(R.drawable.noeyes);
                      painSymptomsButton.setImageResource(R.drawable.pain);
                      respiratorySymptomsButton.setImageResource(R.drawable.nolungs);
                      skinSymptomsButton.setImageResource(R.drawable.noskin);
                return;

            case 2:   eyeSymptomsButton.setImageResource(R.drawable.noeyes);
                      painSymptomsButton.setImageResource(R.drawable.nopain);
                      respiratorySymptomsButton.setImageResource(R.drawable.lungs);
                      skinSymptomsButton.setImageResource(R.drawable.noskin);
                return;
            case 3:  eyeSymptomsButton.setImageResource(R.drawable.noeyes);
                     painSymptomsButton.setImageResource(R.drawable.nopain);
                     respiratorySymptomsButton.setImageResource(R.drawable.nolungs);
                     skinSymptomsButton.setImageResource(R.drawable.skin);

                return;


        }
    }
}
