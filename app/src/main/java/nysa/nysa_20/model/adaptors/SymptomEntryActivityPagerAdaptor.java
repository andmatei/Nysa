package nysa.nysa_20.model.adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nysa.nysa_20.model.main_activity_fragments.FragmentAccountSettings;
import nysa.nysa_20.model.main_activity_fragments.FragmentHome;
import nysa.nysa_20.model.main_activity_fragments.FragmentLibrary;
import nysa.nysa_20.model.main_activity_fragments.FragmentSymptomTrack;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentEyeSymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentPainSymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentRespiratorySymptoms;
import nysa.nysa_20.model.symptom_entry_activity_fragments.FragmentSkinSymptoms;

public class SymptomEntryActivityPagerAdaptor extends FragmentPagerAdapter {
    public SymptomEntryActivityPagerAdaptor(FragmentManager fragmentManager){super(fragmentManager);}

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentEyeSymptoms();
            case 1: return new FragmentPainSymptoms();
            case 2: return new FragmentRespiratorySymptoms();
            default:return new FragmentSkinSymptoms();


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
