package com.aditya.jetpack.datasource;

import com.aditya.jetpack.dao.DaoMovie;
import com.aditya.jetpack.model.ModelFilm;

import java.util.List;

class MovieFavoriteDataSource implements DaoMovie {

    @Override
    public List<ModelFilm.Result> getAll() {
        return null;
    }

    @Override
    public void insertAll(ModelFilm.Result... results) {

    }
}
