package com.lsl.mynews.news.view;

import com.lsl.mynews.base.BaseView;
import com.lsl.mynews.bean.NewsDetailBean;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/8.
 */
public interface INewsDetailsView extends BaseView<NewsDetailBean> {

    void showHtmlText(String str);
}
