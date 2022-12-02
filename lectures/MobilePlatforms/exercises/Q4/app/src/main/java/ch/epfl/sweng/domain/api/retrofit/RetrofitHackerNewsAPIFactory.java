package ch.epfl.sweng.domain.api.retrofit;

import static retrofit2.Retrofit.*;

import ch.epfl.sweng.domain.api.HackerNewsAPI;
import ch.epfl.sweng.domain.api.HackerNewsAPIFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHackerNewsAPIFactory implements HackerNewsAPIFactory {
    private final Retrofit retrofit =
            new Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    // only if you want to use LiveData
                    // .addCallAdapterFactory(new LiveDataAdapterFactory())
                    .baseUrl(HackerNewsAPI.BASE_URL)
                    .build();

    @Override
    public HackerNewsAPI create() {
        return retrofit.create(HackerNewsAPI.class);
    }
}