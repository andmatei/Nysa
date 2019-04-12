package nysa.nysa_20.model.main_activity_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nysa.nysa_20.R;

public class FragmentSymptomTrack extends Fragment {
    public FragmentSymptomTrack(){}
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_symptom_track, container, false);

        return view;

    }
}
