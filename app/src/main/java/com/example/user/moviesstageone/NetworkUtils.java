package com.example.user.moviesstageone;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by User on 5/21/2018.
 */

public final class NetworkUtils {


    public static String getResponseFromHttpUrl (URL url) throws IOException {
//        URL api = new URL("https://api.themoviedb.org/3/movie/popular?api_key=d2321f470253a298739307f4629a1a57/");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
        if (hasInput) {
            response = scanner.next();
        }
        scanner.close();

        return response;

        } catch(Exception e) {

            System.out.println("Error: " + e);
       // }
        } finally {

            urlConnection.disconnect();
    }
        return null;

//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.setRequestMethod("GET");
//        request.setRequestProperty("Content-Type", "application/json");
//        request.connect();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            sb.append(line + "\n");
//        }
//        br.close();
//
//        String jsonString = sb.toString();
//        //System.out.println("JSON: " + new JSONObject(jsonString));
//        //System.out.println("JSON object: " + new JSONObject(jsonString));
//        return null;
    }
}
