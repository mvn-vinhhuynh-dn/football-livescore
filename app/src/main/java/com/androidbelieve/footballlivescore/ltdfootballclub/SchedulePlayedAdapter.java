package com.androidbelieve.footballlivescore.ltdfootballclub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.LtdFootballClub;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/6/15.
 */
public class SchedulePlayedAdapter extends RecyclerView.Adapter<SchedulePlayedAdapter.ViewHolder> {
    private ArrayList<LtdFootballClub> mDatas = new ArrayList<>();
    private Context mContext;

    public SchedulePlayedAdapter(Context context, ArrayList<LtdFootballClub> datas) {
        mContext = context;
        mDatas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ltd_fc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvDate.setText(mDatas.get(position).getDate());
        holder.tvHomeName.setText(mDatas.get(position).getHomeTeam());
        holder.tvAwayName.setText(mDatas.get(position).getAwayTeam());
        holder.tvResult.setText(mDatas.get(position).getResult());
        holder.tvLeague.setText(mDatas.get(position).getLeague());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvHomeName;
        TextView tvAwayName;
        TextView tvResult;
        TextView tvLeague;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date_fc_schedule);
            tvHomeName = (TextView) itemView.findViewById(R.id.tv_home_name_fc_schedule);
            tvAwayName = (TextView) itemView.findViewById(R.id.tv_away_name_fc_schedule);
            tvResult = (TextView) itemView.findViewById(R.id.tv_result_fc_schedule);
            tvLeague = (TextView) itemView.findViewById(R.id.tv_league_fc_schedule);
        }
    }
}
