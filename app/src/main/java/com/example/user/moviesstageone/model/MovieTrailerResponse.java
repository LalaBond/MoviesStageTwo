package com.example.user.moviesstageone.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by someone on 6/28/18.
 */

public class MovieTrailerResponse {

    @SerializedName("id")
    private int movieId;

    @SerializedName("results")
    private Trailer [] results;

}
