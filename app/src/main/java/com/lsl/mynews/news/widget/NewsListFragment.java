package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.lsl.mynews.App;
import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;
import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.common.APIs;
import com.lsl.mynews.common.Config;
import com.lsl.mynews.news.presenter.NewsPresenter;
import com.lsl.mynews.news.presenter.NewsPresenterImpl;
import com.lsl.mynews.news.view.INewsView;
import com.lsl.mynews.util.L;
import com.lsl.mynews.util.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/3.
 */
public class NewsListFragment extends BaseFragment implements INewsView, SwipeRefreshLayout.OnRefreshListener {

    private int mType = Config.TYPE_TOP;
    private int pageIndex = 0;
    private static final String fragment_type = "type";


    private NewsPresenter mNewsPresenter;


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private NewsAdapter adapter;

    private List<NewsBean> news;

    private LinearLayoutManager mLinearLayoutManager;

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

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastindex;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取最后的item
                lastindex = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastindex + 1 == adapter.getItemCount()) {
                    mNewsPresenter.loadNews(mType, pageIndex + APIs.PAZE_SIZE);
                }
            }
        });


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

        pageIndex += APIs.PAZE_SIZE;
    }

    @Override
    public void onFailder(Exception e) {
        T.showShort(App.getContext(), e.getMessage());
    }

    @Override
    public void showLoading() {
        if (pageIndex == 0)
            mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (news != null)
            news.clear();
        mNewsPresenter.loadNews(mType, pageIndex);
    }


}
