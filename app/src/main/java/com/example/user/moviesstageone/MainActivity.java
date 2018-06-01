package com.example.user.moviesstageone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.moviesstageone.model.MoviesResponse;
import com.example.user.moviesstageone.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /*Private Fields*/
    private List<MoviesResponse> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService service = null;

            try {
                service = RetrofitClient.getRetrofitInstance().create(DataService.class);
                Call<MoviesResponse> call = service.getAllMovies();

                call.enqueue(new Callback<MoviesResponse>() {


                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                        System.out.println("RESPONSE: " + response.body());
                        System.out.println("RESPONSE to string: " + response.toString());
                    }

                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        System.out.println("ERROR IN RESPONSE: " + t.getMessage());

                    }
                });
            }catch (Exception e ){

                System.out.println("LALA ERROR: " + e);
            }



        //MovieSyncData.startImmediateSync(this);
//        try {
//            URL api = new URL("https://api.themoviedb.org/3/movie/popular?api_key=d2321f470253a298739307f4629a1a57");
//
//            HttpURLConnection request = (HttpURLConnection) api.openConnection();
//            request.setRequestMethod("GET");
//            request.setRequestProperty("Content-Type", "application/json");
//            request.connect();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(api.openStream()));
//            StringBuilder sb = new StringBuilder();
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            br.close();
//
//            String jsonString = sb.toString();
//            System.out.println("JSON: " + new JSONObject(jsonString));
//            System.out.println("JSON object: " + new JSONObject(jsonString));
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
        //}


        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        CustomAdapter adapter = new CustomAdapter(movies);
        recyclerView.setLayoutManager(gridLayout);

    }
}
