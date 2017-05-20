package me.oabbasi.moviedb.movie.list;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import me.oabbasi.moviedb.R;
import me.oabbasi.moviedb.databinding.ActivityMovieListBinding;

public class MovieListActivity extends AppCompatActivity {

    @Inject
    protected MovieListViewModel movieListViewModel;

    private ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        setSupportActionBar(binding.toolbar);
        initUI();
        movieListViewModel.init();
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.moviesRecyclerView.setLayoutManager(layoutManager);
        binding.moviesRecyclerView.addOnScrollListener(movieListViewModel.getEndlessScrollListener(layoutManager));
        binding.moviesRecyclerView.setAdapter(movieListViewModel.getMovieListAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            //TODO filter
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
