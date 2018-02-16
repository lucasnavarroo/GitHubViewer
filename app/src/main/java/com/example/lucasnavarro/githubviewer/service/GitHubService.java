package com.example.lucasnavarro.githubviewer.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity;
import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity_;
import com.example.lucasnavarro.githubviewer.event.AlertaErroConexaoEvent;
import com.example.lucasnavarro.githubviewer.event.RequestRepoEvent;
import com.example.lucasnavarro.githubviewer.event.RequestRepoFailedEvent;
import com.example.lucasnavarro.githubviewer.event.RequestUserEvent;
import com.example.lucasnavarro.githubviewer.event.RequestUserFailedEvent;
import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.model.Repo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubService extends BaseGitHubService {

    public void requestUser(String nomeLogin) {
        getGithubAPI().getUser(nomeLogin).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                if(response.isSuccessful()) {
                    Owner owner = response.body();
                    RequestUserEvent requestUserEvent = new RequestUserEvent(owner);
                    EventBus.getDefault().post(requestUserEvent);
                }
                else {
                    RequestUserFailedEvent requestUserFailedEvent = new RequestUserFailedEvent();
                    EventBus.getDefault().post(requestUserFailedEvent);
                }
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                AlertaErroConexaoEvent alertaErroConexaoEvent = new AlertaErroConexaoEvent();
                EventBus.getDefault().post(alertaErroConexaoEvent);
            }
        });
    }

    public void requestRepo(final Context context, String nomeLogin) {
        getGithubAPI().getRepos(nomeLogin).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    RequestRepoEvent requestRepoEvent = new RequestRepoEvent(repos);
                    EventBus.getDefault().post(requestRepoEvent);
                }
                else {
                      EventBus.getDefault().post(new RequestRepoFailedEvent());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(context, "Erro conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

