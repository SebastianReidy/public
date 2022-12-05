package ch.epfl.sweng.presentation;

import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static java.util.stream.Collectors.toList;
import static ch.epfl.sweng.presentation.utils.LiveDataTransformations.combineLatestNonNull;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.Comparator;
import java.util.List;

import ch.epfl.sweng.domain.RoomStory;
import ch.epfl.sweng.domain.StoriesDatabase;
import ch.epfl.sweng.domain.StoryDAO;
import ch.epfl.sweng.domain.api.ApiResponse;
import ch.epfl.sweng.domain.api.HackerNewsApi;
import ch.epfl.sweng.domain.api.HackerNewsItem;
import ch.epfl.sweng.domain.api.retrofit.RetrofitHackerNewsApiFactory;
import ch.epfl.sweng.domain.Story;

/** A {@link ViewModel} which displays the list of available stories. */
public final class StoriesViewModel extends AndroidViewModel {

  private final HackerNewsApi api;

  private final StoriesDatabase db;
  private final StoryDAO dao;

  /** Creates a new {@link StoriesViewModel} instance. */
  public StoriesViewModel(Application application) {
    super(application);
    this.api = new RetrofitHackerNewsApiFactory().create();
    this.db = Room.databaseBuilder(application,
            StoriesDatabase.class, "database-stories").build();
    this.dao = this.db.storyDAO();
  }

  /** Returns the top story ids from HackerNews. */
  private LiveData<List<Integer>> getTopItemIdsLiveData() {
    var top = api.topStories();  // request the top stories form the api
    // the api returns a list with the IDs of the best stories
    return map(top, r -> r.stream().flatMap(List::stream).collect(toList()));
  }

  /** Returns the top items from HackerNews. */
  private LiveData<List<HackerNewsItem>> getTopItemsLiveData() {
    var responses =
        switchMap(
            getTopItemIdsLiveData(),
            ids -> combineLatestNonNull(ids.stream().map(api::item).collect(toList())));
    return map(responses, r -> r.stream().flatMap(ApiResponse::stream).collect(toList()));
  }

  /** Returns the top stories from the HackerNews API. */
  public LiveData<List<Story>> getTopStoriesLiveData() {
    return map(
        getTopItemsLiveData(),
        list ->
            list.stream()
                .map(it -> new Story.Builder().id(it.id).title(it.title).url(it.url).build())
                .sorted(Comparator.comparing(Story::getId).reversed())
                .collect(toList()));
  }

  private static RoomStory toEntity(Story story){
    RoomStory roomStory = new RoomStory();
    roomStory.id = story.getId();
    roomStory.title = story.getTitle();
    roomStory.url = story.getUrl();
    return roomStory;
  }

  /** Reloads the stories and populates the database with the new values. */
  public void refresh(LifecycleOwner owner) {
    // TODO : Fetch all the stories from the API and store them in the database.
    // use the obeserve(owner, observer (implements an onChange() function; preferably trough lambda))
    getTopStoriesLiveData().observe(owner, list -> {
      var updated = list.stream().map(StoriesViewModel::toEntity).toArray(RoomStory[]::new);
      dao.insertAll(updated);
    });
  }

  /** Moves all the stories from the database. */
  public void clearAll() {
    // TODO : Clear all the stories from the database.
    this.dao.nukeTable();
  }

  /** Returns the top stories from HackerNews. */
  @NonNull
  public LiveData<List<Story>> getTopStories() {
    // TODO : Observe the values from the database.
    return map(this.dao.getAll(), list -> list.stream().map(entity ->
            new Story.Builder().id(entity.id).title(entity.title).url(entity.url).build())
            .collect(toList()));
  }
}
