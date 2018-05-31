package com.example.user.moviesstageone.model;

import com.example.user.moviesstageone.Movies;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class MoviesResponse implements Serializable{


    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private List<Movies> results = null;


}




