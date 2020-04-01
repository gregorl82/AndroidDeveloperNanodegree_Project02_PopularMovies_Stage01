package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

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

    protected Movie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        image = in.readString();
        plot = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(image);
        parcel.writeString(plot);
        parcel.writeDouble(rating);
    }
}
