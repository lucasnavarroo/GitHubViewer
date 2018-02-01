package com.example.lucasnavarro.githubviewer.service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity;
import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.model.Repo;


import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Lucas Navarro on 30/01/2018.
 */

public class GitHubService extends BaseGitHubService {

    public void requestUser(final String nomeLogin, final Context context) {
        getGithubAPI().getUser(nomeLogin).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                if(response.isSuccessful()){
                    Owner owner = response.body();
                    String nomeUsuario = owner.getName();
                    String usuarioAvatarUrl = owner.getAvatar_url();
                    List<Repo> repos = owner.getRepos();

                    Intent intent = new Intent(context, UsuarioActivity.class);
                    intent.putExtra("nomeUsuario", nomeUsuario);
                    intent.putExtra("usuarioAvatarUrl", usuarioAvatarUrl);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("repos", (Serializable) repos);
                    intent.putExtras(bundle);

                    context.startActivity(intent);
                }

                else {
                    Toast.makeText(context, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                alertaErrroConexao(context);
            }
        });
    }

    private void alertaErrroConexao(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Usuário não encontrado.");
        builder.setMessage("Por favor verifique a sua conexão com a internet.");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}

