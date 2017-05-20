package me.oabbasi.moviedb.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import me.oabbasi.moviedb.model.MoviesList;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MoviesConnection {
    private final String API_KEY = "409231100723bd3cc2636039a37d56e0";

    private final TheMovieDbApi theMovieDbApi;

    public MoviesConnection() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.themoviedb.org/3/")
                .build();

        theMovieDbApi = retrofit.create(TheMovieDbApi.class);
    }

    public Observable<MoviesList> getMovies(int page, Date releaseDateStart, Date releaseDateEnd) {
        return theMovieDbApi.getMovies(API_KEY, page, releaseDateStart, releaseDateEnd);
    }
}
