package com.example.mcs.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mcs.weatherapp.api.call.OpenWeatherMapApiCaller;
import com.example.mcs.weatherapp.api.callback.OpenWeatherMapCallback;
import com.example.mcs.weatherapp.api.constant.OpenWeatherMapConstants;
import com.example.mcs.weatherapp.model.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity implements OpenWeatherMapCallback {

    public static Intent newIntent(Context packageContext, int answerIsTrue) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(OpenWeatherMapConstants.ZIP_VALUE, answerIsTrue);
        return intent;
    }

    public void downloadData(String zipCode) {

        if (!AppHelper.checkConn(this)) {

            Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show();
            finish();
            return;

        }


        String END_POINT = OpenWeatherMapConstants.BASE_URL +"?"+ OpenWeatherMapConstants.ZIP_QUERY_PARAMETER+"="+
                zipCode + OpenWeatherMapConstants.DEFAULT_LANGUAGE_PREFIX +"&"+ OpenWeatherMapConstants.API_KEY_QUERY_PARAMETER+"="+
                OpenWeatherMapConstants.API_KEY_VALUE;
        // HERE TO CALL CALLER

        // QUEDATE CON UNA REFERENCIA
        //OpenWeatherMapApiCaller temp = new OpenWeatherMapApiCaller(this);
        // sacalo del intent
      //  temp.downloadData(zipCode);


        //String Url = getString(R.string.url) + "zip=30030,us";//&AppID=293f015aecea8bb0e6fd9444d4420beb";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                END_POINT,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {

                            Weather weather = new Weather(response);

                            main.setText(weather.getCondition());
                             description.setText(weather.getDescription());
                             temperature.setText(weather.getDegrees().substring(0,3));
                             wind.setText(weather.getWind());
                             cloudness.setText(weather.getCloudiness());
                             presure.setText(weather.getPressure());
                             humildity.setText(weather.getHumidity());
                            sunrise.setText(weather.getSunrise());
                            measure.setText("Measured: " +weather.getMeasured() );



                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1));
        VolleyRequestMgr.getInstance(this).getRequestQueue().add(jsonObjectRequest);


    }

    TextView main;
    TextView description;
    TextView temperature;
    TextView wind;
    TextView cloudness;
    TextView presure;
    TextView humildity;
    TextView sunrise;
    TextView measure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


         main = findViewById(R.id.textView4);
        description = findViewById(R.id.editText2);
        temperature = findViewById(R.id.textView5);
        wind= findViewById(R.id.textView111);
        cloudness= findViewById(R.id.textView222);
        presure = findViewById(R.id.textView333);
        humildity= findViewById(R.id.textView444);
         sunrise= findViewById(R.id.textView555);
         measure= findViewById(R.id.textViewLast);



        Integer zipNumber = getIntent().getIntExtra(OpenWeatherMapConstants.ZIP_VALUE, 0);
        downloadData(zipNumber.toString());







    }


    @Override
    public void onResponse(Weather weather) {
        // TODO: PRINTEA TODO

        Log.d("OnResponse!!!",weather.getDescription());


    }

    @Override
    public void onError() {
        // TODO: REGRESA A LA ANTERIOR
    }
}
