package com.androidbelieve.drawerwithswipetabs.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.acitivities.WebViewsActivity_;
import com.androidbelieve.drawerwithswipetabs.models.News;
import com.androidbelieve.drawerwithswipetabs.news_fragment.NewsTabsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phulx on 30/10/2015.
 */
public class NewsViewpagerAdapter extends PagerAdapter{
    private List<News> mLists = new ArrayList<>();
    private Context mContext;

    public NewsViewpagerAdapter(List<News> mLists, Context mContext) {
        this.mLists = mLists;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater;
        ImageView imgHeaderNews;
        TextView tvHeaderNews;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_viewpager_news, container, false);
        imgHeaderNews = (ImageView)itemView.findViewById(R.id.imgheaderNews);
        tvHeaderNews = (TextView)itemView.findViewById(R.id.tvHeaderNews);

        Picasso.with(mContext)
                .load(mLists.get(position).getSummaryImg()).fit().centerCrop()
                .into(imgHeaderNews);
        tvHeaderNews.setText(mLists.get(position).getTitle());

        container.addView(itemView);

        imgHeaderNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewsActivity_.intent(mContext)
                        .extra("URL", mLists.get(position).getLink()).start();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
