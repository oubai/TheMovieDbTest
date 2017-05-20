package me.oabbasi.moviedb.di;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import me.oabbasi.moviedb.MovieDbApplication;
import me.oabbasi.moviedb.network.MoviesConnection;

@PerApplication
@Component(
        modules = {
                AndroidInjectionModule.class,
                BindingModule.class,
                MovieDbApplicationComponent.AppModule.class
        })
public interface MovieDbApplicationComponent {
    void inject(MovieDbApplication dagger2application);

    @Module
    @PerApplication
    class AppModule {
        @Provides
        MoviesConnection provideMoviesConnection() {
            return new MoviesConnection();
        }
    }
}
