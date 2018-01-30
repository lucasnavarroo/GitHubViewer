package com.example.lucasnavarro.githubviewer.service;

import com.example.lucasnavarro.githubviewer.model.Owner;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lucas Navarro on 28/01/2018.
 */

public interface API {

    @GET("users/{owner}")
    Call<Owner> getUser(@Path("owner") String owner);
}
