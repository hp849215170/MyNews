package com.lsl.mynews.image.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsl.mynews.R;
import com.lsl.mynews.bean.ImageBean;
import com.lsl.mynews.util.ImageLoaderUtil;
import com.lsl.mynews.util.T;

import java.util.List;

/**
 * Created by HP on 2016/6/12.
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private List<ImageBean> mList;
    private Context mContext;

    public ImageRecyclerViewAdapter(List<ImageBean> list,Context context){
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageBean imageBean = mList.get(position);
        holder.tvTitle.setText(imageBean.getTitle());
        ImageLoaderUtil.display(holder.ivBigImage, imageBean.getSourceurl());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBigImage;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBigImage = (ImageView) itemView.findViewById(R.id.iv_big_image);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
