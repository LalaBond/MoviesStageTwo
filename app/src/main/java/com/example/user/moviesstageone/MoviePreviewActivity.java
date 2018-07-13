package com.example.user.moviesstageone;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.moviesstageone.adapters.TrailerListAdapter;
import com.example.user.moviesstageone.data.FavoriteMoviesContract;
import com.example.user.moviesstageone.data.MovieContentProvider;
import com.example.user.moviesstageone.data.MovieDbHelper;
import com.example.user.moviesstageone.model.MovieTrailerResponse;
import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.network.DataService;
import com.example.user.moviesstageone.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by someone on 6/6/18.
 */

public class MoviePreviewActivity extends AppCompatActivity {

    private String posterPath = "http://image.tmdb.org/t/p/w780/";
    private Movies movie;
    private ImageButton likeButton;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_preview);

        Intent intent = getIntent();

        movie = (Movies) intent.getSerializableExtra("movies");

        ImageView image = findViewById(R.id.image);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);
        TextView movieTitleTextView = findViewById(R.id.movieTitleTextView);
        TextView voteAverage = findViewById(R.id.voteAverage);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        likeButton = findViewById(R.id.likeButton);

        /*Calling service in order to get movie trailers*/
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<MovieTrailerResponse> call = service.getMovieTrailer(String.valueOf(movie.getId()));
        APICall(call);

        String posterS1 = movie.getPoster_path();
        String posterPath1 = posterPath.concat(posterS1);
        Picasso.with(this).load(posterPath1).into(image);

        releaseDateTextView.setText(movie.getRelease_date());
        movieTitleTextView.setText(movie.getTitle());
        voteAverage.setText(Double.toString(movie.getVote_average()) + "/10");
        descriptionTextView.setText(movie.getOverview());


        /*Verify if movie is a favorite. O(n) needs improvement*/
        Cursor queryResults = getContentResolver().query(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, null, null, null, null);

        int movieIndex = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID);
        int results = queryResults.getCount();

        for (int i = 0; i < results; i++){

            queryResults.moveToPosition(i);
            if (movie.getId() == queryResults.getInt(movieIndex)){
                //movie is a favorite

                likeButton.setImageResource(R.drawable.like);
                isFavorite = true;
            }
        }

    }


    public void favoriteMovie (View view)
    {
        try {

            if(isFavorite){
                Uri uri = FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(String.valueOf(movie.getId())).build();

                //delete row
                int rowsDeleted = getContentResolver().delete(uri, null, null);

                Toast toast = Toast.makeText(this, movie.getTitle() + " has been deleted from favorites", Toast.LENGTH_LONG);
                toast.show();

                likeButton.setImageResource(R.drawable.unlike);
                isFavorite = false;

            }
            else {
                ContentValues values = new ContentValues();

                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID, movie.getId());
                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_TITLE, movie.getTitle());
                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_DATE, movie.getRelease_date());
                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_RATING, movie.getVote_average());
                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_DESCRIPTION, movie.getOverview());
                values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_POSTER, movie.getPoster_path());


            /*Inserting data using the content provider*/
                Uri uri = getContentResolver().insert(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, values);

                Toast toast = Toast.makeText(this, movie.getTitle() + " has been added to favorites", Toast.LENGTH_LONG);
                toast.show();

                likeButton.setImageResource(R.drawable.like);
                isFavorite = true;
            }

        } catch(Exception e) {

            System.out.println("$LALA: " + e);
        }

    }

    public void showReviews(View view)
    {
                Intent intent = new Intent(this, ReviewsActivity.class);
                intent.putExtra("movieId", movie.getId());
                startActivity(intent);
    }

    /*Method to call api service and get data*/
    private void APICall(Call<MovieTrailerResponse> call) {
        try {

            call.enqueue(new Callback<MovieTrailerResponse>() {

                /*If call was success create adapter with data received*/
                public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {

                    createAdapter(response.body());
                }

                public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                    System.out.println("ERROR IN RESPONSE: " + t.getMessage());
                }
            });

        }catch (Exception e ){

            System.out.println("Error in service: " + e);
        }
    }

    private void createAdapter(MovieTrailerResponse body) {

         /*getting movie trailers and displaying them in the recycler view*/

        RecyclerView trailerRecyclerView = findViewById(R.id.trailersRecyclerView);
        TrailerListAdapter adapter = new TrailerListAdapter(this, body);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        trailerRecyclerView.setLayoutManager(layoutManager);
        trailerRecyclerView.setAdapter(adapter);

    }


}
