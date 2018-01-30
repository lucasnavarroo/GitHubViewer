package com.example.lucasnavarro.githubviewer.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.githubviewer.R;
import com.squareup.picasso.Picasso;

public class UsuarioActivity extends AppCompatActivity {

    TextView tvNomeUsuario;
    ImageView imageViewUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvNomeUsuario = findViewById(R.id.textViewNomeUsuario);
        imageViewUsuario = findViewById(R.id.imageViewUsuario);

        Bundle extras = getIntent().getExtras();
        String nomeUsuario = extras.getString("nomeUsuario");
        String usuarioAvatarUrl = extras.getString("usuarioAvatarUrl");

        tvNomeUsuario.setText(nomeUsuario);
        Picasso.with(this).load(usuarioAvatarUrl).into(imageViewUsuario);
    }
}