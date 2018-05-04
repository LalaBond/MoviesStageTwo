package com.example.user.moviesstageone;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*Private Fields*/
    private List<MoviesResponse> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL api = new URL("https://api.themoviedb.org/3/movie/popular?api_key=");

            HttpURLConnection request = (HttpURLConnection) api.openConnection();
            request.setRequestMethod("GET");
            request.setRequestProperty("Content-Type", "application/json");
            request.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(api.openStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            String jsonString = sb.toString();
            System.out.println("JSON: " + new JSONObject(jsonString));
            System.out.println("JSON object: " + new JSONObject(jsonString));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        CustomAdapter adapter = new CustomAdapter(movies);
        recyclerView.setLayoutManager(gridLayout);

    }
}
