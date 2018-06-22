package com.example.user.moviesstageone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.moviesstageone.model.MovieReviewsResponse;

/**
 * Created by someone on 6/20/18.
 */

public class CustomReviewsAdapter extends RecyclerView.Adapter<CustomReviewsAdapter.ViewHolder> {


    public CustomReviewsAdapter(MovieReviewsResponse body) {


    }

    @Override
    public CustomReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reviews_layout, parent, false);

        return new CustomReviewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomReviewsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
