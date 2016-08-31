package com.hari.daggerpoc.frameworks.dagger;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.util.Log;

import com.hari.daggerpoc.application.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeyahariprakash on 30/08/16.
 */
@Module
@DaggerScope(App.Component.class)
public class FingerPrintModule {
    @Provides
    public FingerprintManager providesFingerprintManager(Context context) {
        Log.e("FingerprintModule","Initializing"+context);
        return context.getSystemService(FingerprintManager.class);
    }

    @Provides
    public KeyguardManager providesKeyguardManager(Context context) {
        return context.getSystemService(KeyguardManager.class);
    }
}
