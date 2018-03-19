package com.example.mcs.weatherapp.api.callback;

import com.example.mcs.weatherapp.model.Weather;

/**
 * Created by Armando on 3/19/18.
 */

public interface OpenWeatherMapCallback extends BaseCallback {
    void onResponse(Weather weather);
}
