package com.example.user.moviesstageone;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final int TASK_LOADER_ID = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<MoviesResponse> call = service.getPopularMovies();

        APICall(call);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
                //getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, null);

            default:
                call = service.getPopularMovies();
                break;
        }
        APICall(call);

        return super.onOptionsItemSelected(item);
    }

    private Cursor getFavorites() {

         try {
            /*fetching data from db and returning*/
            Cursor x = getContentResolver().query(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI, null, null, null, null);

            return x;

         } catch (Exception e) {

                    Log.e(TAG, "Failed to load data.");
                    System.out.println("$lala: " + e);
                    e.printStackTrace();
                    return null;
         }
    }

//    @SuppressLint("StaticFieldLeak")
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//
//        return new AsyncTaskLoader<Cursor>(this) {
//
//            // Initialize a Cursor, this will hold all the task data
//            Cursor mTaskData = null;
//
//            // onStartLoading() is called when a loader first starts loading data
//            @Override
//            protected void onStartLoading() {
//                if (mTaskData != null) {
//                    // Delivers any previously loaded data immediately
//                    deliverResult(mTaskData);
//                } else {
//                    // Force a new load
//                    forceLoad();
//                }
//            }
//
//            // loadInBackground() performs asynchronous loading of data
//            @Override
//            public Cursor loadInBackground() {
//                // Will implement to load data
//
//                // Query and load all task data in the background; sort by priority
//                // [Hint] use a try/catch block to catch any errors in loading data
//
//                try {
//            /*fetching data from db and returning*/
//                    return getContentResolver().query(FavoriteMoviesContract.FavoriteMoviesEntry.CONTENT_URI,
//                            null, null, null, null);
//
//                } catch (Exception e) {
//                    System.out.println("$lala: " + e);
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//
//            // deliverResult sends the result of the load, a Cursor, to the registered listener
//            public void deliverResult(Cursor data) {
//                mTaskData = data;
//                super.deliverResult(data);
//            }
//        };
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }


    }

