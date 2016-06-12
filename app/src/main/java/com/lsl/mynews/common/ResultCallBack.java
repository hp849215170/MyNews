package com.lsl.mynews.common;

import com.lsl.mynews.bean.ImageBean;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by lishoulin on 2016/6/5.
 */

public abstract class ResultCallBack implements Callback {
    @Override
    public void onFailure(Request request, IOException e) {
        onFailure(e);
    }

    @Override
    public void onResponse(Response response) throws IOException {
        onSuccess(response);
    }

    /**
     * 成功回调
     *
     * @param response
     */
    public abstract List<ImageBean> onSuccess(Response response);

    /**
     * 失败回调
     *
     * @param e
     */
    public abstract void onFailure(Exception e);

}
