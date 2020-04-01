package com.example.android.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android.popularmovies.data.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // COMPLETED (3) Add private variable for Movie array list
    // COMPLETED (4) Create dummy data
    // TODO (10) Use AsyncTask to replace dummy data
    // TODO (13) Implement onClick methods for intents
    // TODO (16) Implement menu and sort method

    private ArrayList<Movie> mMovies;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovies = createMoviesArray();

        mRecyclerView = findViewById(R.id.rv_poster_layout);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMovieAdapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    private ArrayList<Movie> createMoviesArray() {
        ArrayList<Movie> moviesArray = new ArrayList<>();
        Movie movie1 = new Movie("Ad Astra", "2019-09-17", "https://image.tmdb.org/t/p/w185/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg", "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown." ,6);
        Movie movie2 = new Movie("Bloodshot", "2020-02-20", "https://image.tmdb.org/t/p/w185/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg", "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought." ,7.2);
        Movie movie3 = new Movie("John Wick", "2014-10-22", "https://image.tmdb.org/t/p/w185/5vHssUeVe25bMrof1HyaPyWgaP.jpg", "Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him." ,7.2);
        Movie movie4 = new Movie("The Lion King", "2019-07-12", "https://image.tmdb.org/t/p/w185/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg", "Simba idolizes his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",7.1);
        Movie movie5 = new Movie("Bad Boys for Life", "2020-01-15", "https://image.tmdb.org/t/p/w185/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg", "Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel." ,6.7);
        Movie movie6 = new Movie("Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)", "2020-02-05", "https://image.tmdb.org/t/p/w185/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg", "After her breakup with the Joker, Harley Quinn joins forces with singer Black Canary, assassin Huntress, and police detective Renee Montoya to help a young girl named Cassandra, who had a hit placed on her after she stole a rare diamond from crime lord Roman Sionis." ,7.1);

        moviesArray.add(movie1);
        moviesArray.add(movie2);
        moviesArray.add(movie3);
        moviesArray.add(movie4);
        moviesArray.add(movie5);
        moviesArray.add(movie6);

        return moviesArray;
    }
}
