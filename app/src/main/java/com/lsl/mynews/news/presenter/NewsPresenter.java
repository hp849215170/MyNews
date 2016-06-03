package com.lsl.mynews.news.presenter;

import com.lsl.mynews.base.BasePresenter;

/**
 * Description:新闻业务的接口
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public interface NewsPresenter extends BasePresenter{
    /**
     * 读取新闻
     *
     * @param page  页码
     * @param type  新闻类型
     */
    void loadNews(int type, int page);
}
