package me.oabbasi.moviedb.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class MoviesList implements Serializable{

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<MovieListing> movieListings = null;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;

}