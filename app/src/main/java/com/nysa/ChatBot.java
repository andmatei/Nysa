package com.nysa;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ChatBot extends AppCompatActivity {
    Button btnSpeak;
    Button btnSend;
    EditText editText;
    ImageView imageSpeak, imageSend;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private RecyclerView mMessagesList;
    private final List<Messages> messagesList = new ArrayList<>();
    private LinearLayoutManager mLinearLayout;
    private MessageAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        btnSend =(Button) findViewById(R.id.SubmitBtn);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);

        mAdapter = new MessageAdapter(messagesList);

        mMessagesList = (RecyclerView) findViewById(R.id.messages_list);
        mLinearLayout = new LinearLayoutManager(this);
        mMessagesList.setHasFixedSize(true);
        mMessagesList.setLayoutManager(mLinearLayout);
        mMessagesList.setAdapter(mAdapter);


        editText = (EditText) findViewById(R.id.SubmitEditText);
        imageSpeak = (ImageView) findViewById(R.id.SpeakImage);
        imageSend = (ImageView) findViewById(R.id.SubmitImage);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String message = s.toString().trim();
                if(message.isEmpty()){
                    btnSpeak.setVisibility(View.VISIBLE);
                    imageSpeak.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.GONE);
                    imageSend.setVisibility(View.GONE);
                }
                else{

                    btnSpeak.setVisibility(View.GONE);
                    imageSpeak.setVisibility(View.GONE);
                    btnSend.setVisibility(View.VISIBLE);
                    imageSend.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mes = editText.getText().toString();
                if(!mes.isEmpty()){
                    Messages m = new Messages("user",mes);
                    messagesList.add(m);
                    mAdapter.notifyDataSetChanged();
                    RetrieveFeedTask task = new RetrieveFeedTask();
                    task.execute(mes);
                    editText.setText("");


                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();

            }
        });


    }


    private void promptSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry, your device doesn\\'t support speech input", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String userQuery = result.get(0);

                    Messages m = new Messages("user",userQuery);
                    messagesList.add(m);
                    mAdapter.notifyDataSetChanged();
                    RetrieveFeedTask task = new RetrieveFeedTask();
                    task.execute(userQuery);


                }
                break;
            }

        }
    }


    public String GetText(String query) throws UnsupportedEncodingException {

        String text = "";
        BufferedReader reader = null;
        try {
            URL url = new URL("https://api.api.ai/v1/query?v=20150910");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Authorization", "Bearer 393b0eb1ebd147d2bfe1a2ae07345c27");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonParam = new JSONObject();
            JSONArray queryArray = new JSONArray();
            queryArray.put(query);
            jsonParam.put("query", queryArray);
            jsonParam.put("lang", "en");
            jsonParam.put("sessionId", "1234567890");


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            Log.d("karma", "after conversion is " + jsonParam.toString());
            wr.write(jsonParam.toString());
            wr.flush();
            Log.d("karma", "json is " + jsonParam);

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");
            }

            text = sb.toString();

            JSONObject object1 = new JSONObject(text);
            JSONObject object = object1.getJSONObject("result");
            JSONObject fulfillment = null;
            String speech = null;

            fulfillment = object.getJSONObject("fulfillment");

            speech = fulfillment.optString("speech");

            return speech;


        } catch (Exception ex) {
            Log.d("karma", "exception at last " + ex);
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        return null;
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            String s = null;
            try {

                s = GetText(voids[0]);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.d("karma", "Exception occurred " + e);
            }

            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Messages m = new Messages("bot",s);
            messagesList.add(m);
            mAdapter.notifyDataSetChanged();

        }

    }
}