package com.lsl.mynews.news.presenter;

import com.lsl.mynews.base.BasePresenter;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/8.
 */
public interface NewsDetailsPresenter extends BasePresenter {


    /**
     * 获取新闻详情
     *
     * @param docId
     */
    void loadNewsDetails(String docId);
}
