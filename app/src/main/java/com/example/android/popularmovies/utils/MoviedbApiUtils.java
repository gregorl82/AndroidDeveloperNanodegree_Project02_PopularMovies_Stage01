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
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            default:
                month = "December";
        }

        return day + " " + month + " " + year;
    }

    public static String formatImage(String fileName) {
        return (IMAGE_BASE_URL + fileName);
    }

}
