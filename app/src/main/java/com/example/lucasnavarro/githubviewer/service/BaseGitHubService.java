package com.example.lucasnavarro.githubviewer.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas Navarro on 30/01/2018.
 */

public class BaseGitHubService {

    private final String BASE_URL = "https://api.github.com/";

    protected API getGithubAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(API.class);
    }
}
