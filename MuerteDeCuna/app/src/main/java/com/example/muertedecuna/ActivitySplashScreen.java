package com.example.muertedecuna;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.muertedecuna.beans.User;
import com.example.muertedecuna.beans.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class ActivitySplashScreen extends AppCompatActivity {


    public static final String MY_PREFERENCES= "com.example.muertedecuna.PREFERENCES";

    String measurements, level, id, name, tierSolo, rankSolo, tierFlex, rankFlex, championId, championLevel;
    Boolean islogin = false;
    JSONArray matchParticipants, matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(!isNetworkAvailable()) {
            Toast toast = Toast.makeText(ActivitySplashScreen.this,
                    "Necesitas tener conexión a Internet", Toast.LENGTH_LONG);
            toast.show();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    finishAndRemoveTask();
                    System.exit(0);
                }
            };

            Timer timer = new Timer();
            timer.schedule(task,4000);
        }else{

            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    Intent intent;
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            };

            Timer timer = new Timer();


            timer.schedule(task, 5000);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}