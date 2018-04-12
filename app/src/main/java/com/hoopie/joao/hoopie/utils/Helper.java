package com.hoopie.joao.hoopie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hoopie.joao.hoopie.R;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class with code that might be reused for common tasks.
 */

public class Helper {

    /**
     * Different status we can get from http responses
     */
    public enum HttpResponses {
        SUCCESS, SERVER_ERROR, NETWORK_ERROR;
    }

    public final static String HOOP_BASE_URL = "http://files.hoop.co.uk/";
    public final static String LOG_TAG = "tagg";

    /**
     * Shortcut to log something into the console.
     */
    public static void log(Object logThis) {
        Log.i(LOG_TAG, String.valueOf(logThis));
    }


    /**
     * Build a Retrofit instance with custom configuration.
     */
    public Retrofit makeRetrofit() {
        // Make an OkHttpClient builder
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        // Create an interceptor to log every request's body/headers and add it to our builder
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        // Build the OkHttpClient instance
        OkHttpClient client = builder.build();

        // Build a Retrofit instance using our custom OkHttpClient with the interceptor
        // Also hardcode foursquare base url because it's the only one being used
        return new Retrofit.Builder()
                .baseUrl(HOOP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(client)
                .build();
    }

    /**
     * Get SharedPreferences object.
     */
    public SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.preferences_key), Context.MODE_PRIVATE);
    }

    /**
     * Take object and return String in json format
     */
    public String serializeObj(Object object) {
        return new Gson().toJson(object);
    }

    /**
     * Take ISO date string and return a DateTime object.
     */
    public DateTime getDateTimeObject(String dateTimeISO) {
        try {
            return ISODateTimeFormat.dateTimeNoMillis().parseDateTime(dateTimeISO);
        } catch (IllegalArgumentException e) {
            return ISODateTimeFormat.dateTime().parseDateTime(dateTimeISO);
        }
    }

    /**
     * Take ISO date string and convert into given format.
     */
    public String convertIsoToSimpleDate(String dateTimeISO, String outputFormat) {
        return getDateTimeObject(dateTimeISO).toString(outputFormat);
    }

    /**
     * Returns the correct suffix for the last digit (1st, 2nd, .. , 13th, .. , 23rd)
     */
    public String getLastDigitSufix(int number) {
        switch( (number<20) ? number : number%10 ) {
            case 1 : return "st";
            case 2 : return "nd";
            case 3 : return "rd";
            default : return "th";
        }
    }

    /**
     * Make text for the activity day.
     */
    public String makeDateString(String startIso) {
        return convertIsoToSimpleDate(startIso, "EEEE, d'" +
                getLastDigitSufix(getDateTimeObject(startIso).getDayOfMonth()) + "' 'of' MMMM");
    }

    /**
     * Make text for the activity hour.
     */
    public String makeHourString(String startIso, String endIso) {
        return convertIsoToSimpleDate(startIso, "HH:mm") + " - " +
                convertIsoToSimpleDate(endIso, "HH:mm");
    }

    /**
     * Make text for the activity ages.
     */
    public String makeAgeString(String ages) {
        return "All Ages".equals(ages) ? ages : ages + " old";
    }

    /**
     * Make text for the activity location.
     */
    public String makeLocationString(String placeName, String address, String postcode) {
        return placeName + " - " + address + ", " + postcode;
    }

    /**
     * Build url to query google static map API
     */
    public String getGoogleMapsImageUrl(double lat, double lon, int width, int height) {
        float scale = 1;
        // adjust the scale of the image (that we want it to return) based on our imageView dimensions
        while (width / scale > 640) {
            scale = scale + 0.1f;
        }
        String s = "https://maps.googleapis.com/maps/api/staticmap?center=" + lat + "," + lon +
                "&zoom=13&size=" + (int) (width / scale) + "x" + (int) (height / scale) + "&sensor=false" +
                "&markers=color:red%7Clabel:C%7C" + lat + "," + lon +
                "&scale=" + scale +
                "&style=feature:all|saturation:-75|visibility" +
                "&style=feature:poi|visibility:off" +
                "&style=feature:administrative.locality|visibility:off" +
                "&style=feature:water|hue:0x1A76B7|saturation:40|lightness:-10" +
                "&key=AIzaSyAAjIS5mlS1LWFnrdjnlVtxpB4rTKTgIoY";
        log(s);
        return s;
    }

}
