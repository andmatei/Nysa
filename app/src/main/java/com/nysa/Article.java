package com.nysa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Article extends AppCompatActivity {

    private JSONArray mData;
    private ArticleRecyclerView adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mRecyclerView = (RecyclerView) findViewById(R.id.articleRecyclerView);
        if(getIntent().hasExtra("article_data")) {
            try {
                mData = new JSONArray(getIntent().getStringExtra("article_data"));
                adapter = new ArticleRecyclerView(this, mData);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void testData() throws JSONException {
        String data = "[{\"type\":\"heading\", \"title\":\"Lorem ipsum dolor sit amet\", \"author\":\"Mr. Ambrosia, from Mr.Ambrosia Clinic\", \"posted\":\"4 hours ago\", \"tagname\":\"News\"}]";
        mData = new JSONArray(data);
        Log.d("Article class", data);
    }
}
