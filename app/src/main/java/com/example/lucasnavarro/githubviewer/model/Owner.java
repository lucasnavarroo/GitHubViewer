package com.example.lucasnavarro.githubviewer.model;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucas Navarro on 28/01/2018.
 */

public class Owner implements Serializable {
    private String name;
    private String avatar_url;
    private List<Repo> repos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
