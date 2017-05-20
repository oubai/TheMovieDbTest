package me.oabbasi.moviedb.network;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.oabbasi.moviedb.model.MoviesList;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MoviesConnection {

    private final TheMovieDbApi theMovieDbApi;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public MoviesConnection() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TheMovieDbApi.BASE_URL)
                .build();

        theMovieDbApi = retrofit.create(TheMovieDbApi.class);
    }

    public Observable<MoviesList> getMovies(int page, Date releaseDateStart, Date releaseDateEnd) {
        return theMovieDbApi.getMovies(TheMovieDbApi.API_KEY, page, releaseDateStart == null ? null : simpleDateFormat.format(releaseDateStart),
                releaseDateEnd == null ? null : simpleDateFormat.format(releaseDateEnd));
    }
}
