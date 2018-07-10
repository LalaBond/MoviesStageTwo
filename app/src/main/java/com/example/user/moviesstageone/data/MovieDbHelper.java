package com.example.user.moviesstageone.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by someone on 7/8/18.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";

    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        /*Query for creating database*/
        final String SQL_CREATE_MOVIEFAVORITES_TABLE =

                "CREATE TABLE " + FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME + " (" +

                        FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                        FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL " + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIEFAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        /*Only will get called if I change Database version*/
    }
}
