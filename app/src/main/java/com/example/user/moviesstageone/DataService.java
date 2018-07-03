package com.example.user.moviesstageone;

import com.example.user.moviesstageone.model.MovieReviewsResponse;
import com.example.user.moviesstageone.model.MovieTrailerResponse;
import com.example.user.moviesstageone.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by someone on 5/29/18.
 */

public interface DataService {

    @GET("popular?api_key=")
    Call<MoviesResponse> getPopularMovies();

    @GET("top_rated?api_key=")
    Call<MoviesResponse> getTopRatedMovies();

    @GET("278/reviews?api_key=")
    Call<MovieReviewsResponse> getMovieReviews();

    @GET("{movie_id}/videos?api_key=")
    Call<MovieTrailerResponse> getMovieTrailer(@Path("movie_id") String movie_id);
}
