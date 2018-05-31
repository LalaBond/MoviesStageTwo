package com.example.user.moviesstageone.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by someone on 5/29/18.
 */

public class RetrofitClient {

    private static Retrofit retrofit;

    private static String BASE_URL = "https://api.themoviedb.org/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
