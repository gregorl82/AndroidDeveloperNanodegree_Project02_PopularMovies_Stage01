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

    private ArrayList<Movie> mMovies;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onItemClick(Movie movie);
    }

    public MovieAdapter(ArrayList<Movie> movies, MovieAdapterOnClickHandler handler) {
        this.mMovies = movies;
        this.mClickHandler = handler;
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

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPosterImageView;
        private TextView mTitleTextView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = view.findViewById(R.id.iv_movie_poster);
            mTitleTextView = view.findViewById(R.id.tv_movie_title);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMovies.get(adapterPosition);
            mClickHandler.onItemClick(movie);
        }
    }

}
