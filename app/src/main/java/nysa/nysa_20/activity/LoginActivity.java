package nysa.nysa_20.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.LoginFormular;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.service.connectivity.LocationService;
import nysa.nysa_20.service.connectivity.LoginService;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.PermissionService;

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
        String filename = "PatientAccountFile.txt";
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

            ExecutorService executorService = Executors.newCachedThreadPool();
            LoginService loginService = new LoginService(loginFormular);
            Future<JSONObject> future = executorService.submit(loginService);
            try {

                JSONObject apiResponse  = future.get();
                if(apiResponse.getString("status").equals("FAIL"))
                    Toast.makeText(this,apiResponse.getString("messege"), Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(this,"Successfully logged in!", Toast.LENGTH_SHORT).show();

                    JSONObject data = apiResponse.getJSONObject("data");
                    Account account = new Account(
                            new RegistrationFormular.Builder()
                                .setEmail(data.getString("email"))
                                .setFirstName(data.getString("first_name"))
                                .setLastName(data.getString("last_name"))
                                .setUsername(data.getString("username"))
                                .build()
                    );
                    AccountHolder.setAccount(account);
                    ActivityShiftService.toMainActivity(this);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("authToken", data.getString("auth_token"));
                    editor.putString("refreshToken", data.getString("refresh_token"));
                    editor.putString("id", data.getString("id"));

                    editor.apply();
                }

            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                executorService.shutdown();
            }
        }


    }
    private void registerButtonClicked() {
        ActivityShiftService.toRegisterActivity(this);
    }
}
