package com.example.muertedecuna.json;

import com.example.muertedecuna.network.jsonBeans.JsonArray;
import com.example.muertedecuna.network.jsonBeans.JsonFilters;
import com.example.muertedecuna.network.jsonBeans.JsonLogin;
import com.example.muertedecuna.network.jsonBeans.JsonValues;
import com.example.muertedecuna.network.jsonBeans.JsonValuesArray;
import com.example.muertedecuna.network.jsonBeans.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("users/register")
    Call<Post>createUser(@Body Post post);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("users/login")
    Call<JsonLogin>authUser(@Body Post post);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read")
    Call<ArrayList<ArrayList<JsonValues>>>getValues(@Body JsonFilters jf);
}
