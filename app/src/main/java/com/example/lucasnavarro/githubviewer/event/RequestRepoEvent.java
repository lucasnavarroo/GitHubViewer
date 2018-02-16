package com.example.lucasnavarro.githubviewer.event;

import com.example.lucasnavarro.githubviewer.model.Repo;

import java.util.List;

/**
 * Created by Lucas Navarro on 16/02/2018.
 */

public class RequestRepoEvent {
    private List<Repo> repos;

    public RequestRepoEvent(List<Repo> repos) {
        this.repos = repos;
    }

    public List<Repo> getRepos() {
        return repos;
    }

}
