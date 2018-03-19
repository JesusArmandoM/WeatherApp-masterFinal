package com.example.mcs.weatherapp.api.call;

import android.content.Context;
import android.net.Uri;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mcs.weatherapp.VolleyRequestMgr;
import com.example.mcs.weatherapp.api.callback.BaseCallback;
import com.example.mcs.weatherapp.api.constant.OpenWeatherMapConstants;
import com.example.mcs.weatherapp.api.callback.OpenWeatherMapCallback;
import com.example.mcs.weatherapp.model.Weather;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by Armando on 3/19/18.
 */

public class OpenWeatherMapApiCaller implements BaseApiCall {
    WeakReference<BaseCallback> callback;

    public OpenWeatherMapApiCaller(BaseCallback callback) {
        this.callback = new WeakReference<BaseCallback>(callback);
    }

    public OpenWeatherMapApiCaller() {
        callback = null;
    }

    @Override
    public void setCallback(BaseCallback callback) {
        this.callback = new WeakReference<BaseCallback>(callback);
    }

    public void downloadData(String zipcode) {

        Uri END_POINT = Uri
                .parse(OpenWeatherMapConstants.BASE_URL)
                .buildUpon()
                .appendQueryParameter(OpenWeatherMapConstants.ZIP_QUERY_PARAMETER,
                        zipcode + OpenWeatherMapConstants.DEFAULT_LANGUAGE_PREFIX)
                .appendQueryParameter(OpenWeatherMapConstants.API_KEY_QUERY_PARAMETER,
                        OpenWeatherMapConstants.API_KEY_VALUE)
                .build();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                END_POINT.toString(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            Weather weather = new Weather(response);
                            ((OpenWeatherMapCallback) callback.get()).onResponse(weather);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((OpenWeatherMapCallback) callback.get()).onError();
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1));
        if (callback.get() != null) {
            VolleyRequestMgr.getInstance((Context) callback.get()).getRequestQueue().add(jsonObjectRequest);
        }

    }

}
