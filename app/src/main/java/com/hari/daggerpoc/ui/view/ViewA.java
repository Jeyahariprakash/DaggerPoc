package com.hari.daggerpoc.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.hari.daggerpoc.frameworks.dagger.DaggerService;
import com.hari.daggerpoc.ui.presenter.ScreenA;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class ViewA extends RelativeLayout {
    @Inject
    ScreenA.Presenter presenter;

    public ViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<ScreenA.Component>getDaggerComponent(context).inject(this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }
}
