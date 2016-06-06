package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;
import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.common.Config;
import com.lsl.mynews.news.presenter.NewsPresenter;
import com.lsl.mynews.news.presenter.NewsPresenterImpl;
import com.lsl.mynews.news.view.INewsView;
import com.lsl.mynews.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public class NewsListFragment extends BaseFragment implements INewsView {

    private int mType = Config.TYPE_TOP;
    private int pageIndex = 0;
    private static final String fragment_type = "type";


    private NewsPresenter mNewsPresenter;


    private RecyclerView mRecyclerView;


    private NewsAdapter adapter;

    private List<NewsBean> news;

    public static NewsListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        bundle.putInt(fragment_type, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(fragment_type);
        mNewsPresenter = new NewsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        news = new ArrayList<>();
        adapter = new NewsAdapter(news, getContext());
        mRecyclerView.setAdapter(adapter);

        mNewsPresenter.loadNews(mType, pageIndex);

        return view;
    }

    @Override
    public void onSuccess(List<NewsBean> datas) {

        L.info(datas.toString());

        if (news == null)
            news = new ArrayList<>();
        news.addAll(datas);
        adapter.notifyDataSetChanged();
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
