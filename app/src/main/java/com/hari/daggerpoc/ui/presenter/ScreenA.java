package com.hari.daggerpoc.ui.presenter;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.cache.ResponseCache;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.dagger.FingerPrintModule;
import com.hari.daggerpoc.frameworks.dagger.ModuleDependencies;
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
        return DaggerScreenA_Component.builder().component(parent).fingerPrintModule(new FingerPrintModule()).moduleDependencies(new ModuleDependencies()).build();
    }

    @dagger.Component(dependencies = MainActivity.Component.class,modules = {ModuleDependencies.class, FingerPrintModule.class})
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(ViewA view);

        SharedPreferences sharedPreferences();

        FingerprintManager fingerprintManager();

        KeyguardManager keyguardManager();
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<ViewA> {
        @Inject
        RestClient restClient;

        @Inject
        ResponseCache responseCache;

        @Inject
        SharedPreferences sharedPreferences;

        @Inject
        FingerprintManager fingerprintManager;

        @Inject
        KeyguardManager keyguardManager;

        Context context;

        public Callback<WeatherResponse> configServiceCallback = new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d("ScreenA","Response data -->"+response.body().toString());
                responseCache.setWeatherResponse(response.body());
                Flow.get(context).setHistory(History.single(new ScreenB("object")), Flow.Direction.FORWARD);
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
            Log.e("Screen A","shared preferences-->"+sharedPreferences.getBoolean("HARI",false));
            Log.e("Screen A","fingerprintManager-->"+fingerprintManager);

                restClient.getService(WeatherService.class).getConfiguration().enqueue(configServiceCallback);


        }


    }
}

