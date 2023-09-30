package com.tungngt.dev;

import android.app.Application;

import com.google.android.material.color.DynamicColors;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.data.container.impl.AppContainerImpl;

public class MyApplication extends Application {

    public AppContainer appContainer;
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this);

        appContainer = new AppContainerImpl(this);
        
    }
}
