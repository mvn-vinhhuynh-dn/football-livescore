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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ltd_today, parent, false);
        return new ViewholderLtdToday(view);
    }

    @Override
    public void onBindViewHolder(final ViewholderLtdToday holder, final int position) {
        holder.tvDate.setText(mArraylist.get(position).getDate());
        holder.tvHomeName.setText(mArraylist.get(position).getHomeName());
        Picasso.with(mContext).load(mArraylist.get(position).getImgHome()).into(holder.imgHome);
        holder.tvGoals.setText(mArraylist.get(position).getTime());
        holder.tvAgainstName.setText(mArraylist.get(position).getAwayName());
        Picasso.with(mContext).load(mArraylist.get(position).getImgAway()).into(holder.imgAgainst);
        if (mArraylist.get(position).getCatran() != null) {
            holder.tvHdp_rte.setText(mArraylist.get(position).getCatran().getHdp_rte());
            holder.tvHdp_1.setText(mArraylist.get(position).getCatran().getHdp_1());
            holder.tvHdp_2.setText(mArraylist.get(position).getCatran().getHdp_2());
            holder.tvOue_rte.setText(mArraylist.get(position).getCatran().getOue_rte());
            holder.tvOue_1.setText(mArraylist.get(position).getCatran().getOue_1());
            holder.tvOue_2.setText(mArraylist.get(position).getCatran().getOue_2());

            holder.tvHdp_rte_h1.setText(mArraylist.get(position).getHiep1().getHdp_rte());
            holder.tvHdp_1_h1.setText(mArraylist.get(position).getHiep1().getHdp_1());
            holder.tvHdp_2_h1.setText(mArraylist.get(position).getHiep1().getHdp_2());
            holder.tvOue_rte_h1.setText(mArraylist.get(position).getHiep1().getOue_rte());
            holder.tvOue_1_h1.setText(mArraylist.get(position).getHiep1().getOue_1());
            holder.tvOue_2_h1.setText(mArraylist.get(position).getHiep1().getOue_2());
        }
        if (mArraylist.get(position).getCatran() != null && mArraylist.get(position).getCatran().isOpen()) {
            holder.llRateLtdToday.setVisibility(View.VISIBLE);
        } else {
            holder.llRateLtdToday.setVisibility(View.GONE);
        }

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

        holder.tvRateToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArraylist.get(position).getCatran().isOpen()) {
                    mArraylist.get(position).getCatran().setOpen(false);
                } else {
                    mArraylist.get(position).getCatran().setOpen(true);
                }
                notifyDataSetChanged();
            }
        });

        holder.llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ltd, parent, false);
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
        private TextView tvHdp_rte, tvHdp_1, tvHdp_2, tvOue_rte, tvOue_1, tvOue_2;
        private TextView tvHdp_rte_h1, tvHdp_1_h1, tvHdp_2_h1, tvOue_rte_h1, tvOue_1_h1, tvOue_2_h1;
        private TextView tvRateToday;

        private ImageView imgHome;
        private ImageView imgAgainst;
        private ImageView imgRate;

        private LinearLayout llDetails;
        private LinearLayout llHome;
        private LinearLayout llAgainst;
        private LinearLayout llRateLtdToday;

        public ViewholderLtdToday(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name_Today);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name_Today);
            tvGoals = (TextView) itemView.findViewById(R.id.tv_goals_Today);
            tvDate = (TextView) itemView.findViewById(R.id.tvDateToday);

            tvHdp_rte = (TextView) itemView.findViewById(R.id.tvHdp_rte);
            tvHdp_1 = (TextView) itemView.findViewById(R.id.tvHdp_1);
            tvHdp_2 = (TextView) itemView.findViewById(R.id.tvHdp_2);
            tvOue_rte = (TextView) itemView.findViewById(R.id.tvOue_rte);
            tvOue_1 = (TextView) itemView.findViewById(R.id.tvOue_1);
            tvOue_2 = (TextView) itemView.findViewById(R.id.tvOue_2);

            tvHdp_rte_h1 = (TextView) itemView.findViewById(R.id.tvHdp_rte_h1);
            tvHdp_1_h1 = (TextView) itemView.findViewById(R.id.tvHdp_1_h1);
            tvHdp_2_h1 = (TextView) itemView.findViewById(R.id.tvHdp_2_h1);
            tvOue_rte_h1 = (TextView) itemView.findViewById(R.id.tvOue_rte_h1);
            tvOue_1_h1 = (TextView) itemView.findViewById(R.id.tvOue_1_h1);
            tvOue_2_h1 = (TextView) itemView.findViewById(R.id.tvOue_2_h1);

            tvRateToday = (TextView) itemView.findViewById(R.id.tvRateToday);

            imgHome = (ImageView) itemView.findViewById(R.id.home_icon_Today);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon_Today);
            imgRate = (ImageView) itemView.findViewById(R.id.imgRate);

            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetailsToday);
            llHome = (LinearLayout) itemView.findViewById(R.id.llHomeToday);
            llAgainst = (LinearLayout) itemView.findViewById(R.id.llAgainstToday);
            llRateLtdToday = (LinearLayout) itemView.findViewById(R.id.llRateLtdToday);
        }
    }

    class VhHeaderLtdToday extends RecyclerView.ViewHolder {
        private TextView tvNameLeague;

        public VhHeaderLtdToday(View itemView) {
            super(itemView);
            tvNameLeague = (TextView) itemView.findViewById(R.id.tv_time_ltd);
        }
    }
}