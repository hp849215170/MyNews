package com.lsl.mynews.util;


import com.lsl.mynews.BuildConfig;

/**
 * Created by lishoulin on 2016/4/28.
 */
public class L {

    public static void v(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            android.util.Log.v(tag, msg);

    }


    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            android.util.Log.d(tag, msg);
    }


    public static void i(String tag, String msg) {

        if (BuildConfig.LOG_DEBUG)
            android.util.Log.i(tag, msg);
    }


    public static void w(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            android.util.Log.w(tag, msg);
    }


    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }


    public static void info(String msg) {
        if (BuildConfig.LOG_DEBUG)
            android.util.Log.i("info", msg);
    }


}
