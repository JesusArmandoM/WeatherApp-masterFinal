package com.example.mcs.weatherapp.model;

import com.example.mcs.weatherapp.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Armando on 3/19/18.
 */

public class Weather {

    private String condition = "";
    private String description = "";
    private String degrees = "";
    private String wind = "";
    private String cloudiness = "";
    private String pressure = "";
    private String humidity = "";
    private String sunrise = "";
    private String measured = "";

    public Weather() {
    }

    public Weather(JSONObject json) {
        fromJson(json);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getMeasured() {
        return measured;
    }

    public void setMeasured(String measured) {
        this.measured = measured;
    }

    public void fromJson(JSONObject json) {

        parseCondition(json);
        parseMeasures(json);
        parseCloudiness(json);
        parseWind(json);
        parseSunrise(json);
        parseMeasure(json);

    }

    private void parseCondition(JSONObject json) {
        try {
            if (!json.isNull("weather")) {
                JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
                condition = weather.getString("main");
                description = weather.getString("description");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseMeasures(JSONObject json) {
        try {
            if (!json.isNull("main")) {
                JSONObject measures = json.getJSONObject("main");
                degrees = "" + Utils.kelvinToFarenheit(measures.getDouble("temp"));
                pressure = "" + measures.getDouble("pressure");
                humidity = "" + measures.getDouble("humidity");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseCloudiness(JSONObject json) {
        try {
            if (!json.isNull("clouds")) {
                JSONObject measures = json.getJSONObject("clouds");
                cloudiness = "" + measures.getDouble("all");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseWind(JSONObject json) {
        try {
            if (!json.isNull("wind")) {
                JSONObject measures = json.getJSONObject("wind");
                wind = "" + measures.getDouble("speed");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseSunrise(JSONObject json) {
        try {
            if (!json.isNull("sys")) {
                JSONObject measures = json.getJSONObject("sys");
                sunrise = Utils.timeStampToMilitarTime(measures.getLong("sunrise"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseMeasure(JSONObject json) {
        try {
            if (!json.isNull("name")) {
                measured = json.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
