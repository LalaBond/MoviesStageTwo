package com.example.user.moviesstageone.data;

import android.provider.BaseColumns;

/**
 * Created by someone on 7/8/18.
 */

public class FavoriteMoviesContract {

    public static final class FavoriteMoviesEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite_movies";
        public static final String COLUMN_ID = "column_id";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";

    }
}
