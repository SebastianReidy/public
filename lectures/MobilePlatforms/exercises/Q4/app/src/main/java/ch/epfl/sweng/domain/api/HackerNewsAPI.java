package ch.epfl.sweng.domain.api;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsAPI {

    String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

    @GET("topstories.json")
    LiveData<ApiResponse<List<Integer>>> topStories();

    @GET("item/{id}.json")
    LiveData<ApiResponse<HackerNewsItem>> item(@Path("id") int id);
}
