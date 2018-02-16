package com.example.lucasnavarro.githubviewer.service;

import com.example.lucasnavarro.githubviewer.model.Repo;

import java.util.List;

/**
 * Created by Lucas Navarro on 06/02/2018.
 */

public interface IRequestRepo {
    void requestRepoDeuCerto(List<Repo> repos);
    void requestRepoDeuErrado();
}
