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

                        new MyAsyncTask().execute();//Quitar esto alv cuando este lo de la base de datos
                        //Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                        //startActivity(intent);
                        //finish();

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

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String urlString = "https://pkoax8g6vf.execute-api.us-east-1.amazonaws.com/Prod/dummyJSON";

                URL url = new URL(urlString);
                String result = downloadUrl(url);

                JSONObject jsonObject = new JSONObject(result);

                measurements = jsonObject.optString("measurements");

                Log.e("ASYNC", "in");

                Log.e("JSON", measurements);
            } catch (MalformedURLException e) {

            } catch (JSONException e) {

            } catch (IOException e) {

            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            Intent intent;
            intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);

            Bundle mBundle = new Bundle();
            mBundle.putString("measurements", measurements);
            /*mBundle.putString("IconId", iconId);
            mBundle.putString("level", level);
            mBundle.putString("id", id);
            mBundle.putString("name", name);
            mBundle.putString("tierSolo", tierSolo);
            mBundle.putString("rankSolo", rankSolo);
            mBundle.putString("tierFlex", tierFlex);
            mBundle.putString("rankFlex", rankFlex);
            mBundle.putString("championId", championId);
            mBundle.putString("championLevel", championLevel);
            mBundle.putString("matchParticipants", matchParticipants.toString());
            mBundle.putString("matches", matches.toString());
            intent.putExtras(mBundle);*/
            startActivity(intent);
            finish();
        }


        private String downloadUrl(URL url) throws IOException {
            InputStream stream = null;
            HttpsURLConnection connection = null;
            String result = null;
            try {
                connection = (HttpsURLConnection) url.openConnection();
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                connection.setReadTimeout(3000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                connection.setConnectTimeout(3000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Open communications link (network traffic occurs here).
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 500.
                    result = readStream(stream);
                }
            } finally {
                // Close Stream and disconnect HTTPS connection.
                if (stream != null) {
                    stream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        private String readStream(InputStream stream) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }
}