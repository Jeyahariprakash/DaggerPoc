package com.hari.daggerpoc.ui.presenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.cache.ResponseCache;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.flow.Layout;
import com.hari.daggerpoc.frameworks.mortar.ScreenComponentFactory;
import com.hari.daggerpoc.service.rest.RestClient;
import com.hari.daggerpoc.service.weather.WeatherService;
import com.hari.daggerpoc.service.weather.response.WeatherResponse;
import com.hari.daggerpoc.ui.activity.MainActivity;
import com.hari.daggerpoc.ui.view.ViewA;

import javax.inject.Inject;

import flow.Flow;
import flow.History;
import flow.path.Path;
import mortar.ViewPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
@Layout(R.layout.screen_a)
public class ScreenA extends Path implements ScreenComponentFactory<MainActivity.Component> {

    @Override
    public Object createComponent(MainActivity.Component parent) {
        return DaggerScreenA_Component.builder().component(parent).build();
    }

    @dagger.Component(dependencies = MainActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(ViewA view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<ViewA> {
        @Inject
        RestClient restClient;

        @Inject
        ResponseCache responseCache;

        Context context;

        public Callback<WeatherResponse> configServiceCallback = new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d("ScreenA","Response data -->"+response.body().toString());
                Flow.get(context).setHistory(History.single(new ScreenB()), Flow.Direction.FORWARD);
                responseCache.setWeatherResponse(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        };


        private String errorMessage = "";

        @Inject
        Presenter() {

        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            context = getView().getContext();
                restClient.getService(WeatherService.class).getConfiguration().enqueue(configServiceCallback);


        }


    }
}

