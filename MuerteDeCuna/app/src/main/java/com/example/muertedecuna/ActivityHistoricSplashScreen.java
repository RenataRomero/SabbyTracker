package com.example.muertedecuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.example.muertedecuna.network.jsonBeans.JsonFilters;
import com.example.muertedecuna.network.jsonBeans.JsonValues;
import com.example.muertedecuna.network.jsonBeans.JsonValuesArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityHistoricSplashScreen extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi;
    String type, startDay, startMonth, startYear, endDay, endMonth, endYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_splash_screen);

        Intent intent = getIntent();
        startDay = intent.getStringExtra("startDay");
        startMonth = intent.getStringExtra("startMonth");
        startYear = intent.getStringExtra("startYear");
        endDay = intent.getStringExtra("endDay");
        endMonth = intent.getStringExtra("endMonth");
        endYear = intent.getStringExtra("endYear");
        type = intent.getStringExtra("type");

        Log.e("CHARTDATE", startDay);
        Log.e("CHARTDATE", startMonth);
        Log.e("CHARTDATE", startYear);
        Log.e("CHARTDATE", endDay);
        Log.e("CHARTDATE", endMonth);
        Log.e("CHARTDATE", endMonth);
        Log.e("CHARTDATE", type);


        Log.e("TYPE",type);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://58yzesjje0.execute-api.us-east-1.amazonaws.com/beta/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getVariables();

    }

    public void getVariables(){

        JsonFilters filters = new JsonFilters(startDay,startMonth, startYear);

        Log.e("TYPE",filters.toString());
        Call<ArrayList<ArrayList<JsonValues>>> call = jsonPlaceHolderApi.getValues(filters);

        switch (type){
            case "temp":
                call = jsonPlaceHolderApi.getValuesTemp(filters);
                break;
            case "sound":
                call = jsonPlaceHolderApi.getValuesSound(filters);
                break;
            case "pulse":
                call = jsonPlaceHolderApi.getValuesPulse(filters);
                break;
            case "position":
                call = jsonPlaceHolderApi.getValuesPositionX(filters);
                break;
        }


        call.enqueue(new Callback<ArrayList<ArrayList<JsonValues>>>() {

            @Override
            public void onResponse(Call<ArrayList<ArrayList<JsonValues>>> call, Response<ArrayList<ArrayList<JsonValues>>> response) {

                Intent intent;

                if(endDay.equals("0")){
                    Log.e("VALUESINTENT", "ENTRO AL IF");
                    intent = new Intent(ActivityHistoricSplashScreen.this, Chart.class);

                    intent.putExtra("values", response.body());
                    intent.putExtra("type",type);
                    intent.putExtra("startDay",startDay);
                    intent.putExtra("startMonth",startMonth);
                    intent.putExtra("startYear",startYear);
                    intent.putExtra("endDay",endDay);
                    intent.putExtra("endMonth",endMonth);
                    intent.putExtra("endYear",endYear);

                    startActivity(intent);
                    Log.e("VALUESINTENT", "LLEGO AQUI!!!");
                    finish();

                }else{
                    Log.e("VALUESINTENT", "ENTRO AL ELSE");
                    intent = new Intent(ActivityHistoricSplashScreen.this, ActivityHistoricCharts.class);
                    intent.putExtra("type",type);
                    intent.putExtra("startDay",startDay);
                    intent.putExtra("startMonth",startMonth);
                    intent.putExtra("startYear",startYear);
                    intent.putExtra("endDay",endDay);
                    intent.putExtra("endMonth",endMonth);
                    intent.putExtra("endYear",endYear);
                    startActivity(intent);
                    Log.e("VALUESINTENT", "LLEGO AQUI!!!");
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<JsonValues>>> call, Throwable t) {
                Log.e("VALUESERROR", t.toString());
            }
        });
    }

}
