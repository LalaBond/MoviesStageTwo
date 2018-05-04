package com.example.user.moviesstageone;

import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class MoviesResponse {

    private String page;
    private String total_results;
    private String total_pages;
    private List<Movies> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }
}




