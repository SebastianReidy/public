package ch.epfl.sweng.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.domain.Story;
import ch.epfl.sweng.domain.api.ApiResponse;
import ch.epfl.sweng.domain.api.HackerNewsAPI;
import ch.epfl.sweng.domain.api.HackerNewsItem;
import ch.epfl.sweng.domain.api.retrofit.RetrofitHackerNewsAPIFactory;
import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static java.util.stream.Collectors.toList;
import static ch.epfl.sweng.presentation.utils.LiveDataTransformations.combineLatestNonNull;


public class StoriesViewModel extends AndroidViewModel {

    private HackerNewsAPI api;

    public StoriesViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<List<Integer>> getTopStoriesIDs(){
        return map(api.topStories(), ids -> ids.stream().flatMap(List::stream).collect(toList()));
    }

    private LiveData<List<HackerNewsItem>> getTopStoriesItems(){
        var responses = switchMap(getTopStoriesIDs(), ids -> combineLatestNonNull(ids.stream().map(api::item).collect(toList())));
        return map(responses, r -> r.stream().flatMap(ApiResponse::stream).collect(toList()));
    }

    public LiveData<List<Story>> getTopStories(){
        return map(getTopStoriesItems(), list -> list.stream()
                .map(data -> new Story.Builder().id(data.id).title(data.title).url(data.url).build())
                .collect(toList()));
    }
}
