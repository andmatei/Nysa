package nysa.nysa_20.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class AccountSettingsActivity extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView emailTextView;
    private Button changeAllergiesButton;
    private Button logOutButton;
    private Button deleteAccount;
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
        Account account = AccountHolder.getAccount();
        usernameTextView.setText(account.getUsername());
        emailTextView.setText(account.getEmail());
        changeAllergiesButton.setOnClickListener(ev -> changeAllergiesButtonClicked());
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

    private void changeAllergiesButtonClicked() {
        ActivityShiftService.toAllergyRegisterActivity(this);

    }

    private void assignComponentReferences() {
        usernameTextView = findViewById(R.id.usernameAccountSettingsTextView);
        emailTextView = findViewById(R.id.emailAccountSettingsTextView);
        changeAllergiesButton = findViewById(R.id.changeAllergiesButton);
        logOutButton = findViewById(R.id.logOutButton);
        deleteAccount = findViewById(R.id.deleteAccountButton);
    }
}
