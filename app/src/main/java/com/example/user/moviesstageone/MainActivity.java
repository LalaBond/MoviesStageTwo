package com.example.user.moviesstageone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*Private Fields*/
    private List<MoviesResponse> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        CustomAdapter adapter = new CustomAdapter(movies);
        recyclerView.setLayoutManager(gridLayout);


    }
}
