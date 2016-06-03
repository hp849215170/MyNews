package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public class NewsListFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);


        return view;
    }
}
