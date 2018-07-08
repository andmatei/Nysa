package com.nysa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;

public class Tool_bar extends ConstraintLayout {
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    public ImageButton HomeBtn;
    public ImageButton PersonBtn;
    public ImageButton AddBtn;
    public ImageButton LibraryBtn;
    public ImageButton GraphBtn;

    public Tool_bar(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        context = mContext;
        activity = (Activity) context;
        Inflate();
        BindViews();
        ToolBarSetup();
    }

    private void Inflate() {
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.tool_bar, this, true);
    }

    private void BindViews() {
        HomeBtn = (ImageButton) findViewById(R.id.homeBtn);
        PersonBtn = (ImageButton) findViewById(R.id.personBtn);
        AddBtn = (ImageButton) findViewById(R.id.addBtn);
        LibraryBtn = (ImageButton) findViewById(R.id.libraryBtn);
        GraphBtn = (ImageButton) findViewById(R.id.graphBtn);
    }

    private void ToolBarSetup (){
        if(activity instanceof MainActivity) {
            HomeBtn.setImageResource(R.drawable.home);
        } else if(activity instanceof  Library) {
            LibraryBtn.setImageResource(R.drawable.menu);
        } else if(activity instanceof  AccountSettings) {
            PersonBtn.setImageResource(R.drawable.person);
        } else{
            GraphBtn.setImageResource(R.drawable.graph);
        }
        if(!(activity instanceof MainActivity)) {
            HomeBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                }
            });}
        if(!(activity instanceof AccountSettings)) {
            PersonBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, AccountSettings.class);
                    context.startActivity(i);
                }
            });}
        if(!(activity instanceof Library)) {
            LibraryBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Library.class);
                    context.startActivity(i);
                }
            });}
        if(!(activity instanceof SymptomTrack)) {
            GraphBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, SymptomTrack.class);
                    context.startActivity(i);
                }
            });}
        if(!(activity instanceof ChatBot)) {
            AddBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatBot.class);
                    context.startActivity(i);
                }
            });}
    }
}
