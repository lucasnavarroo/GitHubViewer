package com.example.lucasnavarro.githubviewer.service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.activity.BuscaUsuarioActivity;
import com.example.lucasnavarro.githubviewer.activity.UsuarioActivity;
import com.example.lucasnavarro.githubviewer.model.Owner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Lucas Navarro on 30/01/2018.
 */

public class GitHubService extends BaseGitHubService {

    public void requestUser(String username, final Context context) {
        getGithubAPI().getUser(username).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                if(response.isSuccessful()){
                    Owner owner = response.body();
                    String nomeUsuario = owner.getName();
                    String usuarioAvatarUrl = owner.getAvatar_url();

                    Intent intent = new Intent(context, UsuarioActivity.class);
                    intent.putExtra("nomeUsuario", nomeUsuario);
                    intent.putExtra("usuarioAvatarUrl", usuarioAvatarUrl);
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
        builder.setMessage("Por favor verifique sua conexão com a internet.");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}

