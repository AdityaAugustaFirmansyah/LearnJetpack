package com.aditya.jetpack;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.aditya.jetpack.db.AppDatabase;
import com.aditya.jetpack.model.ModelFilm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {

    private Context application;
    private AppDatabase appDatabase;
    private ModelFilm.Result result;
    private ArrayList<Integer> arrayList = new ArrayList<>();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void setup(){
        application = InstrumentationRegistry.getInstrumentation().getContext();
        appDatabase = Room.inMemoryDatabaseBuilder(application,AppDatabase.class).allowMainThreadQueries().build();
        result = new ModelFilm.Result();
    }

    @Test
    public void addDataDatabase(){
        arrayList.add(1);
        result.setGenre_ids(arrayList);
        appDatabase.filmResultDao().insertAll(new ModelFilm.Result());
    }
}