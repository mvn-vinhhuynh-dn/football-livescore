package com.androidbelieve.footballlivescore.stickydapterPrimer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.database.FavoriteMatch;
import com.androidbelieve.footballlivescore.footballclubinfor.FootBallClubInformationActivity_;
import com.androidbelieve.footballlivescore.interfaces.ItemStickyClick;
import com.androidbelieve.footballlivescore.interfaces.OnEditListener;
import com.androidbelieve.footballlivescore.interfaces.OnRemoveListener;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.util.CheckTeamNameSetLogo;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 22/09/14.
 */
public class PrimerAdapter extends RecyclerView.Adapter<PrimerAdapter.ViewHolder> implements OnRemoveListener, OnEditListener {

    private Context mContext;
    private ArrayList<LTD> mDatas = new ArrayList<>();
    private ItemStickyClick mListener;
    private List<FavoriteMatch> mFavorites;

    public PrimerAdapter(Context context, ArrayList<LTD> personDataProvider, ItemStickyClick itemStickyClick) {
        this.mContext = context;
        this.mDatas = personDataProvider;
        mListener = itemStickyClick;
        setHasStableIds(true);
        mFavorites = FavoriteMatch.listAll(FavoriteMatch.class);
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
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
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

        viewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(0).getFixtures().get(position).isFavorite()) {
                    mDatas.get(0).getFixtures().get(position).setFavorite(false);
                } else {
                    mDatas.get(0).getFixtures().get(position).setFavorite(true);
                }
                notifyDataSetChanged();
            }
        });

        CheckTeamNameSetLogo.setTeamName(mContext, viewHolder.tvHomeName, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setTeamName(mContext, viewHolder.tvAgainstName, mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, viewHolder.imgHome, mDatas.get(0).getFixtures().get(position).getHomeTeamName());
        CheckTeamNameSetLogo.setLogo(mContext, viewHolder.imgAgainst, mDatas.get(0).getFixtures().get(position).getAwayTeamName());
        viewHolder.llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .extra("NAME_FC", CheckTeamNameSetLogo.getUrl(mContext, mDatas.get(0).getFixtures().get(position).getHomeTeamName()))
                        .extra("NAME", mDatas.get(0).getFixtures().get(position).getHomeTeamName())
                        .start();
            }
        });

        viewHolder.llAgainst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBallClubInformationActivity_.intent(mContext)
                        .extra("NAME_FC", CheckTeamNameSetLogo.getUrl(mContext, mDatas.get(0).getFixtures().get(position).getAwayTeamName()))
                        .extra("NAME", mDatas.get(0).getFixtures().get(position).getAwayTeamName())
                        .start();
            }
        });
        checkFavorite(viewHolder, position);
    }

    private void checkFavorite(final ViewHolder holder, final int position) {
        if (mDatas.get(0).getFixtures().get(position).isFavorite() || checkFavorite(mDatas.get(0).getFixtures().get(position).getHomeTeamName(),
                mDatas.get(0).getFixtures().get(position).getAwayTeamName())) {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite);

        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
            mDatas.get(0).getFixtures().get(position).setFavorite(false);
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnDataBase(mDatas.get(0).getFixtures().get(position).getHomeTeamName()
                        , mDatas.get(0).getFixtures().get(position).getAwayTeamName())) {
                    holder.imgFavorite.setImageResource(R.drawable.ic_unfavorite);
                    mDatas.get(0).getFixtures().get(position).setFavorite(false);
                    Select.from(FavoriteMatch.class)
                            .where(Condition.prop("HOME_NAME").eq(mDatas.get(0).getFixtures().get(position).getHomeTeamName()),
                                    Condition.prop("AWAY_NAME").eq(mDatas.get(0).getFixtures().get(position).getAwayTeamName()))
                            .first().delete();

                } else {
                    holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
                    mDatas.get(0).getFixtures().get(position).setFavorite(true);
                    FavoriteMatch favoriteMatch = new FavoriteMatch(mDatas.get(0).getFixtures().get(position).getHomeTeamName(),
                            mDatas.get(0).getFixtures().get(position).getAwayTeamName(),
                            mDatas.get(0).getFixtures().get(position).getDate(), 0, mDatas.get(0).getFixtures().get(position).getDate());
                    favoriteMatch.save();
                }
            }
        });
    }

    private boolean checkFavorite(String homeName, String awayName) {
        if (mFavorites != null) {
            for (int i = 0; i < mFavorites.size(); i++) {
                if ((homeName.equals(mFavorites.get(i).getHomeName()) && (awayName.equals(mFavorites.get(i).getAwayName())))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOnDataBase(String homeName, String awayName) {
        for (int i = 0; i < FavoriteMatch.listAll(FavoriteMatch.class).size(); i++) {
            if (homeName.equals(FavoriteMatch.listAll(FavoriteMatch.class).get(i).getHomeName())
                    && awayName.equals(FavoriteMatch.listAll(FavoriteMatch.class).get(i).getAwayName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0)
            return mDatas.get(0).getFixtures().size();
        return 0;
    }

    public void onRemove(int position) {
        mDatas.get(0).getFixtures().remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onEdit(final int position) {
        // Do edit function
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHomeName;
        private TextView tvAgainstName;
        private TextView tvGoals;

        private ImageView imgHome;
        private ImageView imgAgainst;
        private ImageView imgFavorite;

        private LinearLayout llDetails;
        private LinearLayout llHome;
        private LinearLayout llAgainst;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHomeName = (TextView) itemView.findViewById(R.id.home_name);
            tvAgainstName = (TextView) itemView.findViewById(R.id.against_name);
            tvGoals = (TextView) itemView.findViewById(R.id.tv_goals);

            imgHome = (ImageView) itemView.findViewById(R.id.home_icon);
            imgFavorite = (ImageView) itemView.findViewById(R.id.img_favorite);
            imgAgainst = (ImageView) itemView.findViewById(R.id.against_icon);

            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetails);
            llHome = (LinearLayout) itemView.findViewById(R.id.llHome);
            llAgainst = (LinearLayout) itemView.findViewById(R.id.llAgainst);
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
