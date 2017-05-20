package me.oabbasi.moviedb.movie.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import me.oabbasi.moviedb.databinding.MovieListingBinding;
import me.oabbasi.moviedb.model.MovieListing;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListingViewHolder> {

    private List<MovieListing> movieListings;
    @Setter
    private OnItemClickListener onItemClickListener;

    public MovieListAdapter() {
        movieListings = new ArrayList<>();
    }

    @Override
    public MovieListingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MovieListingViewHolder(MovieListingBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MovieListingViewHolder customViewHolder, int position) {
        MovieListingBinding binding = customViewHolder.getBinding();
        binding.setModel(movieListings.get(position));
        binding.getRoot().setOnClickListener(view -> {
            if (onItemClickListener != null && movieListings.size() > position) {
                onItemClickListener.onItemClick(view, movieListings.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListings.size();
    }

    public void setData(List<MovieListing> movieListings) {
        this.movieListings = movieListings;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View clickedView, MovieListing movieListing);
    }

    class MovieListingViewHolder extends RecyclerView.ViewHolder {

        @Getter
        private final MovieListingBinding binding;

        public MovieListingViewHolder(MovieListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
