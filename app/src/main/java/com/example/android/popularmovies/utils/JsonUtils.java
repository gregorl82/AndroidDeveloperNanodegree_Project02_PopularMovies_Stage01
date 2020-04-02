package com.example.android.popularmovies.utils;

import com.example.android.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Movie> parseJsonResponse(String json) {

        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject fullResponse;
        try {
            fullResponse = new JSONObject(json);

            JSONArray results = fullResponse.getJSONArray("results");

            int resultsSize = results.length();
            for (int i = 0; i < resultsSize; i++) {

                String title;
                String releaseDate;
                String image;
                String plot;
                double rating;

                JSONObject movieObject = results.getJSONObject(i);

                title = movieObject.getString("original_title");
                plot = movieObject.getString("overview");
                rating = movieObject.getDouble("vote_average");
                releaseDate = MoviedbApiUtils.formatDate(movieObject.getString("release_date"));
                image = MoviedbApiUtils.formatImage(movieObject.getString("poster_path"));

                Movie movie = new Movie(title, releaseDate, image, plot, rating);

                movies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

}
