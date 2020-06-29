package com.aditya.jetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aditya.jetpack.model.ModelFilm;

@Dao
public interface DaoMovie {
    @Query("SELECT * FROM result")
    DataSource.Factory<Integer,ModelFilm.Result> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ModelFilm.Result... results);

    @Query("SELECT * FROM result WHERE id =:id")
    LiveData<ModelFilm.Result> getMovieFavorite(int id);
}
