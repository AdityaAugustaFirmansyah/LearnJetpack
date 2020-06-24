package com.aditya.jetpack.datasource;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelFilm implements Serializable {

    ArrayList<Result>results;

    public ArrayList<Result> getResults() {
        return results;
    }

    @BindingAdapter({"text"})
    public static void setTextFilm(TextView textFilm,String value){
        textFilm.setText(value);
    }

    @BindingAdapter({"imgUrl"})
    public static void setImgUrl(ImageView imageView,String imgUrl){
        Glide.with(imageView.getContext()).load("https://image.tmdb.org/t/p/w500"+imgUrl).into(imageView);
    }

    public static class Result implements Serializable,Cloneable{
        private String title;
        private String poster_path;
        private int id;
        private ArrayList<Integer>genre_ids;

        public ArrayList<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(ArrayList<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }
    }
}
