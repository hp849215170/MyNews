package com.lsl.mynews.news.presenter;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lsl.mynews.bean.NewsDetailBean;
import com.lsl.mynews.common.APIs;
import com.lsl.mynews.common.HttpUtils;
import com.lsl.mynews.common.ResultCallBack;
import com.lsl.mynews.news.view.INewsDetailsView;
import com.lsl.mynews.util.L;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/8.
 */
public class NewsDetailsPresenterImpl implements NewsDetailsPresenter {

    private Handler mHandler;

    private INewsDetailsView mINewsDetailsView;


    public NewsDetailsPresenterImpl(INewsDetailsView INewsDetailsView) {
        mINewsDetailsView = INewsDetailsView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadNewsDetails(final String docId) {
        StringBuilder stringBuilder = new StringBuilder(APIs.NEW_DETAIL)
                .append(docId)
                .append(APIs.END_DETAIL_URL);

        L.w("info_content_url", stringBuilder.toString());

        HttpUtils.get(stringBuilder.toString(), new ResultCallBack() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String str = response.body().string();
                    L.info(str);
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(str).getAsJsonObject();
                    JsonElement jsonElement = jsonObject.get(docId);

                    if (jsonElement == null)
                        return;
                    final NewsDetailBean newsDetailBean = gson.fromJson(jsonElement.getAsJsonObject(), NewsDetailBean.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (newsDetailBean == null)
                                return;
                            mINewsDetailsView.showHtmlText(newsDetailBean.getBody());
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(final Exception e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mINewsDetailsView.onFailder(e);

                    }
                });
            }
        });

    }

    @Override
    public void Destory() {
        mHandler = null;
        mINewsDetailsView = null;
    }
}
