package nysa.nysa_20.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nysa.nysa_20.R;
import nysa.nysa_20.model.APIResponse;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.DoctorAccount;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.model.Symptom;
import nysa.nysa_20.model.SymptomEntry;
import nysa.nysa_20.service.connectivity.RegisterService;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
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
        phoneEditText = (EditText) findViewById(R.id.phoneEditText_Register);
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
        ActivityShiftService.toLoginActivity(this);
    }

    private void registerButtonClicked(){
        RegistrationFormular registrationFormular = new RegistrationFormular.Builder()
                .setFirstName(firstNameEditText.getText().toString().trim())
                .setLastName(lastNameEditText.getText().toString().trim())
                .setUsername(phoneEditText.getText().toString().trim())
                .setEmail(emailEditText.getText().toString().trim())
                .setPassword(passwordEditText.getText().toString().trim())
                .build();
        if(registrationFormular.isAnyEmpty()){
            Toast.makeText(this,"All fields must be completed!",Toast.LENGTH_SHORT).show();
        }
        else{

            //ExecutorService executorService = Executors.newCachedThreadPool();
           // RegisterService registerService = new RegisterService(registrationFormular);
           // Future<APIResponse> future = executorService.submit(registerService);
           // try {

             //   APIResponse apiResponse  = future.get();
             //   if(apiResponse.getStatus().equals("FAIL"))
              //      Toast.makeText(this,apiResponse.getMessege(), Toast.LENGTH_SHORT).show();
            //    else {
                    Toast.makeText(this,"Account successfully created!", Toast.LENGTH_SHORT).show();

                    DoctorAccount account = new DoctorAccount(registrationFormular);



            Account patient = new Account();
            patient.setFirstName("Vlad");
            patient.setLastName("Vlacu");
            patient.setUsername("WackoBeast61");
            patient.setEmail("vladvlaicu99@gmail.com");
            patient.setId("ID001");
            HashMap<String,Boolean> allergyMap = new HashMap<>();
            allergyMap.put("Lactose Allergy",true);
            allergyMap.put("Cat",false);
            allergyMap.put("Insect",true);
            patient.setAllergyMap(allergyMap);

            HashMap<java.time.LocalDate, SymptomEntry> historyMap = new HashMap<>();
            SymptomEntry entry = new SymptomEntry();

            List<String> symptoms = new ArrayList<>();
            symptoms.add("Itchiness");


            entry.getSymptomsPainEntry().add(new Symptom(symptoms,3,"AUCH!","Pain"));
            historyMap.put(LocalDate.now(),entry);
            patient.setHistoryMap(historyMap);
            account.getPatients().add(patient);

            AccountHolder.setAccount(account);
            MainLocalPersistenceService.persistCurrentAccount();



                    ActivityShiftService.toMainActivity(this);


                //    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                 //   SharedPreferences.Editor editor = preferences.edit();

                //    try {
                //        editor.putString("authToken", apiResponse.getDataJSON().getString("auth_token"));
                //        editor.putString("refreshToken", apiResponse.getDataJSON().getString("refresh_token"));
                 //       editor.putString("id", apiResponse.getDataJSON().getString("id"));

                 //       editor.apply();
                //    } catch (Exception e) {
              //          e.printStackTrace();
              //      }
              //  }

          //  } catch (InterruptedException | ExecutionException e){
          //      e.printStackTrace();
        //    } finally {
         //       executorService.shutdown();
        //    }
        }
    }


}
