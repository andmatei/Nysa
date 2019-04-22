package nysa.nysa_20.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.adaptors.PatientsRecyclerView;
import nysa.nysa_20.service.QRService.QRGenerator;

public class MainActivity extends AppCompatActivity {
    private static TextView qrGeneratorTextView;
    private static TextView accountSettingsTextView;
    private static RecyclerView recyclerView;
    private static PatientsRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();

    }

    private void initComponents(){
        initComponentReferences();
        initComponentFunctionalities();
    }

    private void initComponentFunctionalities() {
        qrGeneratorTextView.setOnClickListener(ev -> qrGeneratorTextViewClicked());
        accountSettingsTextView.setOnClickListener(ev -> accountSettingsTextViewClicked());
        setupRecycleView();
    }

    private void accountSettingsTextViewClicked() {
        Intent toAccountSettings = new Intent(this,AccountSettingsActivity.class);
        startActivity(toAccountSettings);
    }


    private void qrGeneratorTextViewClicked() {
        Intent toQRGenerator = new Intent(this, QRGenerator.class);
        startActivity(toQRGenerator);
    }

    private void initComponentReferences() {
        qrGeneratorTextView = findViewById(R.id.qrGeneratorTextView);
        accountSettingsTextView = findViewById(R.id.accountSettingsTextView);
        recyclerView = findViewById(R.id.mainRecycleView);
        adapter = new PatientsRecyclerView(this);
    }



    private void setupRecycleView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }




}
