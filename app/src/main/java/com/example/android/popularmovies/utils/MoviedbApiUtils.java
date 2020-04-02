package com.example.android.popularmovies.utils;

public class MoviedbApiUtils {

    final static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    public static String formatDate(String date) {
        String day;
        String month;
        String year;

        String[] dateComponents = date.split("-");

        day = dateComponents[2];
        year = dateComponents[0];

        String monthComponent = dateComponents[1];
        switch (monthComponent) {
            case "01":
                month = "January";
            case "02":
                month = "February";
            case "03":
                month = "March";
            case "04":
                month = "April";
            case "05":
                month = "May";
            case "06":
                month = "June";
            case "07":
                month = "July";
            case "08":
                month = "August";
            case "09":
                month = "September";
            case "10":
                month = "October";
            case "11":
                month = "November";
            default:
                month = "December";
        }

        return day + " " + month + " " + year;
    }

    public static String formatImage(String fileName) {
        return (IMAGE_BASE_URL + fileName);
    }

}
