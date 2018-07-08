package com.nysa;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Vlad on 27/05/2018 0027.
 */

public class NysaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
