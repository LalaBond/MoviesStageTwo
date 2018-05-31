package com.example.user.moviesstageone;

import com.example.user.moviesstageone.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by someone on 5/29/18.
 */

public interface DataService {

    //@GET("3/movie/popular?api_key=d2321f470253a298739307f4629a1a57")
    @GET("3/movie/popular?api_key=d2321f470253a298739307f4629a1a57")
    Call<List<MoviesResponse>> getAllMovies();
}
