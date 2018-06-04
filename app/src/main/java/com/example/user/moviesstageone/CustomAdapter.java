package com.example.user.moviesstageone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.model.MoviesResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    /*Private Fields*/
    private MoviesResponse moviesResponse;
    private Context context;
    private List<Movies> movieList;

    public CustomAdapter(Context context, MoviesResponse movies) {
        this.context = context;
        moviesResponse = movies;
        movieList = new ArrayList<>(Arrays.asList(movies.getResults()));
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.poster_gridview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
