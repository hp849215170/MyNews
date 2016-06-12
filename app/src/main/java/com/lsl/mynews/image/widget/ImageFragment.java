package com.lsl.mynews.image.widget;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsl.mynews.R;
import com.lsl.mynews.base.BaseFragment;
import com.lsl.mynews.bean.ImageBean;
import com.lsl.mynews.common.APIs;
import com.lsl.mynews.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/2.
 */
public class ImageFragment extends BaseFragment {

    private static ImageFragment instance;
    private SwipeRefreshLayout imageRefresh;
    private RecyclerView rvImage;
    private ImageRecyclerViewAdapter imageRecyclerViewAdapter;
    List<ImageBean> imageList = new ArrayList<>();

    public static ImageFragment getInstance() {
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadImageResource.startLoading(APIs.IMAGES_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        instance = this;

        imageRefresh = (SwipeRefreshLayout) view.findViewById(R.id.image_refresh);
        rvImage = (RecyclerView) view.findViewById(R.id.rv_image);

        rvImage.setLayoutManager(new LinearLayoutManager(getActivity()));
        imageRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadImageResource.startLoading(APIs.IMAGES_URL);
            }
        });


        imageRecyclerViewAdapter = new ImageRecyclerViewAdapter(imageList, getActivity());

        rvImage.setAdapter(imageRecyclerViewAdapter);


        return view;

    }

    public Handler setImageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageRecyclerViewAdapter.notifyDataSetChanged();
        }
    };

    public void setImageData(ImageBean imageBean) {
        imageList.add(imageBean);
        L.i("imageList======>", "" + imageList.size());
        setImageHandler.sendEmptyMessage(0x002);

    }

    public Handler refreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            stopRefresh();
        }
    };

    public void stopRefresh() {
        imageRefresh.setRefreshing(false);
    }
}
