package com.androidbelieve.drawerwithswipetabs.ltdfootballclub;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.LtdFootballClub;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/5/15.
 */
@EActivity(R.layout.activity_schedule_fc)
public class ScheduleFcActivity extends AppCompatActivity {
    @ViewById(R.id.toolbarSchedule)
    Toolbar mToolbarSchedule;

    @ViewById(R.id.tabs_schedule)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager_schedule)
    ViewPager mPager;
    @ViewById(R.id.dialog_ltd_fc)
    MaterialProgressBar mDialog;

    private ViewPagerAdapter mAdapter;

    private ArrayList<LtdFootballClub> mDatasPlayed = new ArrayList<>();
    private ArrayList<LtdFootballClub> mDatasPlaying = new ArrayList<>();
    @Extra("URL_FC_SCHEDULE")
    String url;
    private void notifyDataSetChange() {
        Fragment playingFragment = mAdapter.getItem(0);
        ((PlayingFragment_) playingFragment).notifyDataSetChange(mDatasPlaying);
        Fragment playedFragment = mAdapter.getItem(1);
        ((PlayedFragment_) playedFragment).notifyDataSetChange(mDatasPlayed);
        mAdapter.notifyDataSetChanged();
    }

    @AfterViews
    void afterViews() {
        setSupportActionBar(mToolbarSchedule);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Lịch thi đấu đội bóng");
        }
        mToolbarSchedule.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
        setupViewPager(mPager);
        mTabLayout.setupWithViewPager(mPager);
        getData(url);
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new PlayingFragment_(), "Lượt trận sắp đấu");
        mAdapter.addFragment(new PlayedFragment_(), "Lượt trận đã đấu");
        viewPager.setAdapter(mAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Background
    void getData(String url) {
        mDialog.setVisibility(View.VISIBLE);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                    .get();
            Elements div = doc.select("div.troftbl");
            if (div != null) {
                Element child_div_played = div.get(0).select("tbody").first();
                Element child_div_playing = div.get(1).select("tbody").first();
                if (child_div_played != null) {
                    Elements list_Childs_ed = child_div_played.select("tr");
                    if (list_Childs_ed != null && list_Childs_ed.size() > 0) {
                        for (int i = 2; i < list_Childs_ed.size(); i++) {
                            LtdFootballClub playedDatas = new LtdFootballClub();
                            playedDatas.setDate(list_Childs_ed.get(i).select("td.cal").text());
                            playedDatas.setLeague(list_Childs_ed.get(i).select("td.cup").select("a").text());
                            playedDatas.setHomeTeam(list_Childs_ed.get(i).select("td.hme").select("a").text());
                            playedDatas.setResult(list_Childs_ed.get(i).select("td.sco").select("a").text());
                            playedDatas.setAwayTeam(list_Childs_ed.get(i).select("td.awy").select("a").text());
                            mDatasPlayed.add(playedDatas);
                        }
                    }
                }
                if (child_div_playing != null) {
                    Elements list_Childs_ing = child_div_playing.select("tr");
                    if (list_Childs_ing != null && list_Childs_ing.size() > 0) {
                        for (int i = 2; i < list_Childs_ing.size(); i++) {
                            LtdFootballClub playedDatas = new LtdFootballClub();
                            playedDatas.setDate(list_Childs_ing.get(i).select("td.cal").text());
                            playedDatas.setLeague(list_Childs_ing.get(i).select("td.cup").select("a").text());
                            playedDatas.setHomeTeam(list_Childs_ing.get(i).select("td.hme").select("a").text());
                            playedDatas.setResult(list_Childs_ing.get(i).select("td.tme ").select("a").text());
                            playedDatas.setAwayTeam(list_Childs_ing.get(i).select("td.awy").select("a").text());
                            mDatasPlaying.add(playedDatas);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateUI();
    }

    @UiThread
    void UpdateUI() {
        mDialog.setVisibility(View.GONE);
        notifyDataSetChange();
    }
}
