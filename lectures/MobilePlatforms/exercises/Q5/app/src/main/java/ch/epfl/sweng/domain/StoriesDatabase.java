package ch.epfl.sweng.domain;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomStory.class}, version = 1)
public abstract class StoriesDatabase extends RoomDatabase {
    public abstract StoryDAO storyDAO();
}
