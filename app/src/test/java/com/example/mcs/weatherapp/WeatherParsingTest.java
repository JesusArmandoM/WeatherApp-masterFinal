package com.example.mcs.weatherapp;

import org.json.JSONObject;
import org.junit.Test;

import com.example.mcs.weatherapp.model.Weather;
import com.example.mcs.weatherapp.util.Utils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Armando on 3/19/18.
 */

public class WeatherParsingTest {

    @SuppressWarnings("deprecation")
    @Test
    public void kelvin_farenheit_conversion_isCorrect() throws Exception {
        // assertEquals(56.3, Utils.kelvinToFarenheit(286.65),0.3);
        System.out.println(Utils.kelvinToFarenheit(265));
        System.out.println(Utils.kelvinToFarenheit(278));
        assertEquals(17.33, Utils.kelvinToFarenheit(265),0.3);
        assertEquals(40.73, Utils.kelvinToFarenheit(278),0.3);
    }

    @Test
    public void timestamp_militar_time_conversion_isCorrect() throws Exception {
        // assertEquals("07:41:34", Utils.timeStampToMilitarTime(1521459694));
        System.out.println(Utils.timeStampToMilitarTime(1521456666));
        System.out.println(Utils.timeStampToMilitarTime(1372339860));
        assertEquals("06:51:06", Utils.timeStampToMilitarTime(1521456666));
        assertEquals("09:31:00", Utils.timeStampToMilitarTime(1372339860));
    }

    @Test
    public void weather_parsing_isCorrect() throws Exception {
        Weather mockModel = mockWeather();
        JSONObject mockedResponse = new JSONObject(mockString());
        Weather parsedModel = new Weather(mockedResponse);
        assertEquals(mockModel.getCloudiness(), parsedModel.getCloudiness());
        assertEquals(mockModel.getCondition(), parsedModel.getCondition());
        assertEquals(mockModel.getDegrees(), parsedModel.getDegrees());
        assertEquals(mockModel.getDescription(), parsedModel.getDescription());
        assertEquals(mockModel.getHumidity(), parsedModel.getHumidity());
        assertEquals(mockModel.getMeasured(), parsedModel.getMeasured());
        assertEquals(mockModel.getPressure(), parsedModel.getPressure());
        assertEquals(mockModel.getSunrise(), parsedModel.getSunrise());
        assertEquals(mockModel.getWind(), parsedModel.getWind());
    }

    private Weather mockWeather() {
        Weather mockModel = new Weather();
        mockModel.setCloudiness("75");
        mockModel.setCondition("Mist");
        mockModel.setDegrees("56.3");
        mockModel.setDescription("mist");
        mockModel.setHumidity("100");
        mockModel.setPressure("1008");
        mockModel.setSunrise("07:41:34");
        mockModel.setWind("2.67");
        mockModel.setMeasured("Decatur");
        return mockModel;
    }

    private String mockString() {
        return "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": -84.3,\n" +
                "    \"lat\": 33.77\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 701,\n" +
                "      \"main\": \"Mist\",\n" +
                "      \"description\": \"mist\",\n" +
                "      \"icon\": \"50n\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 500,\n" +
                "      \"main\": \"Rain\",\n" +
                "      \"description\": \"light rain\",\n" +
                "      \"icon\": \"10n\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 741,\n" +
                "      \"main\": \"Fog\",\n" +
                "      \"description\": \"fog\",\n" +
                "      \"icon\": \"50n\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 286.65,\n" +
                "    \"pressure\": 1008,\n" +
                "    \"humidity\": 100,\n" +
                "    \"temp_min\": 285.15,\n" +
                "    \"temp_max\": 288.15\n" +
                "  },\n" +
                "  \"visibility\": 9656,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 2.67,\n" +
                "    \"deg\": 61.5027\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 75\n" +
                "  },\n" +
                "  \"dt\": 1521457500,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 1,\n" +
                "    \"id\": 748,\n" +
                "    \"message\": 0.0044,\n" +
                "    \"country\": \"US\",\n" +
                "    \"sunrise\": 1521459694,\n" +
                "    \"sunset\": 1521503307\n" +
                "  },\n" +
                "  \"id\": 420010029,\n" +
                "  \"name\": \"Decatur\",\n" +
                "  \"cod\": 200\n" +
                "}";
    }

}
