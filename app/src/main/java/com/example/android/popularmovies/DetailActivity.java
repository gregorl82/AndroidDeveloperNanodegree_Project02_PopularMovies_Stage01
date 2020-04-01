package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    // COMPLETED (11) Assign dummy mMovie and populate UI
    // TODO (14) Use intent to assign mMovie

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

        mMovie = new Movie("The Lion King", "2019-07-12", "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg", "Simba idolizes his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",7.1);

        populateUI(mMovie);
    }

    private void populateUI(Movie movie) {
        String image = movie.getImage();
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        String rating = String.valueOf(movie.getRating());
        String plot = movie.getPlot();

        Picasso.get().load(image).fit().into(mMoviePosterImageView);

        mTitleTextView.setText(title);
        mReleaseDateTextView.setText(releaseDate);
        mRatingTextView.setText(rating);
        mPlotTextView.setText(plot);
    }
}
