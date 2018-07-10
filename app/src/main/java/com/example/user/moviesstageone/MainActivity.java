package com.example.user.moviesstageone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.user.moviesstageone.adapters.MoviesCustomAdapter;
import com.example.user.moviesstageone.data.FavoriteMoviesContract;
import com.example.user.moviesstageone.data.MovieDbHelper;
import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.model.MoviesResponse;
import com.example.user.moviesstageone.network.DataService;
import com.example.user.moviesstageone.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<MoviesResponse> call = service.getPopularMovies();

        APICall(call);
    }

    /*Method to call api service and get data*/
    private void APICall(Call<MoviesResponse> call) {
        try {

            call.enqueue(new Callback<MoviesResponse>() {

                /*If call was success create adapter with data received*/
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                    createAdapter(response.body());
                }

                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    System.out.println("ERROR IN RESPONSE: " + t.getMessage());
                }
            });

        }catch (Exception e ){

            System.out.println("Error in service: " + e);
        }
    }

    private void createAdapter(MoviesResponse body) {

        RecyclerView recyclerView = findViewById(R.id.moviePosterRecyclerView);

        MoviesCustomAdapter adapter = new MoviesCustomAdapter(this, body);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<MoviesResponse> call;

        switch (id){

            case (R.id.mostPopular):
                call = service.getPopularMovies();
                break;

            case (R.id.highestRated):
                call = service.getTopRatedMovies();
                break;

            case (R.id.favorites):
                Cursor queryResults = getFavorites();

            default:
                call = service.getPopularMovies();
                break;
        }
        APICall(call);

        return super.onOptionsItemSelected(item);
    }

    private Cursor getFavorites() {

        MovieDbHelper dbHelper = new MovieDbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor cursor =  database.query(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        MoviesResponse response = new MoviesResponse();
        Movies movie = new Movies();


        for (int i = 0; i < cursor.getCount(); i++)
        {

            //response.setResults(movie.setId(cursor.getColumnIndex()););

        }
        cursor.getCount();
        return cursor;

    }
}
