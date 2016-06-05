package com.lsl.mynews.news.presenter;

import android.os.Handler;
import android.os.Looper;

import com.lsl.mynews.common.APIs;
import com.lsl.mynews.common.Config;
import com.lsl.mynews.common.ResultCallBack;
import com.lsl.mynews.common.HttpUtils;
import com.squareup.okhttp.Response;

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
    public void loadNews(int type, int page) {

        String url = getNewsUrl(type, page);

        HttpUtils.get(url, new ResultCallBack() {
            @Override
            public void onSuccess(Response response) {
                
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

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
