package com.example.muertedecuna.network;

import com.example.muertedecuna.network.jsonBeans.JsonUser;
import com.example.muertedecuna.network.jsonBeans.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SabbyTrackerAPI {
    @POST("users/register")
    Call<JsonUser> createUser(@Body JsonUser user);

    void setValue(JsonUser o);
}
