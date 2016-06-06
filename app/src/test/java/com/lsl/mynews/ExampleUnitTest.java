package com.lsl.mynews;

import com.lsl.mynews.common.Config;
import com.lsl.mynews.news.view.INewsView;
import com.lsl.mynews.news.presenter.NewsPresenter;
import com.lsl.mynews.news.presenter.NewsPresenterImpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest implements INewsView {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testNews() {
        NewsPresenter presenter = new NewsPresenterImpl(this);
        presenter.loadNews(Config.TYPE_TOP, 0);
    }
}