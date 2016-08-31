package com.hari.daggerpoc.frameworks.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hari.daggerpoc.application.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeyahariprakash on 30/08/16.
 */
@Module
@DaggerScope(App.Component.class)
public class ModuleDependencies {

    @Provides
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
