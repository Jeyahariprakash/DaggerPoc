package com.hari.daggerpoc.service.weather;


import com.hari.daggerpoc.constants.URLConstants;
import com.hari.daggerpoc.service.weather.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public interface WeatherService {

    @GET(URLConstants.WEATHER)
    Call<WeatherResponse> getConfiguration();

}
