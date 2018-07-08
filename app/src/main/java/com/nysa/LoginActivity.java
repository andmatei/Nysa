package com.nysa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private Toolbar lToolbar;
    private EditText lEmail;
    private EditText lPassword;
    private Button LBtn;
    private Button GBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog lgProgress;
    private TextView tEmail;
    private TextView tPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mAuth = FirebaseAuth.getInstance();


        lEmail = (EditText) findViewById(R.id.emailAddress);
        lPassword = (EditText) findViewById(R.id.passwordAdress);
        LBtn = (Button) findViewById(R.id.lgBtn);
        GBtn = (Button) findViewById(R.id.regBtn);

        lgProgress = new ProgressDialog(this);

        LBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lg_email = lEmail.getText().toString();
                String lg_password = lPassword.getText().toString();

                if(!TextUtils.isEmpty(lg_email)&&!TextUtils.isEmpty(lg_password)){

                    lgProgress.setTitle("Logging In");
                    lgProgress.setCanceledOnTouchOutside(false);
                    lgProgress.setMessage("Please wait while we check your credentials!");
                    //lgProgress.show();

                    signIn_user(lg_email, lg_password);

                }

            }
        });

       Typeface fontSegoeLight = Typeface.createFromAsset(getAssets(),"Fonts/segoeuil.ttf");
        Typeface fontSegoeBold = Typeface.createFromAsset(getAssets(),"Fonts/segoeuib.ttf");
        Typeface fontSegoeSemiLight = Typeface.createFromAsset(getAssets(),"Fonts/segoe-ui-semilight-610.ttf");
       lEmail.setTypeface(fontSegoeSemiLight);
       lPassword.setTypeface(fontSegoeSemiLight);

       GBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent regIntent = new Intent(LoginActivity.this,RegisterActivity.class);
               regIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(regIntent);

           }
       });

    }
    private void signIn_user(String lg_email, String lg_password) {
        mAuth.signInWithEmailAndPassword(lg_email,lg_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //lgProgress.dismiss();
                    Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }else{

                    //lgProgress.hide();
                    Toast.makeText(LoginActivity.this,"You got an Error. Please check the form and try again.",Toast.LENGTH_LONG).show();

                }
            }
        });


    };
}

