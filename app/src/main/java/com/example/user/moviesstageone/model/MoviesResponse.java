package com.example.user.moviesstageone.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


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
    private Movies [] results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public Movies[] getResults() {
        return results;
    }

    public void setResults(Movies[] results) {
        this.results = results;
    }
}




