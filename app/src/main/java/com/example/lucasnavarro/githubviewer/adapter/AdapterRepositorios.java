package com.example.lucasnavarro.githubviewer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucasnavarro.githubviewer.R;
import com.example.lucasnavarro.githubviewer.model.Repo;

import java.util.List;

/**
 * Created by Lucas Navarro on 01/02/2018.
 */

public class AdapterRepositorios extends RecyclerView.Adapter<AdapterRepositorios.MyViewHolder> {

    private List<Repo> repos;

    public AdapterRepositorios(List<Repo> repos){
        this.repos = repos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_repositorios, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.textViewLinguagem.setText(repo.getLanguageRepo());
        holder.textViewNomeRepo.setText(repo.getNameRepo());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomeRepo;
        TextView textViewLinguagem;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewNomeRepo = itemView.findViewById(R.id.textViewNomeRepo);
            textViewLinguagem = itemView.findViewById(R.id.textViewLinguagem);
        }
    }

}
