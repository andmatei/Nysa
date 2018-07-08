package com.nysa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText nDisplayFirstName;
    private EditText nDisplayLastName;
    private EditText nDisplayEmail;
    private EditText nDisplayPassword;
    private EditText nDisplayUsername;
    private Button nregLoginBtn;
    private Button nregRegBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog nRegProgres;
    private DatabaseReference nDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nRegProgres = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        nDisplayFirstName = (EditText) findViewById(R.id.FirstName);
        nDisplayLastName = (EditText) findViewById(R.id.LastName);
        nDisplayEmail = (EditText) findViewById(R.id.Email);
        nDisplayPassword = (EditText) findViewById(R.id.Password);
        nDisplayUsername = (EditText) findViewById(R.id.Username);
        nregRegBtn = (Button) findViewById(R.id.regBtn);
        nregLoginBtn = (Button) findViewById(R.id.lgBtn);


        nregRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String display_first_name = nDisplayFirstName.getText().toString();
                String display_last_name = nDisplayLastName.getText().toString();
                String display_email = nDisplayEmail.getText().toString();
                String display_password = nDisplayPassword.getText().toString();
                String display_username = nDisplayUsername.getText().toString();
                if (!TextUtils.isEmpty(display_first_name) && !TextUtils.isEmpty(display_email) && !TextUtils.isEmpty(display_password) && !TextUtils.isEmpty(display_last_name) && !TextUtils.isEmpty(display_username)) {

                    nRegProgres.setTitle("Creating Account");
                    nRegProgres.setMessage("Please wait while we are creating your account!");
                    nRegProgres.setCanceledOnTouchOutside(false);
                    nRegProgres.show();
                    register_user(display_first_name, display_last_name, display_username, display_email, display_password);
                }

            }
        });


        nregLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                toLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toLoginIntent);


            }
        });
    }


    private void register_user(final String firstName, final String lastName, final String username, final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();
                    nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    HashMap<String, String> userMap = new HashMap<>();
                    String FIRST_NAME = firstName;
                    userMap.put("first_name", FIRST_NAME);
                    String LAST_NAME = lastName;
                    userMap.put("last_name", LAST_NAME);
                    String USERNAME = username;
                    userMap.put("username", USERNAME);
                    String ALLERGIES = "nulll";
                    userMap.put("allergies", ALLERGIES);
                    String EMAIL = email;
                    userMap.put("email", EMAIL);
                    String PASSWORD = password;
                    userMap.put("password", PASSWORD);
                    String lastEntrance = "now";
                    userMap.put("last_entrance", lastEntrance);
                    userMap.put("tracker", "non");


                    nDatabase.setValue(userMap);


               nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker");
                   HashMap<String,String> trackMap = new HashMap<>();
                   trackMap.put("ES","-1");
                   trackMap.put("PS","-1");
                   trackMap.put("SS","-1");
                   trackMap.put("RS1","-1");
                   trackMap.put("RS2","-1");
                   trackMap.put("Score","0");
                   nDatabase.setValue(trackMap);



                   nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("allergies");
                   HashMap<String,String> userrMap = new HashMap<>();
                   String Allergy;
                   String [] allergies = new String[20];
                   allergies[1] = "Lactose";
                   allergies[2] = "Egg";
                   allergies[3] = "Gluten";
                   allergies[4] = "Sesame";
                   allergies[5] = "Crustaceans";
                   allergies[6] = "Peanuts";
                   allergies[7] = "Soy";
                   allergies[8] = "Nuts";
                   allergies[9] = "Fish";
                   allergies[10] = "Dog";
                   allergies[11] = "Cat";
                   allergies[12] = "Penicilin";
                   allergies[13] = "Antibiotics";
                   allergies[14] = "Anticonvulsants";
                   allergies[15] = "Aspirin";
                   allergies[16] = "Insect";
                   allergies[17] = "Pollen";
                   allergies[18] = "Dust";
                   allergies[19] = "Mold";
                    Boolean F = false;
                   for(int i=1;i<=19;i++)
                   {

                       userrMap.put(allergies[i],F.toString());

                   }

                   nDatabase.setValue(userrMap);


                    nRegProgres.dismiss();
                    Intent mainIntent = new Intent(RegisterActivity.this, RegisterAllergies.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();

                } else {
                    nRegProgres.hide();
                    Toast.makeText(RegisterActivity.this, "Cannot finish the registration. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }


            }
        });

    }
  }

