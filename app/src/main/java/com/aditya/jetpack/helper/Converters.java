package com.aditya.jetpack.helper;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> genre_ids) {
        Gson gson = new Gson();
        return gson.toJson(genre_ids);
    }

    @TypeConverter
    public static ArrayList<Integer> fromInt(String value) {
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(value,type);
    }
}
