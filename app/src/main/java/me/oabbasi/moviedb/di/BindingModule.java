package me.oabbasi.moviedb.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.oabbasi.moviedb.movie.list.MovieListActivity;
import me.oabbasi.moviedb.movie.list.di.MovieListActivitySubComponent;

@PerApplication
@Module(subcomponents = {
        MovieListActivitySubComponent.class
})
public interface BindingModule {
    @Binds
    @IntoMap
    @PerApplication
    @ActivityKey(MovieListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMovieListActivityInjectorFactory(MovieListActivitySubComponent.Builder builder);
}
