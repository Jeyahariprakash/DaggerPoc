package com.hari.daggerpoc.application;

import android.app.Application;
import android.content.Context;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.dagger.DaggerService;

import dagger.Provides;
import mortar.MortarScope;

public class App extends Application {

    private MortarScope mortarScope;

    @Override
    public void onCreate() {
        super.onCreate();
        Component component = DaggerApp_Component.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        mortarScope = MortarScope.buildRootScope().withService(DaggerService.SERVICE_NAME, component).build(getResources().getString(R.string.motor_scope_name));
    }

    @Override
    public Object getSystemService(String name) {
        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }

    @dagger.Component(modules = {AppModule.class})
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(App app);

        Context context();
    }

    @dagger.Module
    @DaggerScope(App.Component.class)
    public class AppModule {

        private App app;

        public AppModule(App app){
            this.app = app;
        }

        @Provides
        Context provideApplicationContext() {
            return app.getApplicationContext();
        }
    }

}
