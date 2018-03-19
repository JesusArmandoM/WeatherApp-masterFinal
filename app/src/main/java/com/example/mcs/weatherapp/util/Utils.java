package com.example.mcs.weatherapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Armando on 3/19/18.
 */

public class Utils {

    public static double kelvinToFarenheit(double input) {
        return ((input-273) * 1.8) + 32;
    }

    public static String timeStampToMilitarTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(timestamp * 1000L));
    }

}
