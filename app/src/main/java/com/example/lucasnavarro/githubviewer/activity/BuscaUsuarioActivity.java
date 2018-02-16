package com.example.lucasnavarro.githubviewer.activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.lucasnavarro.githubviewer.R;

import com.example.lucasnavarro.githubviewer.event.RequestUserEvent;
import com.example.lucasnavarro.githubviewer.model.Owner;
import com.example.lucasnavarro.githubviewer.service.GitHubService;
import com.example.lucasnavarro.githubviewer.service.IRequestUser;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;


import static android.support.design.widget.Snackbar.LENGTH_SHORT;

@EActivity(R.layout.activity_busca_usuario)
public class BuscaUsuarioActivity extends BaseActivity implements IRequestUser{

    @ViewById
    protected EditText editTextLoginUsuario;

    @ViewById
    protected Button buttonBuscarUsuario;

    @ViewById
    protected CoordinatorLayout coordinatorLayout;

    @ViewById
    protected ProgressBar progressBar;

    @AfterViews
    void afterViews(){
        progressBar.setVisibility(View.GONE);
    }

    @Click
    protected void buttonBuscarUsuario() {
        progressBar.setVisibility(View.VISIBLE);
        requestUserService();
    }

    public void requestUserService(){
        GitHubService gitHubService = new GitHubService();
        gitHubService.requestUser(BuscaUsuarioActivity.this, String.valueOf(editTextLoginUsuario.getText()), this);
    }

    @Override
    public void requestUserDeuCerto(Owner owner) {
        preparaIntentUsuarioActivity(owner);
    }

    private void preparaIntentUsuarioActivity(Owner owner) {
        String nomeUsuario = owner.getName();
        String usuarioAvatarUrl = owner.getAvatar_url();
        String nomeLogin = owner.getLogin();

        Intent intent = new Intent(this, UsuarioActivity_.class);
        intent.putExtra("nomeUsuario", nomeUsuario);
        intent.putExtra("usuarioAvatarUrl", usuarioAvatarUrl);
        intent.putExtra("nomeLogin", nomeLogin);

        startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void requestUserDeuErrado() {
        Snackbar snackbar = Snackbar.make(editTextLoginUsuario, "Usuário não encontrado.", LENGTH_SHORT);
        snackbar.show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void alertaErroConexao() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(BuscaUsuarioActivity.this);
            builder.setTitle("Erro de conexão.");
            builder.setMessage("Por favor, verifique sua conexão com a internet.");
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
            progressBar.setVisibility(View.GONE);
    }

    @Subscribe
    public void onEvent(RequestUserEvent event) {
        preparaIntentUsuarioActivity(event.getOwner());
    }
}

