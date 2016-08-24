package com.hari.daggerpoc.utils;

import android.util.Log;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
@Module
public class Utils {

    public static String TAG = "Utils";

    @Inject
    public Utils() {

    }

    public void printLogs() {
        Log.e(TAG,"Printing logs");
    }

}
