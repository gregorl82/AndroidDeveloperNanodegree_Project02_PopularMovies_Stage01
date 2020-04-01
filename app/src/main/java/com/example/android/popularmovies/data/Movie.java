package com.example.android.popularmovies.data;

public class Movie {

    // COMPLETED (1) Add attributes and constructor for a Movie object
    private String title;
    private String releaseDate;
    private String image;
    private String plot;
    private double rating;

    public Movie(String title, String releaseDate, String image, String plot, double rating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.image = image;
        this.plot = plot;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }

    public String getPlot() {
        return plot;
    }

    public double getRating() {
        return rating;
    }
}
