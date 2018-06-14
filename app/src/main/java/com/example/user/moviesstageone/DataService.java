package com.example.user.moviesstageone;

import com.example.user.moviesstageone.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by someone on 5/29/18.
 */

public interface DataService {


    @GET("3/movie/popular?api_key=")
    Call<MoviesResponse> getPopularMovies();

    @GET("3/movie/top_rated?api_key=")
    Call<MoviesResponse> getTopRatedMovies();
}
