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
import com.hari.daggerpoc.ui.activity.MainActivity;
import com.hari.daggerpoc.ui.view.ViewB;

import javax.inject.Inject;

import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
@Layout(R.layout.screen_b)
public class ScreenB extends Path implements ScreenComponentFactory<MainActivity.Component> {

    @Override
    public Object createComponent(MainActivity.Component parent) {
        return DaggerScreenB_Component.builder().component(parent).build();
    }

    @dagger.Component(dependencies = MainActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(ViewB view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<ViewB> {

        Context context;

        private String errorMessage = "";

        @Inject
        ResponseCache responseCache;

        @Inject
        Presenter() {

        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            context = getView().getContext();
            Log.d("ScreenB","Response cache -->"+responseCache.getWeatherResponse());

        }


    }
}

