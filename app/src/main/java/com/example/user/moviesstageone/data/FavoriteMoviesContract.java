package com.example.user.moviesstageone.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by someone on 7/8/18.
 */

public class FavoriteMoviesContract {

    /*Building Uri to access data from the db*/
    public static final String AUTHORITY = "com.example.user.moviesstageone";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_FAVORITE_MOVIES = "favorite_movies";


    /*Base columns generate automatically the id column*/
    public static final class FavoriteMoviesEntry implements BaseColumns {


        /*Building Uri.  Final Uri should be
        content://com.example.user.moviesstageone/favorite_movies*/
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_MOVIES).build();

        /*Modeling database table*/
        public static final String TABLE_NAME = "favorite_movies";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";

    }
}
