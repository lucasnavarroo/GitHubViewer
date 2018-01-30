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
import com.example.lucasnavarro.githubviewer.service.GitHubService;

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

        GitHubService gitHubService = new GitHubService();
        gitHubService.requestUser("lerolero", this);

    }

}
