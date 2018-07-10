package com.example.user.moviesstageone.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by someone on 7/10/2018.
 */

public class MovieContentProvider extends ContentProvider {

    private MovieDbHelper dbHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final int FAVORITE_MOVIES = 100;
    public static final int FAVORITE_MOVIE_ID = 101;



    public static UriMatcher buildUriMatcher(){

    /*No matching Uri. Caso base*/
    UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /*Uri to get all table data*/
    uriMatcher.addURI(FavoriteMoviesContract.AUTHORITY, FavoriteMoviesContract.PATH_FAVORITE_MOVIES, 100);

    /*Uri to get a single item*/
        uriMatcher.addURI(FavoriteMoviesContract.AUTHORITY, FavoriteMoviesContract.PATH_FAVORITE_MOVIES + "/#", 101);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        dbHelper = new MovieDbHelper(context);

        return true;
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        /*Reading data from database*/
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        Cursor cursor;
        switch (match){
            case FAVORITE_MOVIES:
                cursor = database.query(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;
            default:
                throw new UnsupportedOperationException("Unknown  uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        Uri returnUri;
        /*match the uri received */
        int match = sUriMatcher.match(uri);
        switch (match){
            case FAVORITE_MOVIES:
                /*insert new row of data. If insert was'nt successful it will return -1*/
                long id = database.insert(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME, null, contentValues);
                if(id > 0){
                    //success. do something
                    returnUri = ContentUris.withAppendedId(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, id);
                }
                else{
                    //insert has failed
                    throw new android.database.SQLException("Failed to insert row with uri " + uri);
                }

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

        /*Letting the content resolver know that data has changed and needs to update UI*/
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
