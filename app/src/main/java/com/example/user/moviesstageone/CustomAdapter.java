package com.example.user.moviesstageone;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.moviesstageone.model.MoviesResponse;

import java.util.List;

/**
 * Created by Lassarie Rosa on 5/4/2018.
 */

public class CustomAdapter extends BaseAdapter {

    /*Private Fields*/
    private List<MoviesResponse> movies;

    public CustomAdapter(List<MoviesResponse> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
