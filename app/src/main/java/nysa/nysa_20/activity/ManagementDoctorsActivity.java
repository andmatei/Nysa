package nysa.nysa_20.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.Doctor;
import nysa.nysa_20.service.QRService.QRReader;

public class ManagementDoctorsActivity extends AppCompatActivity {
    private static TextView addDoctor;
    private static TextView nameDoctor;
    private static TextView phoneDoctor;
    private static TextView mailDoctor;
    private static Account account;
    private static Doctor newDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_doctors);

        initComponents();



    }

    private void initComponents() {
        initComponentsReferences();
        initComponentsFunctionalities();
    }

    private void initComponentsReferences() {
        addDoctor = findViewById(R.id.addDoctorTextView);
        nameDoctor = findViewById(R.id.nameDoctorTextView);
        phoneDoctor = findViewById(R.id.phoneDoctorTextView);
        mailDoctor = findViewById(R.id.emailDoctorTextView);

    }

    private void initComponentsFunctionalities() {
        account = AccountHolder.getAccount();
        if(account.getDoctor()!=null){
            Doctor doctor = account.getDoctor();
            nameDoctor.setText(doctor.getNume());
            phoneDoctor.setText(doctor.getPhone());
            mailDoctor.setText(doctor.getEmail());
        }
        addDoctor.setOnClickListener(ev -> addDoctorClicked());

    }

    private void addDoctorClicked() {
        Intent intent = new Intent(this, QRReader.class);
        this.startActivity(intent);
        finish();

    }
}
