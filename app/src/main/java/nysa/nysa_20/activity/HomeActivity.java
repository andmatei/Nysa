package nysa.nysa_20.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.adaptors.NewsRecyclerView;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.MainActivityUtilitaryClass;
import nysa.nysa_20.service.utilitary.SymptomEntryService;


public class HomeActivity extends AppCompatActivity {
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        account = AccountHolder.getAccount();

        prepareComponents();

    }

    private void prepareComponents() {

        initializeElements();
        initializeElementFunctions();

        prepareSymptomSugestion();

        prepareNewsfeed();

    }


    private void initializeElements() {
        currentDateTextView = findViewById(R.id.dateMainTextView);
        usernameTextView = findViewById(R.id.usernameMainTextView);
        currentScoreTextView = findViewById(R.id.currentScoreTextView);
        maxScoreTextView = findViewById(R.id.maxScoreTextView);
        progressBar = findViewById(R.id.progressBar);
        addSymptomButton  = findViewById(R.id.addSymptomButtonImageView);
        noSymptom1TextView = findViewById(R.id.noSymptoms1TextView);
        noSymptom2TextView = findViewById(R.id.noSimptoms2TextView);
        symptom1TextView = findViewById(R.id.symptom1TextView);
        recyclerView = findViewById(R.id.recyclerviewid);

    }

     private void initializeElementFunctions() {
        currentDateTextView.setText(MainActivityUtilitaryClass.getCurrentTime());
        usernameTextView.setText(account.getUsername());
        addSymptomButton.setOnClickListener(ev -> ActivityShiftService.toSymptomTrackActivity(this));
    }

    private void prepareSymptomSugestion() {


        if(SymptomEntryService.isTodayLastEntry()){
            currentScoreTextView.setVisibility(View.VISIBLE);
            maxScoreTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            symptom1TextView.setVisibility(View.VISIBLE);
            noSymptom1TextView.setVisibility(View.GONE);
            noSymptom2TextView.setVisibility(View.GONE);

            int progress = Integer.parseInt(currentScoreTextView.getText().toString());
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
        NewsRecyclerView adapter = new NewsRecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

}
