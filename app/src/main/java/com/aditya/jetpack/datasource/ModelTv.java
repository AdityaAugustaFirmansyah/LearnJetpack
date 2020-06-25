package com.aditya.jetpack.datasource;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelTv implements Serializable, Cloneable {
    ArrayList<Result> results;

    public ArrayList<Result> getResults() {
        return results;
    }

    public static class Result implements Serializable, Cloneable {
        private String original_name;
        private String name;
        private String overview;
        private String poster_path;
        private double popularity;
        private int id;
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

        public void setGenre_ids(ArrayList<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}