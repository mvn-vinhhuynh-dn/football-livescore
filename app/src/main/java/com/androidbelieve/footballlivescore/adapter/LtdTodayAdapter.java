package com.androidbelieve.footballlivescore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.footballclubinfor.FootBallClubInformationActivity_;
import com.androidbelieve.footballlivescore.models.LtdToday;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

/**
 *
 * Created by phulx on 12/11/2015.
 */
public class LtdTodayAdapter extends RecyclerView.Adapter<LtdTodayAdapter.ViewholderLtdToday> implements StickyRecyclerHeadersAdapter<LtdTodayAdapter.VhHeaderLtdToday> {

    private Context mContext;
    private ArrayList<LtdToday> mArraylist;

    public LtdTodayAdapter(Context mContext, ArrayList<LtdToday> mArraylist) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
    }

    @Override
    public ViewholderLtdToday onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ltd_today,parent,false);
        return new ViewholderLtdToday(view);
    }

    @Override
    public void onBindViewHolder(ViewholderLtdToday holder, final int position) {
        holder.tvDate.setText(mArraylist.get(position).getDate());
        holder.tvHomeName.setText(mArraylist.get(position).getHomeName());
        Picasso.with(mContext).load(mArraylist.get(position).getImgHome()).into(holder.imgHome);
        holder.tvGoals.setText(mArraylist.get(position).getTime());
        holder.tvAgainstName.setText(mArraylist.get(position).getAwayName());
        Picasso.with(mContext).load(mArraylist.get(position).getImgAway()).into(holder.imgAgainst);

        holder.llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .myUrl(mArraylist.get(position).getLinkHome())
                        .name(mArraylist.get(position).getHomeName())
                        .start();
            }
        });

        holder.llAgainst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .myUrl(mArraylist.get(position).getLinkAway())
                        .name(mArraylist.get(position).getAwayName())
                        .start();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    //header

    @Override
    public long getHeaderId(int position) {
        return mArraylist.get(position).getHeaderId();
    }

    @Override
    public VhHeaderLtdToday onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ltd,parent,false);
        return new VhHeaderLtdToday(view);
    }

    @Override
    public void onBindHeaderViewHolder(VhHeaderLtdToday holder, int position) {
        holder.tvNameLeague.setText(mArraylist.get(position).getTypeId());
    }



    class ViewholderLtdToday extends RecyclerView.ViewHolder {
        private TextView tvHomeName;
        private TextView tvAgainstName;
        private TextView tvGoals;
        private TextView tvDate;

        private ImageView imgHome;
        private ImageView imgAgainst;

        private LinearLayout llDetails;
        private LinearLayout llHome;
        private LinearLayout llAgainst;
        public ViewholderLtdToday(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name_Today);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name_Today);
            tvGoals = (TextView) itemView.findViewById(R.id.tv_goals_Today);
            tvDate = (TextView) itemView.findViewById(R.id.tvDateToday);

            imgHome = (ImageView) itemView.findViewById(R.id.home_icon_Today);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon_Today);

            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetailsToday);
            llHome = (LinearLayout) itemView.findViewById(R.id.llHomeToday);
            llAgainst = (LinearLayout) itemView.findViewById(R.id.llAgainstToday);
        }
    }

    class VhHeaderLtdToday extends RecyclerView.ViewHolder {
        private TextView tvNameLeague;
        public VhHeaderLtdToday(View itemView) {
            super(itemView);
            tvNameLeague = (TextView)itemView.findViewById(R.id.tv_time_ltd);
        }
    }
}