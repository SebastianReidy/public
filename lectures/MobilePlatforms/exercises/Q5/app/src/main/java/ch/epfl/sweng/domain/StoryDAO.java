package ch.epfl.sweng.domain;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StoryDAO {
    @Query("SELECT * FROM RoomStory")
    LiveData<List<RoomStory>> getAll();

    @Query("DELETE FROM RoomStory")
    void nukeTable();

    @Insert
    void insertAll(RoomStory... stories);

    @Delete
    void delete(RoomStory story);
}
