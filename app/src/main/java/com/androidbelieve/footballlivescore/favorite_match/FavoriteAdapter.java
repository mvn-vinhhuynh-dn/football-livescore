package com.androidbelieve.footballlivescore.favorite_match;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.database.FavoriteMatch;
import com.androidbelieve.footballlivescore.util.CheckTeamNameSetLogo;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/26/15.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements StickyRecyclerHeadersAdapter<FavoriteAdapter.VhFavoriteAdapter> {
    private Context mContext;
    private List<FavoriteMatch> mDatas;

    public FavoriteAdapter(Context context, List<FavoriteMatch> favoriteMatchList) {
        this.mContext = context;
        this.mDatas = favoriteMatchList;
    }


    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_favorite_match, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        CheckTeamNameSetLogo.setLogo(mContext, holder.imgHome, mDatas.get(position).getHomeName());
        CheckTeamNameSetLogo.setLogo(mContext, holder.imgAgainst, mDatas.get(position).getAwayName());

        holder.tvAgainstName.setText(mDatas.get(position).getAwayName());
        holder.tvHomeName.setText(mDatas.get(position).getHomeName());

        if (mDatas.get(position).getIsNotification() == 0) {
            holder.imgNotification.setImageResource(R.drawable.ic_notification);
        } else {
            holder.imgNotification.setImageResource(R.drawable.ic_unnotification);
        }

        String day = getConvertTime(mDatas.get(position).getTimeStart());
        try {
            holder.tvTime.setText(getCurentTime(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getHeaderId(int position) {
        return mDatas.get(position).getHeaderId().hashCode();
    }

    @Override
    public VhFavoriteAdapter onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ltd, parent, false);
        return new VhFavoriteAdapter(view);
    }

    @Override
    public void onBindHeaderViewHolder(VhFavoriteAdapter holder, int position) {
        String day = getConvertDate(mDatas.get(position).getTimeStart());
        try {
            holder.tvDateStart.setText(getCurentDate(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHomeName;
        private TextView tvAgainstName;
        private TextView tvTime;

        private ImageView imgHome;
        private ImageView imgAgainst;
        private ImageView imgNotification;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name_favorite);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name_favorite);
            tvTime = (TextView) itemView.findViewById(R.id.tv_goals_favorite);

            imgHome = (ImageView) itemView.findViewById(R.id.home_icon_favorite);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon_favorite);
            imgNotification = (ImageView) itemView.findViewById(R.id.img_notification_favorite);
        }
    }

    public String getConvertTime(String adate) {
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Europe/London");
        Date date = null;
        try {
            date = form.parse(adate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String newDateStr = postFormater.format(date);
        return newDateStr;
    }

    public String getCurentTime(String time) throws ParseException {
        // From TimeZone Etc/UTC
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        TimeZone tz = TimeZone.getTimeZone("Etc/UTC");
        formatter.setTimeZone(tz);
        Date date = formatter.parse(time);


        // To TimeZone America/New_York
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("HH:mm");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        sdfAmerica.setTimeZone(tzInAmerica);

        String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
        return sDateInAmerica;
    }

    public class VhFavoriteAdapter extends RecyclerView.ViewHolder {
        private TextView tvDateStart;

        public VhFavoriteAdapter(View itemView) {
            super(itemView);
            tvDateStart = (TextView) itemView.findViewById(R.id.tv_time_ltd);
        }
    }

    public String getConvertDate(String adate) {
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Europe/London");
        Date date = null;
        try {
            date = form.parse(adate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String newDateStr = postFormater.format(date);
        return newDateStr;
    }

    public String getCurentDate(String time) throws ParseException {
        // From TimeZone Etc/UTC
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        TimeZone tz = TimeZone.getTimeZone("Etc/UTC");
        formatter.setTimeZone(tz);
        Date date = formatter.parse(time);


        // To TimeZone America/New_York
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd/MM/yyyy");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        sdfAmerica.setTimeZone(tzInAmerica);

        String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
        return sDateInAmerica;
    }
}