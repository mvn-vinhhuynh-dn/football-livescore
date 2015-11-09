package com.androidbelieve.drawerwithswipetabs.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.acitivities.WebViewsActivity_;
import com.androidbelieve.drawerwithswipetabs.models.News;
import com.androidbelieve.drawerwithswipetabs.util.FixedSpeedScroller;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Created by phulx on 29/10/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    int currentPage = 0;
    Handler handler;
    private Context mContext;
    private List<News> mArraylistNews = new ArrayList<>();
    private List<News> mArraylistNewsHeader = new ArrayList<>();

    public NewsAdapter(Context mContext, List<News> mArraylistNews, List<News> mArraylistNewsHeader) {
        this.mContext = mContext;
        this.mArraylistNews = mArraylistNews;
        this.mArraylistNewsHeader = mArraylistNewsHeader;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new ViewHolderItem(rootView);
        } else if (viewType == TYPE_HEADER) {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header_news, parent, false);
            return new VhHeader(rootView);
        }
        throw new RuntimeException("there is no type that matches " +
                "the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder != null && holder instanceof ViewHolderItem) {
            ((ViewHolderItem) holder).tvNews.setText(mArraylistNews.get(position - 1).getTitle());
            Picasso.with(mContext).load(mArraylistNews.get(position - 1).getSummaryImg()).into(((ViewHolderItem) holder).imgNews);
        } else if (holder instanceof VhHeader) {
            fixDuration(((VhHeader) holder).viewPager);
            ((VhHeader) holder).viewPager.setAdapter(new NewsViewpagerAdapter(mArraylistNewsHeader, mContext));
            ((VhHeader) holder).indicator.setViewPager(((VhHeader) holder).viewPager);

            handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == 5) {
                        currentPage = 0;
                    }
                    ((VhHeader) holder).viewPager.setCurrentItem(currentPage++, true);
                }
            };

            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 2000, 4000);
        }
    }


    @Override
    public int getItemCount() {
        return mArraylistNews.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView tvNews;

        public ViewHolderItem(View itemView) {
            super(itemView);
            imgNews = (ImageView) itemView.findViewById(R.id.imgNews);
            tvNews = (TextView) itemView.findViewById(R.id.tvNews);
        }
    }

    class VhHeader extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        PageIndicator indicator;

        public VhHeader(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewpPagerNews);
            indicator = (CirclePageIndicator) itemView.findViewById(R.id.indicatorNews);
        }
    }

    private void fixDuration(ViewPager viewPager) {
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateInterpolator();
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), sInterpolator, false);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }
}