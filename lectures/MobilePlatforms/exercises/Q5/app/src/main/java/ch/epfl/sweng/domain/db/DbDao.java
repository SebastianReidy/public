package ch.epfl.sweng.domain.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbDao {
    @Query("SELECT * FROM DbStory")
    LiveData<List<DbStory>> getAll();

    @Insert
    void insertAll(DbStory... stories);

    @Delete
    void delete(DbStory story);

    @Query("DELETE FROM DbStory")
    void clear();
}
