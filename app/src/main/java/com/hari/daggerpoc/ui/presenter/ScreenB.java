package com.hari.daggerpoc.ui.presenter;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.cache.ResponseCache;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.dagger.FingerPrintModule;
import com.hari.daggerpoc.frameworks.flow.Layout;
import com.hari.daggerpoc.frameworks.mortar.ScreenComponentFactory;
import com.hari.daggerpoc.ui.activity.MainActivity;
import com.hari.daggerpoc.ui.view.ViewB;

import javax.inject.Inject;

import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
@Layout(R.layout.screen_b)
public class ScreenB extends Path implements ScreenComponentFactory<MainActivity.Component> {

    private String value = "";

    public ScreenB(String value) {
        this.value = value;
    }

    @Override
    public Object createComponent(MainActivity.Component parent) {
        return DaggerScreenB_Component.builder().component(parent).fingerPrintModule(new FingerPrintModule()).module(new Module()).build();
    }

    @dagger.Component(dependencies = MainActivity.Component.class,modules = {FingerPrintModule.class,Module.class})
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(ViewB view);

        FingerprintManager fingerprintManager();

        KeyguardManager keyguardManager();
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<ViewB> {

        Context context;

        @Inject
        FingerprintManager fingerprintManager;

        private String errorMessage = "";

        @Inject
        ResponseCache responseCache;

        String value = "";

        @Inject
        Presenter(String value) {
            this.value= value;
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            context = getView().getContext();
            Log.d("ScreenB","Finger print manager-->"+fingerprintManager);
            Log.d("ScreenB","value-->"+value);
            Log.d("ScreenB","Response cache -->"+responseCache);

        }


    }

    @dagger.Module
    public class Module {

        @Provides
        @DaggerScope(Component.class)
        public Presenter providesPresenter() {
            return new Presenter(value);
        }

    }
}

