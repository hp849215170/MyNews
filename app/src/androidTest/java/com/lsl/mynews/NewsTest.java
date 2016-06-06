package com.lsl.mynews;

import android.test.InstrumentationTestCase;

import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.common.Config;
import com.lsl.mynews.news.view.INewsView;
import com.lsl.mynews.news.presenter.NewsPresenter;
import com.lsl.mynews.news.presenter.NewsPresenterImpl;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public class NewsTest extends InstrumentationTestCase implements INewsView {

    public void test() {
        NewsPresenter presenter = new NewsPresenterImpl(this);
        presenter.loadNews(Config.TYPE_TOP, 0);
    }

    @Override
    public void onSuccess(List<NewsBean> datas) {

    }

    @Override
    public void onFailder(Exception e) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
