package com.example.user.moviesstageone;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class Movies implements Serializable {

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;


}


