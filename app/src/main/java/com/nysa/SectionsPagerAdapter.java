package com.nysa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Vlad on 25/04/2018 0025.
 */

class SectionsPagerAdapter extends FragmentPagerAdapter{

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }



    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : EyesFragment eyesFragment = new EyesFragment();


                      return eyesFragment;
            case 1 :PainFragment painFragment = new PainFragment();

                      return painFragment;
            case 2 :RespiratoryFragment respiratoryFragment = new RespiratoryFragment();


                      return respiratoryFragment;
            case 3 : SkinFragment skinFragment = new SkinFragment();


                      return skinFragment;
            default: return null;


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
