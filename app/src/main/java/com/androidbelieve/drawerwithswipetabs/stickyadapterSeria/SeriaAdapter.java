package com.androidbelieve.drawerwithswipetabs.stickyadapterSeria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.footballclubinfor.FootBallClubInformationActivity_;
import com.androidbelieve.drawerwithswipetabs.interfaces.ItemStickyClick;
import com.androidbelieve.drawerwithswipetabs.interfaces.OnEditListener;
import com.androidbelieve.drawerwithswipetabs.interfaces.OnRemoveListener;
import com.androidbelieve.drawerwithswipetabs.models.LTD;
import com.androidbelieve.drawerwithswipetabs.util.CheckTeamNameSetLogo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/27/15.
 */
public class SeriaAdapter extends RecyclerView.Adapter<SeriaAdapter.ViewHolder> implements OnRemoveListener, OnEditListener {
    private ItemStickyClick mListener;
    private Context mContext;
    private ArrayList<LTD> mDatas = new ArrayList<>();

    public SeriaAdapter(Context context, ArrayList<LTD> personDataProvider, ItemStickyClick itemStickyClick) {
        this.mContext = context;
        mListener = itemStickyClick;
        this.mDatas = personDataProvider;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ltd_primerlueage, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        if (mDatas != null && mDatas.size() > 0)
            return mDatas.get(0).getFixtures().get(position).hashCode();
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvAgainstName.setText(mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        viewHolder.tvHomeName.setText(mDatas.get(0).getFixtures().get(position).getHomeTeamName());


        if (mDatas.get(0).getFixtures().get(position)
                .getResult().getGoalsHomeTeam() == -1) {
            String day = getConvertDate(mDatas.get(0).getFixtures().get(position).getDate());
            try {
                viewHolder.tvGoals.setText(getCurentTime(day));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            viewHolder.tvGoals.setText(mDatas.get(0).getFixtures().get(position)
                    .getResult().getGoalsHomeTeam() + " - " + mDatas.get(0)
                    .getFixtures().get(position).getResult().getGoalsAwayTeam());
        }
        viewHolder.llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemStickyClick(mDatas.get(0)
                                .getFixtures().get(position).getHomeTeamName(),
                        mDatas.get(0).getFixtures()
                                .get(position).getAwayTeamName(), mDatas.get(0)
                                .getFixtures().get(position)
                                .get_links().getSelf().getHref());
            }
        });
        CheckTeamNameSetLogo.setTeamName(mContext, viewHolder.tvHomeName, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setTeamName(mContext, viewHolder.tvAgainstName, mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, viewHolder.imgHome, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, viewHolder.imgAgainst, mDatas.get(0).getFixtures().get(position).getAwayTeamName());

        viewHolder.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .extra("NAME_FC", CheckTeamNameSetLogo.getUrl(mContext, mDatas.get(0).getFixtures().get(position).getHomeTeamName()))
                        .extra("NAME", mDatas.get(0).getFixtures().get(position).getHomeTeamName())
                        .start();
            }
        });

        viewHolder.imgAgainst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .extra("NAME_FC", CheckTeamNameSetLogo.getUrl(mContext, mDatas.get(0).getFixtures().get(position).getAwayTeamName()))
                        .extra("NAME", mDatas.get(0).getFixtures().get(position).getAwayTeamName())
                        .start();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0)
            return mDatas.get(0).getFixtures().size();
        return 0;
    }

    @Override
    public void onRemove(int position) {
        mDatas.get(0).getFixtures().remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onEdit(final int position) {
//        final EditText edit = new EditText(mContext);
//        edit.setTextColor(Color.BLACK);
//        edit.setText(personDataProvider.getItems().get(position));
//        new AlertDialog.Builder(mContext).setTitle(R.string.edit).setView(edit).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String name = edit.getText().toString();
//                personDataProvider.update(position, name);
//                notifyItemChanged(position);
//            }
//        }).create().show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHomeName;
        private TextView tvAgainstName;
        private TextView tvGoals;

        private ImageView imgHome;
        private ImageView imgAgainst;
        private LinearLayout llDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name);
            tvGoals = (TextView) itemView.findViewById(R.id.tv_goals);

            imgHome = (ImageView) itemView.findViewById(R.id.home_icon);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon);
            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetails);
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

}