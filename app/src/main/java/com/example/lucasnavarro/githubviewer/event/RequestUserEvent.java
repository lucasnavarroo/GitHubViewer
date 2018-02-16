package com.example.lucasnavarro.githubviewer.event;

import com.example.lucasnavarro.githubviewer.model.Owner;

/**
 * Created by Lucas Navarro on 15/02/2018.
 */

public class RequestUserEvent {
    private Owner owner;

    public RequestUserEvent(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

}
