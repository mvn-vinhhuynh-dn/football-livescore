package com.androidbelieve.drawerwithswipetabs.stickydapterPrimer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.LTD;
import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 23/09/14.
 */
public class InitialHeaderAdapter implements StickyHeadersAdapter<InitialHeaderAdapter.ViewHolder> {

    private ArrayList<LTD> mDatas = new ArrayList<>();

    public InitialHeaderAdapter(ArrayList<LTD> datas) {
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ltd, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder headerViewHolder, int position) {
        String day = getConvertDate(mDatas.get(0).getFixtures().get(position).getDate());
        try {
            headerViewHolder.letter.setText(getCurentTime(day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getHeaderId(int position) {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.get(0).getFixtures().get(position).getDate().charAt(0);
        }
        return -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView letter;

        public ViewHolder(View itemView) {
            super(itemView);
            letter = (TextView) itemView.findViewById(R.id.tv_time_ltd);
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
        SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy EEE", Locale.getDefault());
        return postFormater.format(date);
    }

    public String getCurentTime(String time) throws ParseException {
        // From TimeZone Etc/UTC
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy EEE");
        TimeZone tz = TimeZone.getTimeZone("Etc/UTC");
        formatter.setTimeZone(tz);
        Date date = formatter.parse(time);


        // To TimeZone America/New_York
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd/MM/yyyy EEE");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        sdfAmerica.setTimeZone(tzInAmerica);

        return sdfAmerica.format(date);
    }
}
