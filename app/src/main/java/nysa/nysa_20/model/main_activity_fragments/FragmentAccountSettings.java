package nysa.nysa_20.model.main_activity_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.activity.ManagementDoctorsActivity;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.service.QRService.QRGenerator;
import nysa.nysa_20.service.QRService.QRReader;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class FragmentAccountSettings extends Fragment {
    private TextView usernameTextView;
    private TextView emailTextView;
    private Button changeAllergiesButton;
    private Button logOutButton;
    private Button deleteAccount;
    private View view;
    private Button managementButton;
    public FragmentAccountSettings(){}


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_account_settings, container, false);
        initComponents();

        return view;
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
        managementButton.setOnClickListener(ev -> managementButtonClicked());
    }

    private void managementButtonClicked() {
            Intent intent = new Intent(this.getContext(), ManagementDoctorsActivity.class);
            this.startActivity(intent);
    }

    private void deleteAccountClicked() {
        AccountHolder.disconnect();
        ActivityShiftService.toLoginActivity(view.getContext());
        //TODO deleteAccount

    }

    private void logOutButtonClicked() {
        AccountHolder.disconnect();
        ActivityShiftService.toLoginActivity(view.getContext());
    }

    private void changeAllergiesButtonClicked() {
       // ActivityShiftService.toAllergyRegisterActivity(view.getContext());
        Intent intent = new Intent(view.getContext(), QRReader.class);
        this.startActivity(intent);
    }

    private void assignComponentReferences() {
        usernameTextView = view.findViewById(R.id.usernameAccountSettingsTextView);
        emailTextView = view.findViewById(R.id.emailAccountSettingsTextView);
        changeAllergiesButton = view.findViewById(R.id.changeAllergiesButton);
        logOutButton = view.findViewById(R.id.logOutButton);
        deleteAccount = view.findViewById(R.id.deleteAccountButton);
        managementButton = view.findViewById(R.id.managementDoctorButton);
    }
}
