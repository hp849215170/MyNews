package com.lsl.mynews.about.widget;

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
 * Date     :2016/6/2.
 */
public class AboutFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_about,container,false);
        return view;
    }
}
