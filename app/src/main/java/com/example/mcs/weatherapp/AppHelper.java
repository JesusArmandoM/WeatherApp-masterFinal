package com.example.mcs.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rmay on 24/02/2017.
 */

public class AppHelper {
    public static boolean checkConn(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        if (!networkInfo.isConnected())
            return false;
        if (!networkInfo.isAvailable())
            return false;
        return true;
    }

}
