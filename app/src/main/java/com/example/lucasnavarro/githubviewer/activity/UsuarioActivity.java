package com.example.lucasnavarro.githubviewer.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.githubviewer.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLUE)
                .borderWidthDp(2)
                .oval(true)
                .build();

        Picasso.with(this).load(usuarioAvatarUrl).fit().transform(transformation).into(imageViewUsuario);
    }
}