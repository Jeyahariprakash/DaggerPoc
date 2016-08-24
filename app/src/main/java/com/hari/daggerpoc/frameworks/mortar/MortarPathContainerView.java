package com.hari.daggerpoc.frameworks.mortar;

import android.content.Context;
import android.util.AttributeSet;

import com.hari.daggerpoc.R;
import com.hari.daggerpoc.frameworks.flow.FramePathContainerView;
import com.hari.daggerpoc.frameworks.flow.SimplePathContainer;

import flow.path.Path;

public class MortarPathContainerView extends FramePathContainerView {

    public MortarPathContainerView(Context context, AttributeSet attrs) {
        super(context, attrs, new SimplePathContainer(R.id.screen_switcher_tag, Path.contextFactory(new BasicMortarContextFactory(new ScreenScoper()))));
    }
}
