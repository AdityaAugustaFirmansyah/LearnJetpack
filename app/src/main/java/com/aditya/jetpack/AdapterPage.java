package com.aditya.jetpack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.jetpack.databinding.ItemFilmBinding;

public class AdapterPage extends PagedListAdapter<ModelFilm.Result,AdapterPage.Holder> {
    private static final DiffUtil.ItemCallback<ModelFilm.Result> diffCallback = new DiffUtil.ItemCallback<ModelFilm.Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelFilm.Result oldItem, @NonNull ModelFilm.Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelFilm.Result oldItem, @NonNull ModelFilm.Result newItem) {
            return true;
        }
    };

    protected AdapterPage() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding itemFilmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_film,parent,false);
        return new Holder(itemFilmBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bindData(getItem(position));
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
    }
}
