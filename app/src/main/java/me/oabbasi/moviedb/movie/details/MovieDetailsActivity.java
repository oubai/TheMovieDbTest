package me.oabbasi.moviedb.movie.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.oabbasi.moviedb.R;
import me.oabbasi.moviedb.databinding.ActivityMovieDetailsBinding;
import me.oabbasi.moviedb.model.MovieListing;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MODEL = "ExtraModel";
    private ActivityMovieDetailsBinding binding;
    private MovieListing model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        setSupportActionBar(binding.toolbar);
        model = (MovieListing) getIntent().getSerializableExtra(EXTRA_MODEL);
        binding.setModel(model);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener((view) -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
