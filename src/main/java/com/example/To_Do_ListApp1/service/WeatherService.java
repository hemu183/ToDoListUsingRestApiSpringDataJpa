package com.example.To_Do_ListApp1.service;

import com.example.To_Do_ListApp1.APIResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String apikey="80819458fe55ee226009a23c5e1931b0";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_Key&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeatherDetails(String city){

        String FinalApi = API.replace("CITY",city).replace("API_Key", apikey);
        ResponseEntity<WeatherResponse> Response = restTemplate.exchange(FinalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = Response.getBody();
        return body;
    }
}