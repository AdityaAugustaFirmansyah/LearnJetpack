package com.aditya.jetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aditya.jetpack.model.ModelFilm;

import java.util.List;

@Dao
public interface DaoMovie {
    @Query("SELECT * FROM result")
    LiveData<List<ModelFilm.Result>> getAll();

    @Insert
    void insertAll(ModelFilm.Result... results);
}
