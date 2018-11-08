package com.yj.appstore;

import android.app.Application;

public class App extends Application{
    static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getContext() {
        return app;
    }
}
