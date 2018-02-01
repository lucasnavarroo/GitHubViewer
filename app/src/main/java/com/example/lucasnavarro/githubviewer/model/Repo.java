package com.example.lucasnavarro.githubviewer.model;

import java.io.Serializable;

/**
 * Created by Lucas Navarro on 28/01/2018.
 */

public class Repo implements Serializable {
    public String name;
    public String language;

    public String getNameRepo() {
        return name;
    }

    public void setNameRepo(String name) {
        this.name = name;
    }

    public String getLanguageRepo() {
        return language;
    }

    public void setLanguageRepo(String language) {
        this.language = language;
    }
}
