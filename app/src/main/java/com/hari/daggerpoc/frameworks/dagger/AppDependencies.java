package com.hari.daggerpoc.frameworks.dagger;


import com.hari.daggerpoc.cache.ResponseCache;
import com.hari.daggerpoc.utils.Utils;

import dagger.Module;

/**
 * Created by jeyahariprakash on 03/08/16.
 */

@Module(includes = {Utils.class, ResponseCache.class})
public interface AppDependencies {

    Utils utils();

}
