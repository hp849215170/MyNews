package com.lsl.mynews.common;


import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:网络封装类
 * Author   :lishoulin
 * Date     :2016/6/1.
 */
public class HttpUtils {

    private static OkHttpClient mClient;


    private static void ini() {

        synchronized (HttpUtils.class) {
            if (mClient == null) {
                mClient = new OkHttpClient();
                mClient.setConnectTimeout(10L, TimeUnit.SECONDS);
                mClient.setWriteTimeout(10L, TimeUnit.SECONDS);
                mClient.setReadTimeout(10L, TimeUnit.SECONDS);
                mClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
            }
        }

    }

    private static OkHttpClient getInstance() {
        if (mClient == null)
            ini();

        return mClient;
    }

    /********************************对外接口********************************/
    /**
     * get请求
     *
     * @param url
     * @param callback
     */
    public static void get(String url, ResultCallBack callback) {
        Request request = new Request.Builder().get().url(url).build();
        getInstance().newCall(request).enqueue(callback);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void post(String url, List<Param> params, ResultCallBack callback) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param p : params
                ) {
            builder.add(p.key, p.value);
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();

        getInstance().newCall(request).enqueue(callback);
    }


    /**
     * post请求参数类
     */
    public static class Param {

        String key;
        String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }

}
