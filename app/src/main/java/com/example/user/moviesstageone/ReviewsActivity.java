package com.example.user.moviesstageone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.user.moviesstageone.model.MovieReviewsResponse;
import com.example.user.moviesstageone.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Intent intent = getIntent();

        int movieId = (int) intent.getSerializableExtra("movieId");

        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);

        Call<MovieReviewsResponse> call = service.getMovieReviews();

        APICall(call);

    }

    private void createAdapter(MovieReviewsResponse response) {

        RecyclerView reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        CustomReviewsAdapter reviewsAdapter = new CustomReviewsAdapter(this, response);

        reviewsRecyclerView.setLayoutManager(linearLayout);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }


    /*Method to call api service and get data*/
        private void APICall(Call<MovieReviewsResponse> call) {
            try {

                call.enqueue(new Callback<MovieReviewsResponse>() {

                    /*If call was success create adapter with data received*/
                    public void onResponse(Call<MovieReviewsResponse> call, Response<MovieReviewsResponse> response) {

                        createAdapter(response.body());
                    }

                    public void onFailure(Call<MovieReviewsResponse> call, Throwable t) {
                        System.out.println("ERROR IN RESPONSE: " + t.getMessage());
                    }
                });

            }catch (Exception e ){

                System.out.println("Error in service: " + e);
            }
        }

}
