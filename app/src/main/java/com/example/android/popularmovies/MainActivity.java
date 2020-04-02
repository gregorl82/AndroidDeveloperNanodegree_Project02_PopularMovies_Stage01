package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.utils.JsonUtils;
import com.example.android.popularmovies.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    // TODO (3) Use AsyncTask to replace dummy data
    // TODO (5) Implement menu and sort method

    private ArrayList<Movie> mMovies = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mProgressBar;

    private MovieAdapter mMovieAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_poster_layout);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message);
        mProgressBar = findViewById(R.id.pb_loading_indicator);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMovieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(mMovieAdapter);

        URL initialUrl = NetworkUtils.buildUrl("popular");
        new FetchMoviesTask().execute(initialUrl);
    }

    public class FetchMoviesTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL queryUrl = params[0];
            String json = null;
            try {
                json = NetworkUtils.getResponseFromApi(queryUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            mProgressBar.setVisibility(View.INVISIBLE);
            ArrayList<Movie> foundMovies;
            if (json != null && !json.equals(" ")) {
                foundMovies = JsonUtils.parseJsonResponse(json);
                mMovies = foundMovies;
                mMovieAdapter.setMovieData(mMovies);
                showRecyclerView();
            } else {
                showErrorMessage();
            }

        }
    }

    @Override
    public void onItemClick(Movie movie) {
        Context context = this;
        Class detailActivity = DetailActivity.class;

        Intent intent = new Intent(context, detailActivity);
        intent.putExtra("Clicked movie", movie);

        startActivity(intent);
    }

    private void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

}
