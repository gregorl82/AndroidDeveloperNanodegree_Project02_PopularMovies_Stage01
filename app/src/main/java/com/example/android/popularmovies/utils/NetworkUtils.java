package com.example.android.popularmovies.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String API_BASE_URL = "https://api.themoviedb.org/3/movie";

    final static String API_PARAM = "api_key";
    final static String API_KEY = "************************";

    final static String LANG_PARAM = "language";
    final static String LANG_VALUE = "en-US";

    final static String PAGE_PARAM = "page";
    final static String PAGE_VALUE = "1";

    public static URL buildUrl(String endpoint) {
        Uri builtUri = Uri.parse(API_BASE_URL).buildUpon()
                .appendPath(endpoint)
                .appendQueryParameter(API_PARAM, API_KEY)
                .appendQueryParameter(LANG_PARAM, LANG_VALUE)
                .appendQueryParameter(PAGE_PARAM,PAGE_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromApi(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
