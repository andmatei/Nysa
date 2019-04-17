package nysa.nysa_20.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.SymptomEntry;
import nysa.nysa_20.model.Toolbar_MainActivity;
import nysa.nysa_20.model.Toolbar_SymptomEntryActivity;
import nysa.nysa_20.model.adaptors.MainActivityPagerAdaptor;
import nysa.nysa_20.model.adaptors.SymptomEntryActivityPagerAdaptor;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentEyeSymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentPainSymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentRespiratorySymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentSkinSymptoms;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.SymptomEntryService;

public class SymptomEntryActivity extends AppCompatActivity {
    private static ViewPager symptomEntryPager;
    private static SymptomEntryActivityPagerAdaptor adaptor;
    private static Toolbar_SymptomEntryActivity toolbar_symptomEntryActivity;
    private static TextView saveButton;
    private static TextView cancelButton;
    private static Account account;
    private static boolean isEveryPageVisited = false;
    SymptomEntry todaySymptomEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_entry);

        initComponenets();

        if(SymptomEntryService.isTodayLastEntry()){

            todaySymptomEntry = account.getHistoryMap().get(java.time.LocalDate.now());
            FragmentEyeSymptoms.retrievedData(todaySymptomEntry.getSymptomsSightEntry());
            FragmentPainSymptoms.retrievedData(todaySymptomEntry.getSymptomsPainEntry());
            FragmentRespiratorySymptoms.retrievedData(todaySymptomEntry.getSymptomsRespirationEntry());
            FragmentSkinSymptoms.retrievedData(todaySymptomEntry.getSymptomsSkinEntry());
        }

    }

    private void initComponenets() {

        initElements();
        initElementsFunction();
        prepareViewPager();
        account = AccountHolder.getAccount();
        todaySymptomEntry = new SymptomEntry();

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


            todaySymptomEntry.setSymptomsSightEntry( FragmentEyeSymptoms.getSymptoms());
            todaySymptomEntry.setSymptomsPainEntry(FragmentPainSymptoms.getSymptoms());
            todaySymptomEntry.setSymptomsRespirationEntry(FragmentRespiratorySymptoms.getSymptoms());
            todaySymptomEntry.setSymptomsSkinEntry(FragmentSkinSymptoms.getSymptoms());

            if(SymptomEntryService.isTodayLastEntry()){
                account.getHistoryMap().replace(java.time.LocalDate.now(),todaySymptomEntry);
            }
            else{
                account.getHistoryMap().put(java.time.LocalDate.now(),todaySymptomEntry);
            }

            MainLocalPersistenceService.persistCurrentAccount();


            ActivityShiftService.toMainActivity(this);



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
                if(position==3){
                    isEveryPageVisited = true;
                }

                if(isEveryPageVisited){
                    saveButton.setVisibility(View.VISIBLE);
                }

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
