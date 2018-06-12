package com.example.user.moviesstageone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.user.moviesstageone.model.Movies;
import com.example.user.moviesstageone.model.MoviesResponse;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class MoviesCustomAdapter extends RecyclerView.Adapter<MoviesCustomAdapter.ViewHolder> {

    /*Private Fields*/
    private MoviesResponse moviesResponse;
    private Context context;
    private List<Movies> movieList;
    private String posterPath = "http://image.tmdb.org/t/p/w185/";
    int count = 0;




    public MoviesCustomAdapter(Context context, MoviesResponse movies) {
        this.context = context;
        moviesResponse = movies;
        movieList = new ArrayList<>(Arrays.asList(movies.getResults()));
    }

    @Override
    public MoviesCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.poster_gridview, parent, false);

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(MoviesCustomAdapter.ViewHolder holder, int position) {

        String posterS1, posterS2;

        if(count < getItemCount() * 2)
        {

            posterS1 = movieList.get(count).getPoster_path();
            holder.imageView1.setTag(count);

            posterS2 = movieList.get(count + 1).getPoster_path();
            holder.imageView2.setTag(count + 1);

            count = count + 2;

            String posterPath1 = posterPath.concat(posterS1);
            String posterPath2 = posterPath.concat(posterS2);

            Picasso.with(context).load(posterPath1).into(holder.imageView1);
            Picasso.with(context).load(posterPath2).into(holder.imageView2);
        }

        System.out.println(posterPath);

    }

    @Override
    public int getItemCount() {
        return movieList.size()/2;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1, imageView2;

        public ViewHolder(final View itemView) {
            super(itemView);


            /*setting up row holders*/

            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView1.setTag(2);

            imageView2 = itemView.findViewById(R.id.imageView2);

            imageView1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent moviePreview = new Intent(context, MoviePreviewActivity.class);

                    moviePreview.putExtra("movies", movieList.get(getAdapterPosition()));
                    System.out.println("POSITION " + getAdapterPosition());
                    context.startActivity(moviePreview);

                }
            });

            imageView2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent moviePreview = new Intent(context, MoviePreviewActivity.class);

                    moviePreview.putExtra("movies", movieList.get(getAdapterPosition() + 2));
                    System.out.println("POSITION " + getAdapterPosition());
                    context.startActivity(moviePreview);

                }
            });

        }


    }


}
