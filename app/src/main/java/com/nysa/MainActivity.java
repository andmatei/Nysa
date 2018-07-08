package com.nysa;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private ProgressDialog lgOutProgress;
    private DatabaseReference nDatabase;
    private DatabaseReference mDatabase;
    private FirebaseUser c_user;
    private TextView helloView;
    private HashMap<String,String> newMap = new HashMap<>();
    private  TextView usernameView;
    private TextView greetingsView;
    private TextView greetings2View;
    private  TextView dateView;
    boolean sameEntryDate;
    String lastEntryDate = new String();
    String JSON_URL = new String();
    private Button graphBtn;
    private Button addSymptom;
    private TextView noSymptom1;
    private TextView noSymptom2;
    private String uid = new String();
    private TextView ScoreTxt;
    private TextView ScoreTxt2;
    private ProgressBar pbar;
    private Tool_bar tool_bar;






    private String nameMonth;
    private JsonArrayRequest request;
    private JSONArray mData;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    NewsRecyclerView adapter;










    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tool_bar = (Tool_bar) findViewById(R.id.tool_bar);

        pbar = (ProgressBar)  findViewById(R.id.progressBar);
        ScoreTxt2 = (TextView) findViewById(R.id.ScoreTxt2);
        ScoreTxt = (TextView) findViewById(R.id.ScoreTxt);
        pbar = (ProgressBar) findViewById(R.id.progressBar);

        addSymptom = (Button) findViewById(R.id.addSymptom);
        addSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SymptomTrack.class);
                startActivity(i);
            }
        });

        noSymptom1 = (TextView) findViewById(R.id.nosymptoms1);
        noSymptom2 = (TextView) findViewById(R.id.nosymptoms2);
        recyclerView = findViewById(R.id.recyclerviewid);
        c_user = FirebaseAuth.getInstance().getCurrentUser();

        nDatabase = FirebaseDatabase.getInstance().getReference().child("JasonURL");
        nDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String url =(String) dataSnapshot.getValue();
                jsonrequest(url);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mAuth = FirebaseAuth.getInstance();

        Typeface fontSegoeSemiBold = Typeface.createFromAsset(getAssets(), "Fonts/seguisb.ttf");
        Typeface fontSegoeStd = Typeface.createFromAsset(getAssets(), "Fonts/Segoe-UI.ttf");
        Typeface fontSegoeSemiLight = Typeface.createFromAsset(getAssets(), "Fonts/segoe-ui-semilight-610.ttf");
        Typeface fontSegoeLight = Typeface.createFromAsset(getAssets(),"Fonts/segoeuil.ttf");
        helloView = (TextView) findViewById(R.id.helloView);
        helloView.setTypeface(fontSegoeSemiBold);

        usernameView = (TextView) findViewById(R.id.usernameView);
        usernameView.setTypeface(fontSegoeSemiLight);

        greetingsView = (TextView) findViewById(R.id.greetingsView);
        greetingsView.setTypeface(fontSegoeSemiLight);

        greetings2View = (TextView) findViewById(R.id.greetings2View);
        greetings2View.setTypeface(fontSegoeLight);

        dateView = (TextView) findViewById(R.id.dateView);
        dateView.setTypeface(fontSegoeLight);



        if(c_user==null)
        {
            sendToLogin();
        }else {
            uid = c_user.getUid();
            nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
            nDatabase.keepSynced(true);
            nDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String getUsername =(String) dataSnapshot.child("username").getValue();
                    usernameView.setText(getUsername + ".");
                    lastEntryDate =(String) dataSnapshot.child("last_entrance").getValue();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    sendToLogin();

                }
            });
        }
        SimpleDateFormat current_day = new SimpleDateFormat("dd");
        String day = current_day.format(new Date());
        SimpleDateFormat current_month = new SimpleDateFormat("MM");
        String month = current_month.format(new Date());

        SimpleDateFormat current_year = new SimpleDateFormat("yyyy");
        String year = current_year.format(new Date());

        dateChanger(month);
        dateView.setText("TODAY | "+day+" "+nameMonth+", "+year);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("last_entrance");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat current_date = new SimpleDateFormat("dd-MM-yyyy");
                final String date = current_date.format(new Date());

                 String  now = (String) dataSnapshot.getValue();

                if(date.equals(now)){
                    noSymptom1.setText("Your symptom score:");
                    noSymptom2.setVisibility(View.GONE);
                    DatabaseReference puncte = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker").child("Score");
                    puncte.keepSynced(true);
                    puncte.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long L = (long)dataSnapshot.getValue();
                            long l = L;
                            String a = "" +l;

                            ScoreTxt.setText(a);
                            pbar.setProgress(Integer.parseInt(ScoreTxt.getText().toString()));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    ScoreTxt.setVisibility(View.VISIBLE);
                    pbar.setVisibility(View.VISIBLE);
                    ScoreTxt2.setVisibility(View.VISIBLE);


                }
                else
                {

                    ScoreTxt.setVisibility(View.GONE);
                    pbar.setVisibility(View.GONE);
                    ScoreTxt2.setVisibility(View.GONE);
                    noSymptom1.setText("You didn't track your symptoms yet");
                    noSymptom2.setVisibility(View.VISIBLE);
                    nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker");
                    nDatabase.keepSynced(true);
                    HashMap<String,String> trackMap = new HashMap<>();
                    trackMap.put("ES","-1");
                    trackMap.put("PS","-1");
                    trackMap.put("SS","-1");
                    trackMap.put("RS1","-1");
                    trackMap.put("RS2","-1");
                    trackMap.put("Score","0");
                    nDatabase.setValue(trackMap);
            }}
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void jsonrequest(final String JSON_URL) {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mData = response;
                setuprecycleview();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setuprecycleview() {
        adapter = new NewsRecyclerView(this, mData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void sendToLogin() {
        Intent startIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(startIntent);
        finish();
    }

    public void dateChanger(String month){
        if(month.equals("01")){
            nameMonth = "January";
        }
        else
        if(month.equals("02")){
            nameMonth = "February";
        }
        else
        if(month.equals("03")){
            nameMonth = "March";
        }
        else
        if(month.equals("04")){
            nameMonth = "April";
        }
        else
        if(month.equals("05")){
            nameMonth = "May";
        }
        else
        if(month.equals("06")){
            nameMonth = "June";
        }
        else
        if(month.equals("07")){
            nameMonth = "July";
        }
        else
        if(month.equals("08")){
            nameMonth = "August";
        }
        else
        if(month.equals("09")){
            nameMonth = "September";
        }
        else
        if(month.equals("10")){
            nameMonth = "October";
        }
        else
        if(month.equals("11")){
            nameMonth = "November";
        }
        else
        if(month.equals("12")){
            nameMonth = "December";
        }

    }
}




