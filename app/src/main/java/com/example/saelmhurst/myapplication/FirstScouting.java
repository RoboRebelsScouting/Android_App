package com.example.saelmhurst.myapplication;

import android.app.Application;

/**
 * Created by saelmhurst on 2/3/16.
 */
public class FirstScouting extends Application {

    public static InfoStorage gameInfoStorage = new InfoStorage();

    private static FirstScouting singleton;

    public  static FirstScouting getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
