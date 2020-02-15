package com.aditya.jetpack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.jetpack.databinding.ItemFilmBinding;
import com.aditya.jetpack.databinding.ItemTvBinding;

import java.util.ArrayList;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.Holder> {
    private ArrayList<ModelFilm.Result>modelFilms;
    private ArrayList<ModelTv.Result>modelTvs;

    public void setModelFilms(ArrayList<ModelFilm.Result> modelFilms) {
        this.modelFilms = modelFilms;
    }

    public void setModelTvs(ArrayList<ModelTv.Result> modelTvs) {
        this.modelTvs = modelTvs;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (modelFilms != null){
            ItemFilmBinding itemFilmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_film,parent,false);
            return new Holder(itemFilmBinding.getRoot());
        }else {
            ItemTvBinding itemTvBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_tv,parent,false);
            return new Holder(itemTvBinding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (modelFilms != null){
            holder.bindData(modelFilms.get(position));
        }else {
            holder.bindDataTv(modelTvs.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (modelFilms!=null){
            return modelFilms.size();
        }else {
            return modelTvs.size();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        public void bindData(ModelFilm.Result modelFilm){
            ItemFilmBinding itemFilmBinding = DataBindingUtil.bind(itemView);
            if (itemFilmBinding != null) {
                itemFilmBinding.setDataFilm(modelFilm);
            }
        }
        public void bindDataTv(ModelTv.Result result){
            ItemTvBinding itemTvBinding = DataBindingUtil.bind(itemView);
            if (itemTvBinding != null){
                itemTvBinding.setData(result);
            }
        }
    }
}
