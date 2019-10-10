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
import com.facebook.AccessToken;


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


    String iconId, level, id, name, tierSolo, rankSolo, tierFlex, rankFlex, championId, championLevel;
    Boolean islogin = false;
    JSONArray matchParticipants, matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*if(!isNetworkAvailable()) {
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
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadPreferences();
                islogin =  user.isLogged();
                if(user.isLogged()) {
                    new MyAsyncTask().execute();
                } else {
                    new MyAsyncTask().execute();//Quitar esto alv cuando este lo de la base de datos
                    //Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    //startActivity(intent);
                    //finish();
                }
            }
        };

        Timer timer = new Timer();


        timer.schedule(task, 5000);*/
        Intent intent;
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(!(islogin || isLoggedIn)) {
            intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
        } else {
            intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
        }

        startActivity(intent);
        finish();

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

                String key = Utils.RIOT_KEY;
                String urlProfile = "https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ "Reius" + "?api_key=" + key;

                URL url = new URL(urlProfile);
                String result = downloadUrl(url);

                JSONObject jsonObject = new JSONObject(result);

                iconId = jsonObject.optString("profileIconId");
                level = jsonObject.optString("summonerLevel");
                id = jsonObject.optString("id");
                name = jsonObject.optString("name");
                String accountId = jsonObject.optString("accountId");

                String urlQ = "https://la1.api.riotgames.com/lol/league/v4/positions/by-summoner/" + id + "?api_key=" + key;

                URL urlQueue = new URL(urlQ);
                String resultQ = downloadUrl(urlQueue);

                JSONArray jsonArray = new JSONArray(resultQ);

                Log.e("RANKS", resultQ);

                if(jsonArray.length() == 0){

                    tierSolo = "UNRANKED";
                    rankSolo = "UNRANKED";

                    tierFlex = "UNRANKED";
                    rankFlex = "UNRANKED";

                }else if (jsonArray.length() == 1){

                    JSONObject jsonObjectQSolo = jsonArray.getJSONObject(0);

                    Log.e("RANKS", jsonObjectQSolo.toString());

                    tierSolo = jsonObjectQSolo.optString("tier");
                    rankSolo = jsonObjectQSolo.optString("rank");

                    tierFlex = "UNRANKED";
                    rankFlex = "UNRANKED";

                }else if(jsonArray.length() > 1){

                    JSONObject jsonObjectQSolo = jsonArray.getJSONObject(0);

                    tierSolo = jsonObjectQSolo.optString("tier");
                    rankSolo = jsonObjectQSolo.optString("rank");

                    JSONObject jsonObjectQFlex = jsonArray.getJSONObject(1);

                    tierFlex = jsonObjectQFlex.optString("tier");
                    rankFlex = jsonObjectQFlex.optString("rank");

                }

                String urlM = "https://la1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/" + id + "?api_key=" + key;

                URL urlMasteries = new URL(urlM);
                String resultM = downloadUrl(urlMasteries);

                JSONArray jsonArrayMasteries = new JSONArray(resultM);
                JSONObject jsonObjectMastrie = jsonArrayMasteries.getJSONObject(0);

                championId = jsonObjectMastrie.optString("championId");
                championLevel = jsonObjectMastrie.optString("championLevel");

                String urlMatches = "https://la1.api.riotgames.com/lol/match/v4/matchlists/by-account/"+ accountId +"?endIndex=20&api_key=" + key;

                Log.e("Matches", urlMatches);

                URL urlgetMatches = new URL(urlMatches);
                String resultMatches = downloadUrl(urlgetMatches);

                Log.e("Matches", resultMatches);

                JSONObject jsonObjectMatches = new JSONObject(resultMatches);
                JSONArray jsonArrayMatches = jsonObjectMatches.getJSONArray("matches");

                Log.e("Matches", jsonArrayMatches.toString());

                matchParticipants = new JSONArray();

                matches = jsonArrayMatches;


                for(int i = 0; i < jsonArrayMatches.length(); i++){

                    JSONObject temp = jsonArrayMatches.getJSONObject(i);
                    String urlMatch = "https://la1.api.riotgames.com/lol/match/v4/matches/"+ temp.optString("gameId")+"?api_key=" + key;

                    URL urlGetMatch = new URL(urlMatch);
                    String tempResult = downloadUrl(urlGetMatch);

                    //Log.e("Matches", tempResult);

                    JSONObject jsonObjectParticipantId = new JSONObject(tempResult);
                    JSONArray jsonArrayParticipants = jsonObjectParticipantId.getJSONArray("participantIdentities");

                    String playerId = "";

                    for(int j = 0; j < jsonArrayParticipants.length(); j++){

                        JSONObject tempP = jsonArrayParticipants.getJSONObject(j);
                        JSONObject player = tempP.getJSONObject("player");


                        if(player.optString("summonerId").equals(id))
                            playerId = tempP.optString("participantId");

                    }

                    JSONArray participants = jsonObjectParticipantId.getJSONArray("participants");

                    for(int k = 0; k < participants.length(); k++){

                        JSONObject currentParticipant = participants.getJSONObject(k);

                        if(currentParticipant.optString("participantId").equals(playerId))
                            matchParticipants.put(currentParticipant);

                    }

                }

            } catch (MalformedURLException e) {

            } catch (JSONException e) {

            } catch (IOException e) {

            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            Intent intent;
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            if(!(islogin || isLoggedIn)) {
                intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
            } else {
                intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
            }
            /*Bundle mBundle = new Bundle();
            mBundle.putString("IconId", iconId);
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