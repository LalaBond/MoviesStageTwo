package com.example.user.moviesstageone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.moviesstageone.R;
import com.example.user.moviesstageone.model.MovieReviewsResponse;
import com.example.user.moviesstageone.model.Reviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by someone on 6/20/18.
 */

public class CustomReviewsAdapter extends RecyclerView.Adapter<CustomReviewsAdapter.ViewHolder> {


    private List<Reviews> movieReviewsList;
    //private MovieReviewsResponse movieResponse;
    private Context context;

    public CustomReviewsAdapter(Context context, MovieReviewsResponse movieReviews) {

        this.context = context;
        movieReviewsList = new ArrayList<>(Arrays.asList(movieReviews.getResults()));
        System.out.println(movieReviewsList);
    }

    @Override
    public CustomReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reviews_layout, parent, false);

        return new CustomReviewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomReviewsAdapter.ViewHolder holder, int position) {

        holder.authorTextView.setText(movieReviewsList.get(position).getAuthor());
        holder.descriptionTextView.setText(movieReviewsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView authorTextView, descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            authorTextView = itemView.findViewById(R.id.authorTextView);
            descriptionTextView = itemView.findViewById(R.id.reviewTextView);

        }
    }
}
