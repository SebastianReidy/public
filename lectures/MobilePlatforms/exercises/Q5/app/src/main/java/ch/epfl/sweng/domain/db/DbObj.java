package ch.epfl.sweng.domain.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DbStory.class}, version = 1)
public abstract class DbObj extends RoomDatabase {
    public abstract DbDao storyDao();
}
