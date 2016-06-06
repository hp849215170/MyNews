package com.lsl.mynews.news.presenter;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.common.APIs;
import com.lsl.mynews.common.Config;
import com.lsl.mynews.common.HttpUtils;
import com.lsl.mynews.common.ResultCallBack;
import com.lsl.mynews.news.view.INewsView;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public class NewsPresenterImpl implements NewsPresenter {


    private Handler mHandler;

    private INewsView mINewsView;


    public NewsPresenterImpl(INewsView INewsView) {
        mINewsView = INewsView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadNews(final int type, int page) {

        String url = getNewsUrl(type, page);

        mINewsView.showLoading();

        HttpUtils.get(url, new ResultCallBack() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String str;
                    final List<NewsBean> datas = new ArrayList<>();
                    str = response.body().string();
                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonObject = jsonParser.parse(str).getAsJsonObject();

                    JsonElement jsonElement = jsonObject.getAsJsonArray(getId(type));

                    JsonArray jsonArray = jsonElement.getAsJsonArray();


                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject jsonObject1 = jsonArray.get(i).getAsJsonObject();
                        datas.add(gson.fromJson(jsonObject1, NewsBean.class));
                    }

                    //对数据进行解析
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mINewsView.onSuccess(datas);
                            mINewsView.hideLoading();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
                mINewsView.onFailder(e);
                mINewsView.hideLoading();
            }
        });

    }

    private String getId(int type) {
        String str = null;
        switch (type) {
            case Config.TYPE_TOP:
                str = APIs.TOP_ID;
                break;
            case Config.TYPE_NAB:
                str = APIs.NBA_ID;
                break;
            case Config.TYPE_CAR:
                str = APIs.CAR_ID;
                break;
            case Config.TYPE_JOKE:
                str = APIs.JOKE_ID;
                break;
        }

        return str;

    }

    /**
     * 根据类型获取url
     *
     * @param type 新闻类型
     * @param page 页码
     * @return
     */
    private String getNewsUrl(int type, int page) {
        StringBuilder builder = new StringBuilder();

        switch (type) {
            case Config.TYPE_TOP:
                builder.append(APIs.TOP_URL).append(APIs.TOP_ID);
                break;
            case Config.TYPE_NAB:
                builder.append(APIs.COMMON_URL).append(APIs.NBA_ID);
                break;
            case Config.TYPE_CAR:
                builder.append(APIs.COMMON_URL).append(APIs.CAR_ID);
                break;
            case Config.TYPE_JOKE:
                builder.append(APIs.COMMON_URL).append(APIs.JOKE_ID);
                break;
        }
        builder.append("/").append(page).append(APIs.END_URL);


        return builder.toString();
    }


    @Override
    public void Destory() {

        mINewsView = null;
    }
}
