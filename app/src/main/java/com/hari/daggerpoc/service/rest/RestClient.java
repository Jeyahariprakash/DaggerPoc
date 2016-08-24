package com.hari.daggerpoc.service.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hari.daggerpoc.constants.URLConstants;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * RestClient class is a generic service class to create a individual retrofit service object.
 *
 * @author jeyahariprakash
 * @version 1.0
 */
public class RestClient {

    @Inject
    public RestClient(){

    }

    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(URLConstants.DOMAIN).addConverterFactory(GsonConverterFactory.create()).build();

    public <S> S getService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
