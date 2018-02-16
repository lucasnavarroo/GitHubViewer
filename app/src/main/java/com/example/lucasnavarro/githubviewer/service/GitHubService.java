package com.example.lucasnavarro.githubviewer.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity;
import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity_;
import com.example.lucasnavarro.githubviewer.event.RequestUserEvent;
import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.model.Repo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubService extends BaseGitHubService {

    public void requestUser(final Context context, String nomeLogin, final IRequestUser callbackIRequestUser) {
        getGithubAPI().getUser(nomeLogin).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                if(response.isSuccessful()) {
                    Owner owner = response.body();
                    RequestUserEvent requestUserEvent = new RequestUserEvent(owner);
                    EventBus.getDefault().post(requestUserEvent);
                    //callbackIRequestUser.requestUserDeuCerto(owner);
                }
                else {
                  //  callbackIRequestUser.requestUserDeuErrado();
                }
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                //callbackIRequestUser.alertaErroConexao();
            }
        });
    }

    public void requestRepo(final Context context, String nomeLogin, final IRequestRepo callbackIRequestRepo) {
        getGithubAPI().getRepos(nomeLogin).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    callbackIRequestRepo.requestRepoDeuCerto(repos);
                }
                else {
                    callbackIRequestRepo.requestRepoDeuErrado();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(context, "Erro conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

