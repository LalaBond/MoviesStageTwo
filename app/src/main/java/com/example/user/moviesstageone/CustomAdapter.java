package com.example.user.moviesstageone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.model.MoviesResponse;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
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
    private String posterPath = "http://image.tmdb.org/t/p/w185/";
    int count = 0;



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

        String posterS1, posterS2;

        if(count < getItemCount() * 2) {

            posterS1 = movieList.get(count).getPoster_path();
            posterS2 = movieList.get(count + 1).getPoster_path();

            count = count + 2;

            String posterPath1 = posterPath.concat(posterS1);
            String posterPath2 = posterPath.concat(posterS2);

            Uri x;// = new Uri();
            //holder.imageView1.setImageURI();
            Picasso.with(context).load(posterPath1).into(holder.imageView1);
            Picasso.with(context).load(posterPath2).into(holder.imageView2);
        }

        System.out.println(posterPath);

    }

    @Override
    public int getItemCount() {
        return movieList.size()/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView1, imageView2;


        public ViewHolder(View itemView) {
            super(itemView);

            /*setting up row holders*/

            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);

        }

        @Override
        public void onClick(View view) {

            Intent moviePreview = new Intent(context, MoviePreviewActivity.class);

            moviePreview.putExtra("movies", (Serializable) movieList);
            context.startActivity(moviePreview);

        }
    }


}
