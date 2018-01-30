package com.example.lucasnavarro.githubviewer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.R;
import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.service.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;


public class BuscaUsuarioActivity extends AppCompatActivity {

    TextView loginUsuario;
    Button buttonBuscarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_usuario);

        loginUsuario = findViewById(R.id.editTextNomeUsuario);
        buttonBuscarUsuario = findViewById(R.id.buttonBuscarUsuario);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        final Call<Owner> getOwner = api.GetOwner(loginUsuario.toString());

        buttonBuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwner.enqueue(new Callback<Owner>() {
                    @Override
                    public void onResponse(Call<Owner> call, Response<Owner> response) {
                        if(response.isSuccessful()){
                            Owner owner = response.body();
                            String nomeUsuario = owner.getName();
                            String usuarioAvatarUrl = owner.getAvatar_url();

                            Intent intent = new Intent(BuscaUsuarioActivity.this, UsuarioActivity.class);
                            intent.putExtra("nomeUsuario", nomeUsuario);
                            intent.putExtra("usuarioAvatarUrl", usuarioAvatarUrl);
                            startActivity(intent);

                        } else {
                            Toast.makeText(BuscaUsuarioActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Owner> call, Throwable t) {
                        alertaErrroConexao();
                    }
                });
            }
        });

    }

    private void alertaErrroConexao() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BuscaUsuarioActivity.this);
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
