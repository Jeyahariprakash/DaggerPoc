package com.hari.daggerpoc.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.hari.daggerpoc.R;
import com.hari.daggerpoc.application.App;
import com.hari.daggerpoc.frameworks.dagger.AppDependencies;
import com.hari.daggerpoc.frameworks.dagger.DaggerScope;
import com.hari.daggerpoc.frameworks.dagger.DaggerService;
import com.hari.daggerpoc.frameworks.flow.GsonParceler;
import com.hari.daggerpoc.frameworks.flow.HandlesBack;
import com.hari.daggerpoc.ui.presenter.ScreenA;

import butterknife.BindView;
import butterknife.ButterKnife;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;


public class MainActivity extends Activity implements Flow.Dispatcher {

    public static final String INTRO_PAGE_PUBLISHED_DATE = "intro_page_published_date";

    private static final String TAG = "MainActivity";

    private static final String appLanguage = "nb";

    @BindView(R.id.mortar_path_container_view)
    public PathContainerView pathContainerView;

    private MortarScope mortarScope;

    private FlowDelegate flowDelegate;

    @Override
    public Object getSystemService(String name) {
        if(getApplication() == null) {
            return super.getSystemService(name);
        }

        Object service = null;

        if(flowDelegate != null) {
            service = flowDelegate.getSystemService(name);
        }

        if(service == null && mortarScope != null && mortarScope.hasService(name)) {
            service = mortarScope.getService(name);
        }

        return service != null ? service : super.getSystemService(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mortarScope = MortarScope.findChild(getApplicationContext(), getClass().getName());
        if(mortarScope == null) {
            Component component = DaggerMainActivity_Component.builder().component(DaggerService.<App.Component>getDaggerComponent(getApplicationContext())).build();

            mortarScope = MortarScope.buildChild(getApplicationContext()).withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner()).withService(DaggerService.SERVICE_NAME, component).build(getClass().getName());
        }

        DaggerService.<Component>getDaggerComponent(this).inject(this);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GsonParceler parceler = new GsonParceler(new Gson());
        FlowDelegate.NonConfigurationInstance nonConfig = (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        flowDelegate = FlowDelegate.onCreate(nonConfig, getIntent(), savedInstanceState, parceler, History.single(new ScreenA()), this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if(isFinishing()) {
            MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getClass().getName());
            if(activityScope != null) {
                activityScope.destroy();
            }
        }

        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if(((HandlesBack) pathContainerView).onBackPressed()) {
            return;
        }
        if(flowDelegate.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

    /**
     * this method returns the non configuration instance of flow delegate
     */
    @SuppressWarnings("deprecation")
    @Override
    public Object onRetainNonConfigurationInstance() {
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    /**
     * Flow delegate will resume when android life cycle resume called
     */
    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    /**
     * Flow delegate will pause when android life cycle pause called
     */
    @Override
    protected void onPause() {
        super.onPause();
        flowDelegate.onPause();
    }

    @Override
    public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        Path path = traversal.destination.top();
        setTitle(path.getClass().getSimpleName());
        //ActionBar actionBar = getActionBar();
        //        boolean canGoBack = traversal.destination.size() > 1;
        //        actionBar.setDisplayHomeAsUpEnabled(canGoBack);
        //        actionBar.setHomeButtonEnabled(canGoBack);
        pathContainerView.dispatch(traversal, new Flow.TraversalCallback() {
            @Override
            public void onTraversalCompleted() {
                invalidateOptionsMenu();
                callback.onTraversalCompleted();
            }
        });
    }

    @dagger.Component(dependencies = App.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {

        void inject(MainActivity activity);
    }
}
