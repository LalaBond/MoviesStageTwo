package com.example.user.moviesstageone;

import com.example.user.moviesstageone.model.MovieReviewsResponse;
import com.example.user.moviesstageone.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by someone on 5/29/18.
 */

public interface DataService {

    @GET("popular?api_key=d2321f470253a298739307f4629a1a57")
    Call<MoviesResponse> getPopularMovies();

    @GET("top_rated?api_key=d2321f470253a298739307f4629a1a57")
    Call<MoviesResponse> getTopRatedMovies();

    @GET("19404/reviews?api_key=d2321f470253a298739307f4629a1a57")
    Call<MovieReviewsResponse> getMovieReviews();
}
