package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;
import com.lsl.mynews.util.L;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public class NewsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        L.info("我被调用了呀");

        return view;
    }
}
