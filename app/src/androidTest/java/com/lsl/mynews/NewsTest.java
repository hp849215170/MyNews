package com.lsl.mynews;

import android.test.InstrumentationTestCase;

import com.lsl.mynews.common.Config;
import com.lsl.mynews.news.presenter.INewsView;
import com.lsl.mynews.news.presenter.NewsPresenter;
import com.lsl.mynews.news.presenter.NewsPresenterImpl;

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
}
