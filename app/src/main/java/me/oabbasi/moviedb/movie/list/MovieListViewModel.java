package me.oabbasi.moviedb.movie.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import me.oabbasi.moviedb.model.MovieListing;
import me.oabbasi.moviedb.movie.details.MovieDetailsActivity;
import me.oabbasi.moviedb.network.MoviesConnection;
import me.oabbasi.moviedb.util.EndlessRecyclerViewScrollListener;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieListViewModel implements MovieListAdapter.OnItemClickListener {

    private MoviesConnection moviesConnection;

    @Getter
    private final MovieListAdapter movieListAdapter;
    private EndlessRecyclerViewScrollListener endlessScrollListener;

    private Date startDate = null, endDate = null;
    private List<MovieListing> movieListings;

    public MovieListViewModel(MovieListAdapter movieListAdapter, MoviesConnection moviesConnection) {
        this.movieListAdapter = movieListAdapter;
        this.moviesConnection = moviesConnection;

    }

    public void init() {
        endlessScrollListener.resetState();
        startDate = null;
        endDate = null;
        movieListings = new ArrayList<>();
        movieListAdapter.setOnItemClickListener(this);
        loadData(EndlessRecyclerViewScrollListener.DEFAULT_START_PAGE_INDEX);
    }

    public void updateDates(Date startDate, Date endDate) {
        endlessScrollListener.resetState();
        this.startDate = startDate;
        this.endDate = endDate;
        movieListings = new ArrayList<>();
        loadData(EndlessRecyclerViewScrollListener.DEFAULT_START_PAGE_INDEX);
    }

    public EndlessRecyclerViewScrollListener getEndlessScrollListener(LinearLayoutManager layoutManager) {
        endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadData(page);
            }
        };
        return endlessScrollListener;
    }

    private void loadData(int page) {
        moviesConnection.getMovies(page, startDate, endDate).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> {
                    List<MovieListing> movieListings = moviesList.getMovieListings();
                    if (movieListings != null && movieListings.size() > 0) {
                        this.movieListings.addAll(movieListings);
                        movieListAdapter.setData(this.movieListings);
                        endlessScrollListener.setHasMoreData(true);
                    } else {
                        endlessScrollListener.setHasMoreData(false);
                    }
                }, error -> {
                    error.printStackTrace();
                    endlessScrollListener.setHasMoreData(false);
                });
    }

    @Override
    public void onItemClick(View clickedView, MovieListing movieListing) {
        Context context = clickedView.getContext();
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MODEL, movieListing);
        context.startActivity(intent);
    }
}
