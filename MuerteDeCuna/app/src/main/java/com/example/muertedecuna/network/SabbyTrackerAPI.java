package com.example.muertedecuna.network;

import com.example.muertedecuna.network.jsonBeans.JsonFilters;
import com.example.muertedecuna.network.jsonBeans.JsonLogin;
import com.example.muertedecuna.network.jsonBeans.JsonUser;
import com.example.muertedecuna.network.jsonBeans.JsonValues;
import com.example.muertedecuna.network.jsonBeans.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SabbyTrackerAPI {
    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("users/register")
    Call<JsonUser>createUser(@Body Post post);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("users/login")
    Call<JsonLogin>authUser(@Body Post post);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read")
    Call<ArrayList<ArrayList<JsonValues>>>getValues(@Body JsonFilters jf);
}
