package nysa.nysa_20.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.model.SymptomEntry;
import nysa.nysa_20.model.Toolbar_MainActivity;
import nysa.nysa_20.model.Toolbar_SymptomEntryActivity;
import nysa.nysa_20.model.adaptors.MainActivityPagerAdaptor;
import nysa.nysa_20.model.adaptors.SymptomEntryActivityPagerAdaptor;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentEyeSymptoms;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class SymptomEntryActivity extends AppCompatActivity {
    private static ViewPager symptomEntryPager;
    private static SymptomEntryActivityPagerAdaptor adaptor;
    private static Toolbar_SymptomEntryActivity toolbar_symptomEntryActivity;
    private static TextView saveButton;
    private static TextView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_entry);

        initComponenets();
    }

    private void initComponenets() {

        initElements();
        initElementsFunction();
        prepareViewPager();


    }

    private void initElementsFunction() {
        adaptor = new SymptomEntryActivityPagerAdaptor(getSupportFragmentManager());
        symptomEntryPager.setAdapter(adaptor);

        saveButton.setOnClickListener(ev -> saveButtonClicked());
        cancelButton.setOnClickListener(ev -> cancelButtonClicked());
    }

    private void cancelButtonClicked() {
        ActivityShiftService.toMainActivity(this);
        //TODO CANCEL BUTTON SYMPTOM ENTRY
    }

    private void saveButtonClicked() {
        ActivityShiftService.toMainActivity(this);
        List<String> eyesightSymptoms = FragmentEyeSymptoms.getEyesightSymptoms();
        if(eyesightSymptoms!=null){
            Toast.makeText(this, "OH YEAHHH", Toast.LENGTH_LONG).show();
        }
        //TODO SAVED BUTTON SYMPTOM ENTRY
    }

    private void initElements() {
        symptomEntryPager = findViewById(R.id.symptomEntryViewPager);
        toolbar_symptomEntryActivity = findViewById(R.id.symptomEntryToolBar);
        saveButton = findViewById(R.id.saveSymptomEntryTextView);
        cancelButton = findViewById(R.id.cancelSymptomEntryTextView);
    }

    private void prepareViewPager() {
        symptomEntryPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar_symptomEntryActivity.updateToolbarImageResources(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static void setCurrentTab(int position){
        symptomEntryPager.setCurrentItem(position);
        toolbar_symptomEntryActivity.updateToolbarImageResources(position);
    }
}
