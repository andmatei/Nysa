package com.nysa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;


public class NewsActivity extends AppCompatActivity {

     String title;
     String description;
     String tagname;
     String autor;
     String subtitle;
     String paragraphM;
     String paragraphS;
     String citat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

       title= getIntent().getExtras().getString("news_title");
        description= getIntent().getExtras().getString("news_description");
        tagname= getIntent().getExtras().getString("news_tag");
        autor= getIntent().getExtras().getString("news_autor");
        subtitle= getIntent().getExtras().getString("news_subtitle");
        citat= getIntent().getExtras().getString("news_citat");
        paragraphM= getIntent().getExtras().getString("news_paragraphM");
        paragraphS= getIntent().getExtras().getString("news_paragraphS");


        TextView titleTx = (TextView) findViewById(R.id.TitleNews);
        titleTx.setText(title);

        TextView autorTx = (TextView) findViewById(R.id.AutorNews);
        autorTx.setText(autor);

        TextView tagTx = (TextView) findViewById(R.id.TagnameNews);
        tagTx.setText(tagname);


        TextView subtitleTx = (TextView) findViewById(R.id.Subtitlu);
        subtitleTx.setText(subtitle);
        if(subtitleTx.getText().toString().isEmpty()){
            subtitleTx.setVisibility(View.GONE);
            Space spc = (Space) findViewById(R.id.spatiuSubtitlu1);
            spc.setVisibility(View.GONE);
        }
        else {
            subtitleTx.setVisibility(View.VISIBLE);
            Space spc = (Space) findViewById(R.id.spatiuSubtitlu1);
            spc.setVisibility(View.VISIBLE);
        }
        TextView paragraphm = (TextView) findViewById(R.id.ParagrafM);
        paragraphm.setText(paragraphM);
        if(paragraphm.getText().toString().isEmpty()){
            paragraphm.setVisibility(View.GONE);
            Space spc = (Space) findViewById(R.id.spatiuParagrafM1);
            spc.setVisibility(View.GONE);
        }
        else {
            paragraphm.setVisibility(View.VISIBLE);
            Space spc = (Space) findViewById(R.id.spatiuParagrafM1);
            spc.setVisibility(View.VISIBLE);

        }

        TextView paragraphs = (TextView) findViewById(R.id.ParagrafS);
        paragraphs.setText(paragraphS);
        if(paragraphs.getText().toString().isEmpty()){
            paragraphs.setVisibility(View.GONE);
            Space spc = (Space) findViewById(R.id.spatiuParagraf11);
            spc.setVisibility(View.GONE);
        }
        else {
            paragraphs.setVisibility(View.VISIBLE);
            Space spc = (Space) findViewById(R.id.spatiuParagraf11);
            spc.setVisibility(View.VISIBLE);

        }
        TextView citats = (TextView) findViewById(R.id.citat1News);
       citats.setText(citat);
        if(citats.getText().toString().isEmpty()){
            citats.setVisibility(View.GONE);
            Space spc = (Space) findViewById(R.id.spatiuCitat1);
            spc.setVisibility(View.GONE);
        }
        else
            citats.setVisibility(View.VISIBLE);
        Space spc = (Space) findViewById(R.id.spatiuCitat1);
        spc.setVisibility(View.VISIBLE);
    }
}
