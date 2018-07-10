package com.example.user.moviesstageone.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.moviesstageone.R;
import com.example.user.moviesstageone.model.MovieTrailerResponse;
import com.example.user.moviesstageone.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by someone on 7/2/18.
 */

public class TrailerListAdapter extends RecyclerView.Adapter<TrailerListAdapter.ViewHolder> {

    private List<Trailer> trailers;
    private MovieTrailerResponse response;
    private Context context;



    public TrailerListAdapter(Context context, MovieTrailerResponse response) {

        this.response = response;
        this.context = context;
        trailers =  new ArrayList<>(Arrays.asList(response.results));

    }

    @Override
    public TrailerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trailer_item_layout, parent, false);

        return new TrailerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerListAdapter.ViewHolder holder, int position) {

        String thumbnailKey = trailers.get(position).getKey();
        String youtubeThumbPath = "https://img.youtube.com/vi/" + thumbnailKey + "/0.jpg";

        Picasso.with(context).load(youtubeThumbPath).into(holder.thumbnailImageView);
    }


    @Override
    public int getItemCount() {
        return trailers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnailImageView;
        public ViewHolder(View itemView) {
            super(itemView);


            thumbnailImageView = itemView.findViewById(R.id.trailerThumbnail);
            thumbnailImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                String youtubeLink = "https://www.youtube.com/watch?v=" + trailers.get(getAdapterPosition()).getKey();

                Intent trailerActionView = new Intent(Intent.ACTION_VIEW);
                trailerActionView.setData(Uri.parse(youtubeLink));
                context.startActivity(trailerActionView);

                }
            });






        }
    }
}
