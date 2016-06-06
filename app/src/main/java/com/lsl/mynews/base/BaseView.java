package com.lsl.mynews.base;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public interface BaseView<T> {
    void onSuccess(List<T> datas);

    void onFailder(Exception e);

    void showLoading();

    void hideLoading();
}
