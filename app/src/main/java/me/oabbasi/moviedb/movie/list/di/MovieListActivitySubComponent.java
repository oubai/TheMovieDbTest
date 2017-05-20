package me.oabbasi.moviedb.movie.list.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.oabbasi.moviedb.di.PerActivity;
import me.oabbasi.moviedb.movie.list.MovieListActivity;
import me.oabbasi.moviedb.movie.list.MovieListAdapter;
import me.oabbasi.moviedb.movie.list.MovieListViewModel;
import me.oabbasi.moviedb.network.MoviesConnection;

@PerActivity
@Subcomponent(modules = {
        MovieListActivitySubComponent.MovieListActivityModule.class
})
public interface MovieListActivitySubComponent extends AndroidInjector<MovieListActivity> {

    @Module
    class MovieListActivityModule {

        @Provides
        @PerActivity
        public MovieListAdapter provideAdapter() {
            return new MovieListAdapter();
        }

        @Provides
        public MovieListViewModel provideViewModel(MovieListAdapter movieListAdapter, MoviesConnection moviesConnection) {
            return new MovieListViewModel(movieListAdapter, moviesConnection);
        }
    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieListActivity> {
    }
}
