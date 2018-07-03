package com.example.user.moviesstageone;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.user.moviesstageone.model.MovieTrailerResponse;
import com.example.user.moviesstageone.model.Trailer;

import java.util.List;

/**
 * Created by someone on 7/2/18.
 */

public class TrailerListAdapter extends RecyclerView.Adapter {

    private List<Trailer> trailers;
    private MovieTrailerResponse response;


    public TrailerListAdapter(MovieTrailerResponse response) {

        this.response = response;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
