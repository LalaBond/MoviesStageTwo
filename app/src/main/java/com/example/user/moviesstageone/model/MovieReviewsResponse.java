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

 class Reviews implements Serializable {

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }
 }


