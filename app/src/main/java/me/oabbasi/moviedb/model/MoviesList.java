package me.oabbasi.moviedb.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class MoviesList implements Serializable{

    @SerializedName("page")
    public Integer page;
    @SerializedName("results")
    public List<MovieListing> movieListings = null;
    @SerializedName("total_results")
    public Integer totalResults;
    @SerializedName("total_pages")
    public Integer totalPages;

}