package com.example.lucasnavarro.githubviewer.service;

import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lucas Navarro on 28/01/2018.
 */

public interface API {

    @GET("users/{user}")
    Call<Owner> getUser(@Path("user") String owner);

    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String owner);
}
