package com.example.lucasnavarro.githubviewer.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucasnavarro.githubviewer.R;
import com.example.lucasnavarro.githubviewer.adapter.AdapterRepositorios;
import com.example.lucasnavarro.githubviewer.event.RequestRepoEvent;
import com.example.lucasnavarro.githubviewer.event.RequestRepoFailedEvent;
import com.example.lucasnavarro.githubviewer.model.Repo;
import com.example.lucasnavarro.githubviewer.service.GitHubService;
import com.example.lucasnavarro.githubviewer.service.IRequestRepo;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@EActivity(R.layout.activity_usuario)
public class UsuarioActivity extends BaseActivity{

    @ViewById
    protected TextView textViewNomeUsuario;

    @ViewById
    protected ImageView imageViewUsuario;

    @ViewById
    protected RecyclerView recyclerViewRepositorios;

    @ViewById
    protected ProgressBar progressBarRepo;

    @ViewById
    protected Button buttonVoltar;

    @AfterViews
    void afterViews(){

        putRecyclerView();

        Bundle extras = getIntent().getExtras();
        String nomeUsuario = extras.getString("nomeUsuario");
        String usuarioAvatarUrl = extras.getString("usuarioAvatarUrl");
        String nomeLogin = extras.getString("nomeLogin");

        textViewNomeUsuario.setText(nomeUsuario);

        GitHubService gitHubService = new GitHubService();
        gitHubService.requestRepo(UsuarioActivity.this, nomeLogin);

        progressBarRepo.setVisibility(View.VISIBLE);

        putUserImage(usuarioAvatarUrl);
    }

    public void putUserImage(String usuarioAvatarUrl) {
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLUE)
                .borderWidthDp(2)
                .oval(true)
                .build();

        Picasso.with(this).load(usuarioAvatarUrl).transform(transformation).into(imageViewUsuario);
    }

    public void putRecyclerView() {
        recyclerViewRepositorios.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewRepositorios.setLayoutManager(linearLayoutManager);
    }

    public void preparaRepositorios(List<Repo> repos) {
        AdapterRepositorios adapterRepositorios = new AdapterRepositorios(repos);
        recyclerViewRepositorios.setAdapter(adapterRepositorios);
        progressBarRepo.setVisibility(View.GONE);
    }

    public void toastRepositorioVazio() {
        Toast.makeText(this, "Repositório vazio", Toast.LENGTH_SHORT).show();
        progressBarRepo.setVisibility(View.GONE);
    }

    @Click
    protected void buttonVoltar(){
        finish();
    }

    @Subscribe
    public void onEvent(RequestRepoEvent event) { preparaRepositorios(event.getRepos()); }

    @Subscribe
    public void onEvent(RequestRepoFailedEvent event) { toastRepositorioVazio(); }
}