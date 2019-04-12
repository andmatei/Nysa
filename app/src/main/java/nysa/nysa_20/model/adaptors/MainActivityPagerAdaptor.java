package nysa.nysa_20.model.adaptors;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import nysa.nysa_20.model.main_activity_fragments.FragmentAccountSettings;
import nysa.nysa_20.model.main_activity_fragments.FragmentLibrary;
import nysa.nysa_20.model.main_activity_fragments.FragmentHome;
import nysa.nysa_20.model.main_activity_fragments.FragmentSymptomTrack;

public class MainActivityPagerAdaptor extends FragmentPagerAdapter {

    public MainActivityPagerAdaptor(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1: return new FragmentAccountSettings();
            case 2: return new FragmentLibrary();
            case 3: return new FragmentSymptomTrack();
            default:return new FragmentHome();


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
