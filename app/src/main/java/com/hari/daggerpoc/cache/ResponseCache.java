package com.hari.daggerpoc.cache;

import com.hari.daggerpoc.application.App;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.service.weather.response.WeatherResponse;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by jeyahariprakash on 24/08/16.
 */
@Module
@DaggerScope(App.Component.class)
public class ResponseCache {

    @Inject
    public ResponseCache(){

    }

    private WeatherResponse weatherResponse;


    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}
