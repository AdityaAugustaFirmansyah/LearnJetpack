package com.aditya.jetpack.model;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelFilm implements Serializable {

    private ArrayList<Result>results;

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


    @Entity
    public static class Result implements Serializable,Cloneable{
        private String title;
        private String poster_path;
        @PrimaryKey
        private int id;
        private String original_language;
        private String original_title;
        private boolean video;
        private String backdrop_path;
        private String release_date;
        private String popularity;
        private String vote_average;
        private boolean adult;
        private String vote_count;
        private String overview;

        private ArrayList<Integer>genre_ids;

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public ArrayList<Integer> getGenre_ids() {
            return genre_ids;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getPopularity() {
            return popularity;
        }

        public void setPopularity(String popularity) {
            this.popularity = popularity;
        }

        public String getVote_average() {
            return vote_average;
        }

        public void setVote_average(String vote_average) {
            this.vote_average = vote_average;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getVote_count() {
            return vote_count;
        }

        public void setVote_count(String vote_count) {
            this.vote_count = vote_count;
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
