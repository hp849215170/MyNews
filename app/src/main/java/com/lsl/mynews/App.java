package com.lsl.mynews;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

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
        CrashReport.initCrashReport(getApplicationContext(), "J8f5SBHutsdM04D4", true);

    }

    public static Context getContext() {

        return mContext;
    }
}
