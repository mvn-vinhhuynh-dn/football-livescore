package com.androidbelieve.drawerwithswipetabs.ChampionLeague;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
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
 * Created by phulx on 03/11/2015.
 */
public class C1Adapter extends RecyclerView.Adapter<C1Adapter.ViewHolder> implements OnRemoveListener{

    private Context mContext;
    private ArrayList<LTD> mDatas = new ArrayList<>();

    public C1Adapter(Context mContext, ArrayList<LTD> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ltd_primerlueage, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAgainstName.setText(mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        holder.tvHomeName.setText(mDatas.get(0).getFixtures().get(position).getHomeTeamName());


        if (mDatas.get(0).getFixtures().get(position)
                .getResult().getGoalsHomeTeam() == -1) {
            String day = getConvertDate(mDatas.get(0).getFixtures().get(position).getDate());
            try {
                holder.tvGoals.setText(getCurentTime(day));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.tvGoals.setText(mDatas.get(0).getFixtures().get(position)
                    .getResult().getGoalsHomeTeam() + " - " + mDatas.get(0)
                    .getFixtures().get(position).getResult().getGoalsAwayTeam());
        }
//        holder.tvGoals.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onItemStickyClick(mDatas.get(0)
//                                .getFixtures().get(position).getHomeTeamName(),
//                        mDatas.get(0).getFixtures()
//                                .get(position).getAwayTeamName(), mDatas.get(0)
//                                .getFixtures().get(position)
//                                .get_links().getSelf().getHref());
//            }
//        });
        CheckTeamNameSetLogo.setTeamName(mContext, holder.tvHomeName, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setTeamName(mContext, holder.tvAgainstName, mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, holder.imgHome, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, holder.imgAgainst, mDatas.get(0).getFixtures().get(position).getAwayTeamName());
    }

    @Override
    public long getItemId(int position) {
        if (mDatas != null && mDatas.size() > 0)
            return mDatas.get(0).getFixtures().get(position).hashCode();
        return 0;
    }

    @Override
    public void onRemove(int position) {
        mDatas.get(0).getFixtures().remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.get(0).getFixtures().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHomeName;
        private TextView tvAgainstName;
        private TextView tvGoals;
        private ImageView imgHome;
        private ImageView imgAgainst;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name);
            tvGoals = (TextView) itemView.findViewById(R.id.tv_goals);
            imgHome = (ImageView) itemView.findViewById(R.id.home_icon);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon);
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
