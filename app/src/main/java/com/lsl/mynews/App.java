package com.lsl.mynews;

import android.app.Application;
import android.content.Context;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {

        return mContext;
    }
}
