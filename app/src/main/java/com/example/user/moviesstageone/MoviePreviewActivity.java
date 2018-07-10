package com.example.user.moviesstageone;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_preview);

        Intent intent = getIntent();

        MovieDbHelper dbHelper = new MovieDbHelper(this);
        database = dbHelper.getWritableDatabase();

        movie = (Movies) intent.getSerializableExtra("movies");

        ImageView image = findViewById(R.id.image);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);
        TextView movieTitleTextView = findViewById(R.id.movieTitleTextView);
        TextView voteAverage = findViewById(R.id.voteAverage);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

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


    }


    public void favoriteMovie (View view)
    {
        try {
            ContentValues values = new ContentValues();

            values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID, movie.getId() );
            values.put(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_TITLE, movie.getTitle());

            /*Inserting data using the content provider*/
            Uri uri = getContentResolver().insert(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, values);
            //long id = database.insert(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME, null, values);

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
