package com.aditya.jetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.model.ModelTv;

@Dao
public interface DaoMovie {

    @Query("SELECT * FROM result")
    DataSource.Factory<Integer,ModelFilm.Result> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ModelFilm.Result... results);

    @Query("SELECT * FROM result WHERE id =:id")
    LiveData<ModelFilm.Result> getMovieFavorite(int id);

    @Delete
    void deleteMovieFavorite(ModelFilm.Result result);

    @Query("SELECT * FROM resultTv")
    DataSource.Factory<Integer, ModelTv.Result>getAllTvs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTvs(ModelTv.Result... results);

    @Query("SELECT * FROM resultTv WHERE id = :id")
    LiveData<ModelTv.Result>getTvFavorite(int id);

    @Delete
    void deleteTv(ModelTv.Result... results);
}
