package com.example.lucasnavarro.githubviewer.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucasnavarro.githubviewer.R;

import com.example.lucasnavarro.githubviewer.service.GitHubService;


public class BuscaUsuarioActivity extends AppCompatActivity {

    EditText loginUsuario;
    Button buttonBuscarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_usuario);

        loginUsuario = findViewById(R.id.editTextLoginUsuario);
        buttonBuscarUsuario = findViewById(R.id.buttonBuscarUsuario);

        buttonBuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubService gitHubService = new GitHubService();
                gitHubService.requestUser(String.valueOf(loginUsuario.getText()), BuscaUsuarioActivity.this);
            }
        });

    }

}
