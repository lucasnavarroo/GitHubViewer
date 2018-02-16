package com.example.lucasnavarro.githubviewer.service;

import com.example.lucasnavarro.githubviewer.model.Owner;

/**
 * Created by Lucas Navarro on 06/02/2018.
 */

public interface IRequestUser {
    void requestUserDeuCerto(Owner owner);
    void requestUserDeuErrado();
    void alertaErroConexao();
}
