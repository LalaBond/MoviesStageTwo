package com.example.user.moviesstageone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.net.URL;

/**
 * Created by User on 5/21/2018.
 */

public class MovieSyncData {


    synchronized public static void syncData(Context context){
    try{

        URL api = new URL("https://api.themoviedb.org/3/movie/popular?api_key=d2321f470253a298739307f4629a1a57");
        String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(api);

        System.out.print("");

    }
    catch (Exception e) {
        System.out.println("LALA ERROR: " + e);
    }

    }

    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, MovieSyncIntentService.class);
        context.startService(intentToSyncImmediately);

    }
}
