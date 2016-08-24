package com.hari.daggerpoc.frameworks.mortar;

public interface ScreenComponentFactory<T> {

    Object createComponent(T parent);
}
