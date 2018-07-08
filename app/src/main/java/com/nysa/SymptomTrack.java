package com.nysa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SymptomTrack extends AppCompatActivity {

    private ViewPager trackPager;
    private SectionsPagerAdapter trackAdapter;
    private TabLayout trackTabs;
    private Button saveBtn;
    private DatabaseReference mDatabase;
    private FirebaseUser c_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_track);
        c_user = FirebaseAuth.getInstance().getCurrentUser();
        saveBtn = (Button) findViewById(R.id.SaveBtn);
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = current_user.getUid();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String s1 = dataSnapshot.child("ES").getValue().toString();
                        String s2 = dataSnapshot.child("PS").getValue().toString();
                        String s3 = dataSnapshot.child("RS1").getValue().toString();
                        String s4 = dataSnapshot.child("RS2").getValue().toString();
                        String s5 = dataSnapshot.child("SS").getValue().toString();
                        if(s1.equals("-1")) s1="4";
                        if(s2.equals("-1")) s2="4";
                        if(s3.equals("-1")) s3="4";
                        if(s4.equals("-1")) s4="4";
                        if(s5.equals("-1")) s5="4";
                        int total = (4-Integer.parseInt(s1))*5+(4-Integer.parseInt(s2))*5+(4-Integer.parseInt(s3))*5+(4-Integer.parseInt(s4))*5+(4-Integer.parseInt(s5))*5;
                        DatabaseReference ScoreDB = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker").child("Score");
                        ScoreDB.setValue(total);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                SimpleDateFormat current_date = new SimpleDateFormat("dd-MM-yyyy");
                final String date = current_date.format(new Date());
                changeTime(date);



                toMain();

            }
        });




        trackPager = (ViewPager) findViewById(R.id.trackPager);
        trackTabs = (TabLayout) findViewById(R.id.trackTabs);
        trackAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        trackPager.setAdapter(trackAdapter);
        trackTabs.setupWithViewPager(trackPager);
        trackTabs.getTabAt(0).setIcon(R.drawable.noeyes);
        trackTabs.getTabAt(1).setIcon(R.drawable.nopain);
        trackTabs.getTabAt(2).setIcon(R.drawable.nolungs);
        trackTabs.getTabAt(3).setIcon(R.drawable.noskin);}


        public void changeTime(final String newdate){
            String uid = c_user.getUid();

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("last_entrance");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mDatabase.setValue(newdate);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }





     private void toMain() {
         Intent toHome = new Intent(SymptomTrack.this, MainActivity.class);
         toHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
         startActivity(toHome);
     }


}
