package com.androidbelieve.footballlivescore.acitivities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.AgainstPass;
import com.androidbelieve.footballlivescore.network.apis.AuthApi;
import com.androidbelieve.footballlivescore.network.core.Callback;
import com.androidbelieve.footballlivescore.util.CheckTeamNameSetLogo;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit.RetrofitError;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/28/15.
 */
@EActivity(R.layout.match_detail_activity)
public class MatchDetailActivity extends AppCompatActivity {
    private boolean isFirst = true;
    private TextView mtvHomeName;
    private TextView mtvAgainstName;
    private TextView mtvHomeWin;
    private TextView mtvDraws;
    private TextView mtvAgainstWin;
    private ImageView mImgIconHome;
    private ImageView mImgIconAgainst;

    @ViewById(R.id.recycle_history_against)
    RecyclerView mRecycleView;

    @ViewById(R.id.dialog_history_against)
    MaterialProgressBar mDialog;
    @ViewById(R.id.mToolBar)
    LinearLayout mToolbar;

    @Extra("HOME_NAME")
    String mNameHome;

    @Extra("DIF_NAME")
    String mNameAgainst;

    @Extra("URL_HISTORY")
    String url;

    private View mHeaderView;

    private ParallaxRecyclerAdapter<AgainstPass> myAdapter;
    private ArrayList<AgainstPass> mDatas;

    @AfterViews
    void afterViews() {
        initView();
        setData();
        setAdapterAnimation();
        getHistoryAgainst(url.substring(29, url.length()));
    }

    @Click(R.id.img_back)
    void Close() {
        finish();
    }

    private void initView() {

        mRecycleView.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this));

        mHeaderView = LayoutInflater.from(this).inflate(
                R.layout.mytest, mRecycleView, false);

        mtvHomeName = (TextView) mHeaderView.findViewById(R.id.home_name_details_header);
        mtvAgainstName = (TextView) mHeaderView.findViewById(R.id.against_name_details_header);
        mtvHomeWin = (TextView) mHeaderView.findViewById(R.id.tvHomeWin);
        mtvAgainstWin = (TextView) mHeaderView.findViewById(R.id.tvAgainsWin);
        mtvDraws = (TextView) mHeaderView.findViewById(R.id.tvDraws);

        mImgIconAgainst = (ImageView) mHeaderView.findViewById(R.id.against_icon_detail_header);
        mImgIconHome = (ImageView) mHeaderView.findViewById(R.id.home_icon_detail_header);
    }

    public void setData() {
        CheckTeamNameSetLogo.setLogo(MatchDetailActivity.this, mImgIconHome, mNameHome);
        CheckTeamNameSetLogo.setLogo(MatchDetailActivity.this, mImgIconAgainst, mNameAgainst);
        mtvHomeName.setText(mNameHome);
        mtvAgainstName.setText(mNameAgainst);
    }

    public void getHistoryAgainst(String id) {
        mDialog.setVisibility(View.VISIBLE);
        AuthApi.getDetailsMatch(id, new Callback<AgainstPass>() {
            @Override
            public void success(AgainstPass againstPass) {
                mDatas.add(againstPass);
                myAdapter.notifyDataSetChanged();
                mDialog.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                mDialog.setVisibility(View.GONE);
            }
        });
    }

    private void setAdapterAnimation() {
        mDatas = new ArrayList<>();
        myAdapter = new ParallaxRecyclerAdapter<AgainstPass>(mDatas) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<AgainstPass> parallaxRecyclerAdapter, int i) {
                MyViewHolder myHolder = (MyViewHolder) viewHolder;
                if (mDatas.size() != 0) {
                    try {
                        ((MyViewHolder) viewHolder).mTvTimeAgainst.setText(getSesson(getCurentTime(getConvertDate(mDatas.get(0).getHead2head().getFixtures().get(i).getDate()))) + ", Vòng " + mDatas.get(0).getHead2head().getFixtures().get(i).getMatchday());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    mtvHomeWin.setText("Thắng " + mDatas.get(0).getHead2head().getHomeTeamWins());
                    mtvAgainstWin.setText("Thắng " + mDatas.get(0).getHead2head().getAwayTeamWins());
                    mtvDraws.setText("Hòa " + mDatas.get(0).getHead2head().getDraws());


                    CheckTeamNameSetLogo.setLogo(MatchDetailActivity.this, myHolder.mImgIconAgainstTeam, mDatas.get(0).getHead2head().getFixtures().get(i).getAwayTeamName());
                    CheckTeamNameSetLogo.setLogo(MatchDetailActivity.this, myHolder.mImgIconHomeTeam, mDatas.get(0).getHead2head().getFixtures().get(i).getHomeTeamName());
                    CheckTeamNameSetLogo.checkTeamWin(MatchDetailActivity.this, checkTeamWin(mDatas.get(0).getHead2head().getFixtures().get(i).getResult().getGoalsHomeTeam(), mDatas.get(0).getHead2head().getFixtures().get(i).getResult().getGoalsAwayTeam()), myHolder.mTvHomeName, myHolder.mTvAgainstName);

                    myHolder.mTvGoals.setText(mDatas.get(0).getHead2head().getFixtures().get(i).getResult().getGoalsHomeTeam() + " - " + mDatas.get(0).getHead2head().getFixtures().get(i).getResult().getGoalsAwayTeam());

                    myHolder.mTvHomeName.setText(mDatas.get(0).getHead2head().getFixtures().get(i).getHomeTeamName());
                    myHolder.mTvAgainstName.setText(mDatas.get(0).getHead2head().getFixtures().get(i).getAwayTeamName());
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<AgainstPass> parallaxRecyclerAdapter, int i) {
                View rootView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_history_against, viewGroup, false);
                return new MyViewHolder(rootView);
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<AgainstPass> parallaxRecyclerAdapter) {
                if (mDatas.size() != 0)
                    return mDatas.get(0).getHead2head().getFixtures().size();
                return 0;
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                ImageView mImgIconHomeTeam;
                ImageView mImgIconAgainstTeam;

                TextView mTvHomeName;
                TextView mTvGoals;
                TextView mTvAgainstName;
                TextView mTvTimeAgainst;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    mImgIconHomeTeam = (ImageView) itemView.findViewById(R.id.home_icon_item_detail);
                    mImgIconAgainstTeam = (ImageView) itemView.findViewById(R.id.against_icon_item_detail);
                    mTvHomeName = (TextView) itemView.findViewById(R.id.home_name_item_detail_item);
                    mTvAgainstName = (TextView) itemView.findViewById(R.id.against_name_item_detail_item);
                    mTvGoals = (TextView) itemView.findViewById(R.id.tv_goals_item_detail);
                    mTvTimeAgainst = (TextView) itemView.findViewById(R.id.tv_time_ltd);
                }
            }

            private int checkTeamWin(int homeGoals, int againstGoal) {
                if (homeGoals > againstGoal) {
                    return 0;
                } else if (homeGoals < againstGoal) {
                    return 1;
                }
                return 2;
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
                SimpleDateFormat postFormater = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                String newDateStr = postFormater.format(date);
                return newDateStr;
            }

            public String getCurentTime(String time) throws ParseException {
                // From TimeZone Etc/UTC
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                TimeZone tz = TimeZone.getTimeZone("Etc/UTC");
                formatter.setTimeZone(tz);
                Date date = formatter.parse(time);

                // To TimeZone America/New_York
                SimpleDateFormat sdfAmerica = new SimpleDateFormat("MM/dd/yyyy");
                TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
                sdfAmerica.setTimeZone(tzInAmerica);

                String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
                return sDateInAmerica;
            }

            private String getSesson(String date) {
                // 27/12/2015
                int month = Integer.parseInt(date.substring(0, 2));
                int year = Integer.parseInt(date.substring(8, date.length()));

                if (month <= 6) {
                    return "Mùa " + "20" + (year - 1) + " - " + "20" + year;
                }
                return "Mùa " + "20" + (year) + " - " + "20" + (year + 1);
            }
        };
        myAdapter.setParallaxHeader(mHeaderView, mRecycleView);
        mRecycleView.setAdapter(myAdapter);
    }
}
