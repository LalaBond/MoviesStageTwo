package com.example.user.moviesstageone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

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

            try {
                DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
                Call<MoviesResponse> call = service.getAllMovies();

                call.enqueue(new Callback<MoviesResponse>() {

                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                        System.out.println("RESPONSE: " + response.body());
                        System.out.println("RESPONSE to string: " + response.toString());
                        createAdapter(response.body());
                    }

                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        System.out.println("ERROR IN RESPONSE: " + t.getMessage());
                    }
                });
            }catch (Exception e ){

                System.out.println("LALA ERROR: " + e);
            }
    }

    private void createAdapter(MoviesResponse body) {

        RecyclerView recyclerView = findViewById(R.id.moviePosterRecyclerView);

        MoviesCustomAdapter adapter = new MoviesCustomAdapter(this, body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

       // MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.sort_menu, menu);
        //return true;
    }
}
