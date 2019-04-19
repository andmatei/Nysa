package nysa.nysa_20.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

import nysa.nysa_20.R;
import nysa.nysa_20.model.LocationDataKeeper;
import nysa.nysa_20.model.Messages;
import nysa.nysa_20.model.adaptors.ChatBotActiviytMessageAdaptor;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class ChatBotActivity extends AppCompatActivity {
    private EditText submitEditText;
    private ImageView submitImageView, voiceCommandImageView;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private RecyclerView messagesRecycleView;
    private final List<Messages> messagesList = new ArrayList<>();
    private LinearLayoutManager mLinearLayout;
    private ChatBotActiviytMessageAdaptor mAdaptor;
    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        initComponents();
        Toast.makeText(ChatBotActivity.this, LocationDataKeeper.getLatitude()+" "+LocationDataKeeper.getLongitude()+" "+LocationDataKeeper.getLocality()+LocationDataKeeper.getCountry(),Toast.LENGTH_LONG).show();

    }

    private void initComponents() {
        assignComponentReferences();
        prepareComponents();

    }

    private void prepareComponents() {
        mAdaptor = new ChatBotActiviytMessageAdaptor(messagesList);
        mLinearLayout = new LinearLayoutManager(this);
        messagesRecycleView.setHasFixedSize(true);
        messagesRecycleView.setLayoutManager(mLinearLayout);
        messagesRecycleView.setAdapter(mAdaptor);

        prepareSubmitEditText();
        submitImageView.setOnClickListener(ev -> submitImageViewClicked());
        voiceCommandImageView.setOnClickListener(ev -> promptSpeechInput());
        logoImageView.setOnClickListener(ev -> ActivityShiftService.toMainActivity(this));
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

    private void submitImageViewClicked() {

        submitEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            String messageSubmited = submitEditText.getText().toString();
            if(!messageSubmited.isEmpty()){
                Messages message = new Messages("user",messageSubmited);
                messagesList.add(message);
                mAdaptor.notifyDataSetChanged();
                RetrieveFeedTask task = new RetrieveFeedTask();
                task.execute(messageSubmited);
                submitEditText.setText("");
            }
    }


    private void prepareSubmitEditText() {
        submitEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String message = s.toString().trim();
                if(message.isEmpty()){
                    voiceCommandImageView.setVisibility(View.VISIBLE);
                    submitImageView.setVisibility(View.GONE);
                }
                else{
                    voiceCommandImageView.setVisibility(View.GONE);
                    submitImageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void assignComponentReferences() {
        submitEditText = findViewById(R.id.submitEditText);
        submitImageView = findViewById(R.id.submitImageView);
        voiceCommandImageView = findViewById(R.id.voiceCommandImageView);
        messagesRecycleView = findViewById(R.id.messagesRecycleView);
        logoImageView = findViewById(R.id.logoChatBot);


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
                    mAdaptor.notifyDataSetChanged();
                    RetrieveFeedTask task = new RetrieveFeedTask();
                    task.execute(userQuery);


                }
                break;
            }

        }
    }


    public String getText(String query) throws UnsupportedEncodingException {

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

                s = getText(voids[0]);


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
            mAdaptor.notifyDataSetChanged();

        }

    }
}
