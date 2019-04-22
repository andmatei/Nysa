package nysa.nysa_20.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.DoctorAccount;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class AccountSettingsActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView;
    private Button logOutButton;
    private Button deleteAccount;
    public AccountSettingsActivity(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_settings);
        initComponents();


    }
    private void initComponents() {
        assignComponentReferences();
        assignComponentFunctionalities();
    }

    private void assignComponentFunctionalities() {
        DoctorAccount account = AccountHolder.getAccount();
        nameTextView.setText(account.getFirstName()+" "+account.getLastName());
        emailTextView.setText(account.getEmail());
        logOutButton.setOnClickListener(ev -> logOutButtonClicked());
        deleteAccount.setOnClickListener(ev -> deleteAccountClicked());

    }


    private void deleteAccountClicked() {
        AccountHolder.disconnect();
        ActivityShiftService.toLoginActivity(this);
        //TODO deleteAccount

    }

    private void logOutButtonClicked() {
        AccountHolder.disconnect();
        ActivityShiftService.toLoginActivity(this);
    }



    private void assignComponentReferences() {
        nameTextView = findViewById(R.id.nameAccountSettingsTextView);
        emailTextView = findViewById(R.id.emailAccountSettingsTextView);
        logOutButton = findViewById(R.id.logOutButton);
        deleteAccount = findViewById(R.id.deleteAccountButton);

    }
}
