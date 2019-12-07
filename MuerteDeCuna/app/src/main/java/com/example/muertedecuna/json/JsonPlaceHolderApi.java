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
    @POST("data/read/temp")
    Call<ArrayList<ArrayList<JsonValues>>>getValuesTemp(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read/sound")
    Call<ArrayList<ArrayList<JsonValues>>>getValuesSound(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read/pulse")
    Call<ArrayList<ArrayList<JsonValues>>>getValuesPulse(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read/position-x")
    Call<ArrayList<ArrayList<JsonValues>>>getValuesPositionX(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read/position-y")
    Call<ArrayList<ArrayList<JsonValues>>>getValuesPositionY(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @POST("data/read")
    Call<ArrayList<ArrayList<JsonValues>>>getValues(@Body JsonFilters jf);

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @GET("data/read/temp")
    Call<JsonValues>getCurrentTemp();

    @Headers("x-api-key: BhPMOkeApxa0UKb6kcvId5iNBPmVSGLZ93dzunmN")
    @GET("data/read/pulse")
    Call<JsonValues>getCurrentPulse();
}
