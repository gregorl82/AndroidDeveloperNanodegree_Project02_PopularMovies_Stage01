package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.utils.JsonUtils;
import com.example.android.popularmovies.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private ArrayList<Movie> mMovies = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mProgressBar;

    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_poster_layout);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message);
        mProgressBar = findViewById(R.id.pb_loading_indicator);

        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(mMovieAdapter);

        getDataFromNetwork("popular");
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

    private void getDataFromNetwork(String endpoint) {
        URL initialUrl = NetworkUtils.buildUrl(endpoint);
        new FetchMoviesTask().execute(initialUrl);
    }

    public class FetchMoviesTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL queryUrl = params[0];
            String json = null;

            if (isOnline()) {
                try {
                    json = NetworkUtils.getResponseFromApi(queryUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            mProgressBar.setVisibility(View.INVISIBLE);
            ArrayList<Movie> foundMovies;
            if (json != null) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItem = item.getItemId();
        switch (selectedItem) {
            case R.id.highest_rated:
                mMovieAdapter.setMovieData(null);
                getDataFromNetwork("top_rated");
                return true;
            case R.id.most_popular:
                mMovieAdapter.setMovieData(null);
                getDataFromNetwork("popular");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
