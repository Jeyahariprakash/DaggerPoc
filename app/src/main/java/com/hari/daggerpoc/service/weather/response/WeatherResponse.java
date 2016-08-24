package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class WeatherResponse {
    private Endpoint endpoint;

    /**
     *
     * @return
     * The endpoint
     */
    public Endpoint getEndpoint() {
        return endpoint;
    }

    /**
     *
     * @param endpoint
     * The endpoint
     */
    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }


    @Override
    public String toString() {
        return "WeatherResponse{" +
                "endpoint=" + endpoint +
                '}';
    }
}
