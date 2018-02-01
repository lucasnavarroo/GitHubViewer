package com.example.lucasnavarro.githubviewer.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.githubviewer.R;
import com.example.lucasnavarro.githubviewer.adapter.AdapterRepositorios;
import com.example.lucasnavarro.githubviewer.model.Repo;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.Serializable;
import java.util.List;

public class UsuarioActivity extends AppCompatActivity {

    TextView tvNomeUsuario;
    ImageView imageViewUsuario;
    RecyclerView recyclerViewRepositorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvNomeUsuario = findViewById(R.id.textViewNomeUsuario);
        imageViewUsuario = findViewById(R.id.imageViewUsuario);
        recyclerViewRepositorios = findViewById(R.id.recyclerViewRepositorios);

        recyclerViewRepositorios.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewRepositorios.setLayoutManager(linearLayoutManager);

        Bundle extras = getIntent().getExtras();
        String nomeUsuario = extras.getString("nomeUsuario");
        String usuarioAvatarUrl = extras.getString("usuarioAvatarUrl");

        AdapterRepositorios adapterRepositorios = new AdapterRepositorios();
        recyclerViewRepositorios.setAdapter(adapterRepositorios);

        tvNomeUsuario.setText(nomeUsuario);
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLUE)
                .borderWidthDp(2)
                .oval(true)
                .build();

        Picasso.with(this).load(usuarioAvatarUrl).fit().transform(transformation).into(imageViewUsuario);

    }
}