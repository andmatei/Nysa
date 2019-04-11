package nysa.nysa_20.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.service.connectivity.RegisterService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prepareComponents();

    }

    private void prepareComponents() {
        initializeElements();
        initializeButtonFunctions();
    }

    private void initializeElements(){
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText_Register);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText_Register);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText_Register);
        emailEditText = (EditText) findViewById(R.id.emailEditText_Register);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText_Register);
        loginButton = (Button) findViewById(R.id.loginButton_Register);
        registerButton = (Button) findViewById(R.id.registerButton_Register);

    }

    private void initializeButtonFunctions() {
        loginButton.setOnClickListener(ev -> loginButtonClicked());
        registerButton.setOnClickListener(ev -> registerButtonClicked());

    }

    private void loginButtonClicked(){
        ActivityShiftService.ToLoginActivity(this);
    }

    private void registerButtonClicked(){
        RegistrationFormular registrationFormular = new RegistrationFormular.Builder()
                .setFirstName(firstNameEditText.getText().toString().trim())
                .setLastName(lastNameEditText.getText().toString().trim())
                .setUsername(usernameEditText.getText().toString().trim())
                .setEmail(emailEditText.getText().toString().trim())
                .setPassword(passwordEditText.getText().toString().trim())
                .build();
        if(registrationFormular.isAnyEmpty()){
            Toast.makeText(this,"All fields must be completed!",Toast.LENGTH_SHORT).show();

        }
        else{

            int processCompleted =  RegisterService.initiateRegisterSequence(registrationFormular);

            if(processCompleted == 0){
                Toast.makeText(this,"This account already exists!",Toast.LENGTH_SHORT).show();
           }
           else
               if(processCompleted == 1){
                    Toast.makeText(this,"Account successfully created!", Toast.LENGTH_SHORT).show();


                   Account account = new Account(registrationFormular);
                   AccountHolder.setAccount(account);
                   ActivityShiftService.toAllergyRegisterActivity(this);

               }
               else{
                   Toast.makeText(this,"Oh, no! There was an error!",Toast.LENGTH_SHORT).show();

               }

        }
    }


}
