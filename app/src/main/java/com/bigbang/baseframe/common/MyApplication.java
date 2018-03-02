package com.bigbang.baseframe.common;

import android.app.Application;
import android.content.Context;


/**
 * Created by Administrator on 2017/11/7.
 */

public class MyApplication extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    public static Context getMyApplicationContext() {
        return mApplicationContext;
    }

}
