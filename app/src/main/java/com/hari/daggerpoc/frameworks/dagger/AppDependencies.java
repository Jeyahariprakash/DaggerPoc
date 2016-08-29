package com.hari.daggerpoc.frameworks.dagger;


import com.hari.daggerpoc.cache.ResponseCache;
import com.hari.daggerpoc.utils.Utils;

/**
 * Created by jeyahariprakash on 03/08/16.
 */

public interface AppDependencies {

    Utils utils();

    ResponseCache responseCache();

}
