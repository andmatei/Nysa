package nysa.nysa_20.model.main_activity_fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.adaptors.NewsRecyclerView;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.TimeUtilitaryClass;
import nysa.nysa_20.service.utilitary.SymptomEntryService;

public class FragmentHome extends Fragment {
    private TextView currentDateTextView;
    private TextView usernameTextView;
    private TextView currentScoreTextView;
    private TextView maxScoreTextView;
    private ProgressBar progressBar;
    private ImageView addSymptomButton;
    private TextView noSymptom1TextView;
    private TextView noSymptom2TextView;
    private TextView symptom1TextView;
    private Account account;
    private RecyclerView recyclerView;
    private View view;


    public FragmentHome(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);

        account = AccountHolder.getAccount();


        prepareComponents();

        return view;

    }

    private void prepareComponents() {

        initializeElements();
        initializeElementFunctions();

        prepareSymptomSugestion();

        prepareNewsfeed();


    }


    private void initializeElements() {
        currentDateTextView = view.findViewById(R.id.dateMainTextView);
        usernameTextView = view.findViewById(R.id.usernameMainTextView);
        currentScoreTextView = view.findViewById(R.id.currentScoreTextView);
        maxScoreTextView = view.findViewById(R.id.maxScoreTextView);
        progressBar = view.findViewById(R.id.progressBar);
        addSymptomButton  = view.findViewById(R.id.addSymptomButtonImageView);
        noSymptom1TextView = view.findViewById(R.id.noSymptoms1TextView);
        noSymptom2TextView = view.findViewById(R.id.noSimptoms2TextView);
        symptom1TextView = view.findViewById(R.id.symptom1TextView);
        recyclerView = view.findViewById(R.id.recyclerviewid);

    }

    private void initializeElementFunctions() {
        currentDateTextView.setText(TimeUtilitaryClass.getCurrentTimeFormatMainActivity());
        usernameTextView.setText(account.getUsername());
        addSymptomButton.setOnClickListener(ev -> ActivityShiftService.toSymptomEntryActivity(view.getContext()));
    }

    private void prepareSymptomSugestion() {


        if(SymptomEntryService.isTodayLastEntry()){
            currentScoreTextView.setVisibility(View.VISIBLE);
            maxScoreTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            symptom1TextView.setVisibility(View.VISIBLE);
            noSymptom1TextView.setVisibility(View.GONE);
            noSymptom2TextView.setVisibility(View.GONE);

            int progress = SymptomEntryService.getScore(Objects.requireNonNull(SymptomEntryService.getTodaySymptomEntry()));
            currentScoreTextView.setText(String.valueOf(progress));
            progressBar.setProgress(progress);
        }
        else{
            currentScoreTextView.setVisibility(View.GONE);
            maxScoreTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            symptom1TextView.setVisibility(View.GONE);
            noSymptom1TextView.setVisibility(View.VISIBLE);
            noSymptom2TextView.setVisibility(View.VISIBLE);


        }
    }

    private void prepareNewsfeed(){
        NewsRecyclerView adapter = new NewsRecyclerView(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);


    }



}
