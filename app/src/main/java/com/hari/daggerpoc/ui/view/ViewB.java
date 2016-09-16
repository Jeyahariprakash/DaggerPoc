package com.hari.daggerpoc.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.frameworks.dagger.DaggerService;
import com.hari.daggerpoc.ui.presenter.ScreenB;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class ViewB extends RelativeLayout {
    @Inject
    ScreenB.Presenter presenter;

    @BindView(R.id.screen_b_button)
    public Button button;

    @BindView(R.id.screen_b_edit_text)
    public EditText editText;

    @BindView(R.id.screen_b_text_view)
    public TextView textView;

    @OnClick(R.id.screen_b_button)
    void moveToNext(){presenter.moveToNext();}

    public ViewB(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<ScreenB.Component>getDaggerComponent(context).inject(this);

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
