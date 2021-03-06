package com.hari.daggerpoc.frameworks.dagger;

import android.content.Context;

public class DaggerService {

    public static final String SERVICE_NAME = DaggerService.class.getName();

    @SuppressWarnings("unchecked")
    public static <T> T getDaggerComponent(Context context) {
        //noinspection ResourceType
        return (T) context.getSystemService(SERVICE_NAME);
    }
}
