package com.share.meizu.newsfeedapi;

import android.app.Application;
import android.content.Context;

/**
 * Created by tongxiaotao on 18-3-1.
 */

public class App extends Application {
    static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this.getApplicationContext();
    }
}
