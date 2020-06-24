package com.aditya.jetpack.model;

import java.util.ArrayList;

public class ModelGenre {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class ModelGenreResponse{
        ArrayList<ModelGenre>genres;

        public ArrayList<ModelGenre> getGenres() {

            return genres;
        }

        public void setGenres(ArrayList<ModelGenre> genres) {
            this.genres = genres;
        }
    }
}
