package com.example.lucasnavarro.githubviewer.model;
import java.util.List;

/**
 * Created by Lucas Navarro on 28/01/2018.
 */

public class Owner {
    private String name;
    private String avatar_url;
    //public List<Repositorios> repositoriosList;


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
}
