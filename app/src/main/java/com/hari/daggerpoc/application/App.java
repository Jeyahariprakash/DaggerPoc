package com.hari.daggerpoc.application;

import android.app.Application;
import android.content.Context;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.dagger.DaggerService;

import javax.inject.Singleton;

import dagger.Provides;
import mortar.MortarScope;

public class App extends Application {

    private static Context context;

    private MortarScope mortarScope;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Component component = DaggerApp_Component.create();
        component.inject(this);
        context = getApplicationContext();
        mortarScope = MortarScope.buildRootScope().withService(DaggerService.SERVICE_NAME, component).build(getResources().getString(R.string.motor_scope_name));
    }

    @Override
    public Object getSystemService(String name) {
        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }

    @dagger.Component(modules = {Module.class})
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(App app);
    }

    @dagger.Module
    public class Module {

        @Provides
        @Singleton
        Context provideApplicationContext() {
            return getApplicationContext();
        }
    }

}
