package com.lsl.mynews.main.presenter;

import com.lsl.mynews.R;
import com.lsl.mynews.main.view.MainView;

/**
 * Description: mainPresenter具体业务的实现
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            case R.id.nav_image:
                mMainView.switch2Image();
                break;
            case R.id.nav_about:
                mMainView.switch2About();
                break;
            case R.id.nav_skin:

                break;


        }
    }
}
