package me.oabbasi.moviedb.network;

import java.util.Date;

import me.oabbasi.moviedb.model.MoviesList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TheMovieDbApi {

    @GET("discover/movie")
    Observable<MoviesList> getMovies(@Query("api_key") String apiKey,
                                     @Query("page") int page,
                                     @Query("release_date.gte") Date releaseDateStart,
                                     @Query("release_date.lte") Date releaseDateEnd);
}
