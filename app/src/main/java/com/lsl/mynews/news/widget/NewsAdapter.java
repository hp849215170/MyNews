package com.lsl.mynews.news.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsl.mynews.R;
import com.lsl.mynews.bean.NewsBean;
import com.lsl.mynews.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/6.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOT = 1;

    private List<NewsBean> datas;

    private Context context;

    public NewsAdapter(List<NewsBean> datas, Context context) {
        this.context = context;

        if (datas == null)
            datas = new ArrayList<>();

        this.datas = datas;
    }


    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_news_1, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.foot_view, parent, false);
            FootViewHolder viewHolder = new FootViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            NewsBean newsBean = datas.get(position);
            if (newsBean == null) {
                return;
            }
            ((MyViewHolder) holder).title.setText(newsBean.getTitle());
            ((MyViewHolder) holder).content.setText(newsBean.getDigest());
            ImageLoaderUtil.display(((MyViewHolder) holder).mImageView, newsBean.getImgsrc());
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView title;
        TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.news_image);
            title = (TextView) itemView.findViewById(R.id.news_title);
            content = (TextView) itemView.findViewById(R.id.news_content);
        }
    }


    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

}
