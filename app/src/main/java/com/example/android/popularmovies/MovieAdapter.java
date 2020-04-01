package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    // COMPLETED (2) Add fields for Movie array
    private ArrayList<Movie> mMovies;
    // COMPLETED (5) Modify implemented methods to use movie_item.xml
    // TODO (12) Add onClickListener to view holder

    public MovieAdapter(ArrayList<Movie> mMovies) {
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idOfLayout = R.layout.movie_item;

        boolean shouldAttachImmediatelyToParent = false;

        View view = LayoutInflater.from(context).inflate(idOfLayout, parent, shouldAttachImmediatelyToParent);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        Movie currentMovie = mMovies.get(position);
        String title = currentMovie.getTitle();
        String image = currentMovie.getImage();

        Picasso.get().load(image).fit().into(holder.mPosterImageView);
        holder.mTitleTextView.setText(title);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        // COMPLETED (6) Add fields for main activity views

        private ImageView mPosterImageView;
        private TextView mTitleTextView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = view.findViewById(R.id.iv_movie_poster);
            mTitleTextView = view.findViewById(R.id.tv_movie_title);
        }
    }

}
