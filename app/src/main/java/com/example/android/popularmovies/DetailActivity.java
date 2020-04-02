package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    private ImageView mMoviePosterImageView;
    private TextView mTitleTextView;
    private TextView mReleaseDateTextView;
    private TextView mRatingTextView;
    private TextView mPlotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMoviePosterImageView = findViewById(R.id.iv_detail_poster);
        mTitleTextView = findViewById(R.id.tv_detail_title);
        mReleaseDateTextView = findViewById(R.id.tv_detail_releaseDate);
        mRatingTextView = findViewById(R.id.tv_detail_rating);
        mPlotTextView = findViewById(R.id.tv_detail_plot);

        Intent incomingIntent = getIntent();

        if (incomingIntent.hasExtra("Clicked movie")) {
            mMovie = incomingIntent.getParcelableExtra("Clicked movie");
            populateUI(mMovie);
        }

    }

    private void populateUI(Movie movie) {
        String image = movie.getImage();
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        String rating = movie.getRating() + " out of 10";
        String plot = movie.getPlot();

        Picasso.get().load(image).fit().into(mMoviePosterImageView);

        mTitleTextView.setText(title);
        mReleaseDateTextView.setText(releaseDate);
        mRatingTextView.setText(rating);
        mPlotTextView.setText(plot);
    }

    // TODO (4) Implement menu and back method
}
