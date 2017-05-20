package me.oabbasi.moviedb.network;

import me.oabbasi.moviedb.model.MoviesList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TheMovieDbApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String API_KEY = "409231100723bd3cc2636039a37d56e0";
    String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    @GET("discover/movie")
    Observable<MoviesList> getMovies(@Query("api_key") String apiKey,
                                     @Query("page") int page,
                                     @Query("release_date.gte") String releaseDateStart,
                                     @Query("release_date.lte") String releaseDateEnd);
}
