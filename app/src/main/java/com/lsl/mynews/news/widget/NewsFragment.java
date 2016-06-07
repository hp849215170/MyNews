package com.lsl.mynews.news.widget;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;
import com.lsl.mynews.common.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public class NewsFragment extends BaseFragment {

    private TabLayout mTabLayout;

    private ViewPager mViewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mTabLayout = (TabLayout) view.findViewById(R.id.news_tablayout);

        mViewPager = (ViewPager) view.findViewById(R.id.news_vp);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.headline));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.car));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.joke));


        mViewPager.setOffscreenPageLimit(3); //加载缓存3个

        MyPageAdapter pageAdapter = new MyPageAdapter(getChildFragmentManager());
        pageAdapter.addFragment(NewsListFragment.newInstance(Config.TYPE_TOP), getString(R.string.headline));
        pageAdapter.addFragment(NewsListFragment.newInstance(Config.TYPE_NAB), getString(R.string.nba));
        pageAdapter.addFragment(NewsListFragment.newInstance(Config.TYPE_CAR), getString(R.string.car));
        pageAdapter.addFragment(NewsListFragment.newInstance(Config.TYPE_JOKE), getString(R.string.joke));

        mViewPager.setAdapter(pageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);


        return view;
    }


    private class MyPageAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
