package com.lsl.mynews.main.widget;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lsl.mynews.R;
import com.lsl.mynews.about.widget.AboutFragment;
import com.lsl.mynews.base.BaseActivity;
import com.lsl.mynews.image.widget.ImageFragment;
import com.lsl.mynews.main.presenter.MainPresenter;
import com.lsl.mynews.main.presenter.MainPresenterImpl;
import com.lsl.mynews.main.view.MainView;
import com.lsl.mynews.news.widget.NewsFragment;

public class MainActivity extends BaseActivity implements MainView {

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    private MainPresenter mMainPresenter;

    private NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.app_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.app_nav_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                mMainPresenter.switchNavigation(item.getItemId());
                item.setCheckable(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        mMainPresenter = new MainPresenterImpl(this);
        switch2News();


    }

    private NewsFragment sNewsFragment = new NewsFragment();
    private ImageFragment sImageFragment = new ImageFragment();
    private AboutFragment sAboutFragment = new AboutFragment();

    @Override
    public void switch2News() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.app_fragment_content, sNewsFragment)
                .commit();
        mToolbar.setTitle(R.string.draw_news);
    }

    @Override
    public void switch2Image() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.app_fragment_content, sImageFragment)
                .commit();
        mToolbar.setTitle(R.string.draw_image);
    }

    @Override
    public void switch2About() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.app_fragment_content, sAboutFragment)
                .commit();
        mToolbar.setTitle(R.string.draw_about);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
