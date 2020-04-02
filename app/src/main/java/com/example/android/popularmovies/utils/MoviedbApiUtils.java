package com.example.android.popularmovies.utils;

public class MoviedbApiUtils {

    final static String IMAGE_URL = "https://image.tmdb.org/t/p/w185";

//    public static String formatDate(String date) {
//
//    }

    public static String formatImage(String fileName) {
        return (IMAGE_URL + fileName);
    }

}
