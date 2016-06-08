package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseActivity;
import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.bean.NewsDetailBean;
import com.lsl.mynews.news.presenter.NewsDetailsPresenter;
import com.lsl.mynews.news.presenter.NewsDetailsPresenterImpl;
import com.lsl.mynews.news.view.INewsDetailsView;
import com.lsl.mynews.util.ImageLoaderUtil;
import com.lsl.mynews.util.L;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/7.
 */
public class NewsDetailsActivity extends BaseActivity implements INewsDetailsView {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private Toolbar mToolbar;

    private ImageView mImageView;

    private HtmlTextView mHtmlTextView;

    private NewsBean mNewsBean;


    NewsDetailsPresenter mNewsDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.details_collbar);
        mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        mImageView = (ImageView) findViewById(R.id.details_image);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.details_html);


        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");

        mCollapsingToolbarLayout.setTitle(mNewsBean.getTitle());

        ImageLoaderUtil.display(mImageView, mNewsBean.getImgsrc());


        mNewsDetailsPresenter = new NewsDetailsPresenterImpl(this);

        mNewsDetailsPresenter.loadNewsDetails(mNewsBean.getDocid());


    }


    @Override
    public void onSuccess(List<NewsDetailBean> datas) {
        //不可使用
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewsDetailsPresenter.Destory();
    }

    @Override
    public void showHtmlText(String str) {
        L.w("info_content", str);
        mHtmlTextView.setHtmlFromString(str, new HtmlTextView.LocalImageGetter());
    }
}
