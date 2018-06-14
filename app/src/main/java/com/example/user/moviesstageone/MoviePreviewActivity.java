package com.example.user.moviesstageone;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.moviesstageone.model.Movies;
import com.squareup.picasso.Picasso;

/**
 * Created by someone on 6/6/18.
 */

public class MoviePreviewActivity extends AppCompatActivity {

    public MoviePreviewActivity() {


    }
    private String posterPath = "http://image.tmdb.org/t/p/w780/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_preview);

        Intent intent = getIntent();

        Movies movie = (Movies) intent.getSerializableExtra("movies");

        ImageView image = findViewById(R.id.image);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);
        TextView movieTitleTextView = findViewById(R.id.movieTitleTextView);
        TextView voteAverage = findViewById(R.id.voteAverage);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        String posterS1 = movie.getPoster_path();
        String posterPath1 = posterPath.concat(posterS1);
        Picasso.with(this).load(posterPath1).into(image);


        releaseDateTextView.setText(movie.getRelease_date());
        movieTitleTextView.setText(movie.getTitle());
        voteAverage.setText(Double.toString(movie.getVote_average()) + "/10");
        descriptionTextView.setText(movie.getOverview());

    }




}
