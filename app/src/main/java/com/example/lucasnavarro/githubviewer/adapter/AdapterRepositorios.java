package com.example.lucasnavarro.githubviewer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.githubviewer.model.Repo;

import java.util.List;

/**
 * Created by Lucas Navarro on 01/02/2018.
 */

public class AdapterRepositorios extends RecyclerView.Adapter {

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private List<Repo> repos;

    public AdapterRepositorios(List<Repo> repos){
        this.repos = repos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}
