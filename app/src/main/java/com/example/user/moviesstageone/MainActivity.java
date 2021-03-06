package com.example.user.moviesstageone;

import android.database.Cursor;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.moviesstageone.adapters.MoviesCustomAdapter;
import com.example.user.moviesstageone.data.FavoriteMoviesContract;
import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.model.MoviesResponse;
import com.example.user.moviesstageone.network.DataService;
import com.example.user.moviesstageone.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static int position = 0;
    private static RecyclerView recyclerView;
    private static GridLayoutManager layoutManager;
    private static String itemSeleted = "POPULAR_MOVIES";
    private static int totalResults = 0;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empty = findViewById(R.id.empty);
        recyclerView = findViewById(R.id.moviePosterRecyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<MoviesResponse> call = service.getPopularMovies();;

        if(savedInstanceState != null){

            position =  savedInstanceState.getInt("position");
            itemSeleted = savedInstanceState.getString("itemSelected");

            switch (itemSeleted){

                case "POPULAR_MOVIES":
                    call = service.getPopularMovies();
                    APICall(call);
                    break;
                case "HIGHEST_RATED":
                    call = service.getTopRatedMovies();
                    APICall(call);
                    break;
                case "FAVORITES":
                    Cursor queryResults = getFavorites();
                    if(totalResults > 0){
                        parseToMovieObject(queryResults);
                    }
                    else{
                        recyclerView.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    call = service.getPopularMovies();
                    APICall(call);
                    break;

            }

            layoutManager.scrollToPosition(position);

        }else {

            APICall(call);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*Updating favorite list when we hit back button*/
        if(itemSeleted.equals("FAVORITES")){

            Cursor queryResults = getFavorites();
            totalResults = queryResults.getCount();
            if(totalResults > 0){
                parseToMovieObject(queryResults);
            }
            else{
                    recyclerView.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //layoutManager = new GridLayoutManager(this, 2);
        position = layoutManager.findFirstVisibleItemPosition();

        outState.putInt("position", position);
        outState.putString("itemSelected", itemSeleted);
    }

    /*Method to call api service and get data*/
    private void APICall(Call<MoviesResponse> call) {
        try {

            call.enqueue(new Callback<MoviesResponse>() {

                /*If call was success create adapter with data received*/
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                    createAdapter(response.body());
                    if(recyclerView.getVisibility() == View.GONE){
                        recyclerView.setVisibility(View.VISIBLE);
                        empty.setVisibility(View.GONE);
                    }
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

        MoviesCustomAdapter adapter = new MoviesCustomAdapter(this, body);
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
                itemSeleted = "POPULAR_MOVIES";
                APICall(call);
                break;

            case (R.id.highestRated):
                call = service.getTopRatedMovies();
                itemSeleted = "HIGHEST_RATED";
                APICall(call);
                break;

            case (R.id.favorites):
                Cursor queryResults = getFavorites();
                if(queryResults.getCount() > 0){

                    itemSeleted = "FAVORITES";
                    parseToMovieObject(queryResults);

                }else{

                    recyclerView.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);

                }
                break;

            default:
                call = service.getPopularMovies();
                APICall(call);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void parseToMovieObject(Cursor queryResults) {

        int movieIndex = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID);
        int movieTitle = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_TITLE);
        int movieDate = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_DATE);
        int movieRating = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_RATING);
        int movieDescription = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_DESCRIPTION);
        int moviePoster = queryResults.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_POSTER);

        int results = queryResults.getCount();

        MoviesResponse movieResponse = new MoviesResponse();
        Movies [] movieModel = new Movies[results];

        /*parsing elements of the database to a Movie Response model */
            for (int i = 0; i < results; i++) {
                queryResults.moveToPosition(i);

            /*get from db and set values of data to model*/
                movieModel[i] = new Movies();
                movieModel[i].setId(queryResults.getInt(movieIndex));
                movieModel[i].setTitle(queryResults.getString(movieTitle));
                movieModel[i].setRelease_date(queryResults.getString(movieDate));
                movieModel[i].setVote_average(Double.parseDouble(queryResults.getString(movieRating)));
                movieModel[i].setOverview(queryResults.getString(movieDescription));
                movieModel[i].setPoster_path(queryResults.getString(moviePoster));

                movieResponse.setResults(movieModel);
            }


        /*Creating adapter for favorite movies*/
        createAdapter(movieResponse);

    }

    private Cursor getFavorites() {

         try {
            /*fetching data from db and returning*/
            Cursor x = getContentResolver().query(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, null, null, null, null);

            return x;

         } catch (Exception e) {
             Log.e(TAG, "Failed to load data.");
             e.printStackTrace();
             return null;
         }
    }

}

