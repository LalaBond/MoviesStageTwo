package com.example.user.moviesstageone.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by someone on 6/28/18.
 */

public class Trailer {


    @SerializedName("id")
    private String id;

    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private String size;

    @SerializedName("type")
    private String type;

}
