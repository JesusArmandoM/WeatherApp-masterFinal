package com.example.mcs.weatherapp.api.call;

import com.example.mcs.weatherapp.api.callback.BaseCallback;

/**
 * Created by Armando on 3/19/18.
 */

public interface BaseApiCall {
    // All BaseApiCall are meant to have their own Callback
    void setCallback(BaseCallback callback);
}
