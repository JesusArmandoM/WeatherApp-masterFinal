package com.example.mcs.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcs.weatherapp.api.call.OpenWeatherMapApiCaller;
import com.example.mcs.weatherapp.api.callback.OpenWeatherMapCallback;
import com.example.mcs.weatherapp.api.constant.OpenWeatherMapConstants;
import com.example.mcs.weatherapp.model.Weather;

public class ResultActivity extends AppCompatActivity implements OpenWeatherMapCallback {

    // TODO: make this views to be dataBinded
    TextView main;
    TextView description;
    TextView temperature;
    TextView wind;
    TextView cloudness;
    TextView presure;
    TextView humildity;
    TextView sunrise;
    TextView measure;

    public static Intent newIntent(Context packageContext, int answerIsTrue) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(OpenWeatherMapConstants.ZIP_VALUE, answerIsTrue);
        return intent;
    }

    public void downloadData(String zipCode) {
        if (!AppHelper.checkConn(this)) {
            Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show();
            finish();
        }
        OpenWeatherMapApiCaller service = new OpenWeatherMapApiCaller(this);
        service.downloadData(zipCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        main = findViewById(R.id.textView4);
        description = findViewById(R.id.editText2);
        temperature = findViewById(R.id.textView5);
        wind = findViewById(R.id.textView111);
        cloudness = findViewById(R.id.textView222);
        presure = findViewById(R.id.textView333);
        humildity = findViewById(R.id.textView444);
        sunrise = findViewById(R.id.textView555);
        measure = findViewById(R.id.textViewLast);

        Integer zipNumber = getIntent().getIntExtra(OpenWeatherMapConstants.ZIP_VALUE, 0);
        downloadData(zipNumber.toString());

    }


    @Override
    public void onResponse(Weather weather) {
        main.setText(weather.getCondition());
        description.setText(weather.getDescription());
        temperature.setText(weather.getDegrees().substring(0, 3));
        wind.setText(weather.getWind());
        cloudness.setText(weather.getCloudiness());
        presure.setText(weather.getPressure());
        humildity.setText(weather.getHumidity());
        sunrise.setText(weather.getSunrise());
        measure.setText(getString(R.string.measured) + weather.getMeasured());
    }

    @Override
    public void onError() {
        // TODO: Implement OpenWeatherMapCallback.onError
    }
}
