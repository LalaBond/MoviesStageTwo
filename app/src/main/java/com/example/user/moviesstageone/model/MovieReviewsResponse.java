package com.example.user.moviesstageone.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by someone on 6/20/18.
 */

public class MovieReviewsResponse implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private Reviews [] results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Reviews[] getResults() {
        return results;
    }

    public void setResults(Reviews[] results) {
        this.results = results;
    }
}


