package com.example.muertedecuna.network;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.lifecycle.MutableLiveData;

import com.example.muertedecuna.json.JsonPlaceHolderApi;
import com.example.muertedecuna.network.jsonBeans.JsonTemp;
import com.example.muertedecuna.network.jsonBeans.JsonUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;

    public static Repository getInstance(){
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    private SabbyTrackerAPI api;
    private ProgressDialog pd;

    public Repository(){
        api = RetrofitService.cteateService(SabbyTrackerAPI.class);
    }

    public MutableLiveData<JsonTemp> getTemp(JsonUser user, Activity  context){
        MutableLiveData<JsonTemp> newsData = new MutableLiveData<>();

        ProgressDialog pd = new ProgressDialog(context);

        pd.setMax(100);
        pd.setMessage("Estas siendo registrado, espera un momento por favor...");
        pd.setTitle("Registrando Usuario");

        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        pd.show();

        api.createUser(user).enqueue(new Callback<JsonUser>() {
            @Override
            public void onResponse(Call<JsonUser> call,
                                   Response<JsonUser> response) {
                if (response.isSuccessful()){
                    //newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonUser> call, Throwable t) {
                api.setValue(null);
            }
        });
        return newsData;
    }
}