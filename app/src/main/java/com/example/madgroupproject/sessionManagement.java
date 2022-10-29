package com.example.madgroupproject;


import android.content.Context;
import android.content.SharedPreferences;

public class sessionManagement {

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "key";


    public sessionManagement(Context context) {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user) {

        String username = user.getUsername();

        editor.putString(SESSION_KEY, username).commit();
    }


    public String getSession() {

        return sharedPreferences.getString(SESSION_KEY, "null");
    }

    public void removeSession() {

        editor.putString(SESSION_KEY, "null").commit();

    }

}




