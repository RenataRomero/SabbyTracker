package com.example.muertedecuna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.example.muertedecuna.network.jsonBeans.JsonArray;
import com.example.muertedecuna.network.jsonBeans.JsonFilters;
import com.example.muertedecuna.network.jsonBeans.JsonLogin;
import com.example.muertedecuna.network.jsonBeans.JsonValues;
import com.example.muertedecuna.network.jsonBeans.JsonValuesArray;
import com.example.muertedecuna.network.jsonBeans.Post;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityHistoricSplashScreen extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi;
    JsonValuesArray jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_splash_screen);

        Intent intent = getIntent();
        String startDay = intent.getStringExtra("startDay");
        String startMonth = intent.getStringExtra("startMonth");
        String startYear = intent.getStringExtra("startYear");
        String endDay = intent.getStringExtra("endDay");
        String endMonth = intent.getStringExtra("endMonth");
        String endYear = intent.getStringExtra("endYear");
        String type = intent.getStringExtra("type");

        Log.e("TYPE",type);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://58yzesjje0.execute-api.us-east-1.amazonaws.com/test_beta_post/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getVariables(startDay, startMonth, startYear, endDay, endMonth, endYear, type);

    }

    public void getVariables(String startDay, String startMonth, String startYear, String endDay, String endMonth, String endYear, String type){

        JsonFilters filters = new JsonFilters(startDay,startMonth, startYear, endDay, endMonth, endYear, type);

        Log.e("TYPE",filters.toString());
        Call<ArrayList<ArrayList<JsonValues>>> call = jsonPlaceHolderApi.getValues(filters);


        call.enqueue(new Callback<ArrayList<ArrayList<JsonValues>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<JsonValues>>> call, Response<ArrayList<ArrayList<JsonValues>>> response) {

                Intent intent = new Intent(ActivityHistoricSplashScreen.this, ActivityChartsHistoric.class);
                Log.e("VALUES",response.body().get(0).get(0).getVariable());

                intent.putExtra("values", response.body());

                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<JsonValues>>> call, Throwable t) {
                Log.e("POSTERROR", t.toString());
            }
        });
    }

}
