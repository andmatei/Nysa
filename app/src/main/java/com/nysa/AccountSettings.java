package com.nysa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountSettings extends AppCompatActivity {

    private Button ChangeAllergies;
    private Button LogOut;
    private Button DeleteAcc;
    private FirebaseUser user;
    private TextView UserName;
    private TextView EmailAdd;
    private String uid = new String();
    private DatabaseReference nDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        user = FirebaseAuth.getInstance().getCurrentUser();
        ChangeAllergies = (Button) findViewById(R.id.changeAllergies);
        LogOut = (Button) findViewById(R.id.LogOut);
        DeleteAcc = (Button) findViewById(R.id.DeleteAcc);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(AccountSettings.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        DeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(user, AccountSettings.this);
            }
        });
        ChangeAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountSettings.this, RegisterAllergies.class);
                startActivity(i);
            }
        });

        UserName = (TextView) findViewById(R.id.usernameTextView);
        UserName.setText(user.getDisplayName());

        EmailAdd = (TextView) findViewById(R.id.emailTextView);
        EmailAdd.setText(user.getEmail());

        uid = user.getUid();
        nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        nDatabase.keepSynced(true);
        nDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String getUsername =(String) dataSnapshot.child("username").getValue();
                UserName.setText(getUsername);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void Delete(FirebaseUser user, final Context mContext) {
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Intent i = new Intent(mContext, LoginActivity.class);
                    Activity activity = (Activity) mContext;
                    activity.finish();
                    mContext.startActivity(i);
                }
            }
        });
    }
}
