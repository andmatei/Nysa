package nysa.nysa_20.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import nysa.nysa_20.R;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.LoginFormular;
import nysa.nysa_20.service.connectivity.LoginService;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        checkLoginStatus();
        prepareComponents();

    }

    private void checkLoginStatus() {
        String filename = "AccountFile.txt";
        File file = new File(this.getFilesDir(), filename);
        String fileContents = "";
        FileOutputStream outputStream;
        if (!file.exists())

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainLocalPersistenceService.setAccountFile(file);


        if(MainLocalPersistenceService.isAnyAccountPersisted()){
           MainLocalPersistenceService.retrievePersistedAccount();

        }
        if(!AccountHolder.isEmpty()){
            ActivityShiftService.toMainActivity(this);
        }


    }


    private void prepareComponents() {
       initializeElements();
       initializeButtonFunctions();
    }

    private void initializeButtonFunctions() {
        loginButton.setOnClickListener(ev -> loginButtonClicked());
        registerButton.setOnClickListener(ev -> registerButtonClicked());
    }



    private void initializeElements() {
        emailEditText = (EditText) findViewById(R.id.emailEditText_Login);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText_Login);
        loginButton = (Button) findViewById(R.id.loginButton_Login);
        registerButton = (Button) findViewById(R.id.registerButton_Login);
    }

    private void loginButtonClicked(){
        LoginFormular loginFormular = new LoginFormular.Builder()
                .setEmail(emailEditText.getText().toString().trim())
                .setPassword(passwordEditText.getText().toString().trim())
                .build();
        if(loginFormular.isAnyEmpty()){
            Toast.makeText(this,"All fields must be completed!",Toast.LENGTH_SHORT).show();
        }
        else{
            int processCompleted = LoginService.initiateLoginSequence(loginFormular);

            if(processCompleted == 0) {
                Toast.makeText(this,"Invalid email or password!", Toast.LENGTH_SHORT).show();
            }
            else
                if(processCompleted==1){

                    Toast.makeText(this,"Login successful!",Toast.LENGTH_LONG).show();
                    //TODO retrieve data and continue the flow
                }
                else{
                    Toast.makeText(this,"Oh, no! There was an error!",Toast.LENGTH_LONG).show();
                }

        }


    }
    private void registerButtonClicked() {
        ActivityShiftService.toRegisterActivity(this);
    }
}
