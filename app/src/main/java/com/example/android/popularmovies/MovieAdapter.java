package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private final int NUM_OF_ITEMS = 20;

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idOfLayout = R.layout.test_item;

        boolean shouldAttachImmediatelyToParent = false;

        View view = LayoutInflater.from(context).inflate(idOfLayout, parent, shouldAttachImmediatelyToParent);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String text = String.valueOf(position);
        holder.mTestItemTextView.setText(text);
    }

    @Override
    public int getItemCount() {
        return NUM_OF_ITEMS;
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView mTestItemTextView;
        public MovieAdapterViewHolder(View view) {
            super(view);
            mTestItemTextView = view.findViewById(R.id.tv_test_item);
        }
    }

}
