package com.androidbelieve.footballlivescore.footballclubinfor;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.footballplayerinfor.PlayerInformationDialog;
import com.androidbelieve.footballlivescore.footballplayerinfor.PlayerInformationDialog_;
import com.androidbelieve.footballlivescore.ltdfootballclub.ScheduleFcActivity_;
import com.androidbelieve.footballlivescore.models.FCInformation;
import com.androidbelieve.footballlivescore.util.CheckTeamNameSetLogo;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
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
 * Created by VinhHlb on 11/4/15.
 */
@EActivity(R.layout.info_football_club)
public class FootBallClubInformationActivity extends AppCompatActivity implements FCInformationAdapter.ShowDetailsPlayer {
    @ViewById(R.id.recycle_view_fc_infor)
    RecyclerView mRecycleView;

    @ViewById(R.id.imgLogoFC)
    ImageView mImgLogoFC;

    @ViewById(R.id.tvYear)
    TextView mTvYear;

    @ViewById(R.id.tvFCName)
    TextView mTvName;

    @ViewById(R.id.tvAddress)
    TextView mTvAddress;

    @ViewById(R.id.tvPhoneNum)
    TextView mTvPhoneNum;

    @ViewById(R.id.tvStadium)
    TextView mTvStadium;

    @ViewById(R.id.tvCountry)
    TextView mTvCountry;

    @ViewById(R.id.progress_dialog_fc_info)
    MaterialProgressBar mDialog;

    @ViewById(R.id.toolbarInfo)
    Toolbar mToolbarInfo;

    FCInformationAdapter mAdapter;
    private ArrayList<FCInformation> mDatas = new ArrayList<>();
    @Extra("NAME_FC")
    String myUrl;
    @Extra("NAME")
    String name;

    String year = "";
    private String address = "";
    private String country = "";
    private String fone = "";
    private String stadium = "";
    private String urlImgFc = "";
    private long mLastClickTime;

    @AfterViews
    void afterViews() {
        setSupportActionBar(mToolbarInfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông Tin Đội Bóng");
        mToolbarInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initView();
        setAdapter();
        if (myUrl != null && !myUrl.equals("")) {
            getData(myUrl);
        }
    }

    private void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(FootBallClubInformationActivity.this));
    }

    private void setAdapter() {
        mAdapter = new FCInformationAdapter(this, mDatas, this);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onShowDetails(FCInformation myData) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        PlayerInformationDialog mDialog = PlayerInformationDialog_.builder()
                .myData(myData)
                .build();
        mDialog.show(getSupportFragmentManager(), "PLAYER_INFORMATION");
    }


    @Click(R.id.tvFCName)
    void viewMore() {
        ScheduleFcActivity_.intent(this)
                .extra("URL_FC_SCHEDULE", CheckTeamNameSetLogo.getUrlLtdFc(this, mTvName.getText().toString().trim()))
                .start();
        overridePendingTransition(R.anim.your_right_to_left, R.anim.your_left_to_right);
    }

    @Background
    void getData(String url) {
        mDialog.setVisibility(View.VISIBLE);
        Document docResult = null;
        try {
            docResult = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
            Element div = docResult.select("div.teambox").first();
            Element div_er = docResult.select("div.mchinf").first();
            if (div != null) {
                Element child_divs = div.select("tbody").first();
                Elements trChilds = child_divs.select("tr");
                if (trChilds != null) {
                    //Set name and logo
                    Element elementName = trChilds.get(0).select("img.tmlogo").first();
                    //set info
                    year = (trChilds.get(1).select("b").first().text());
                    address = (trChilds.get(2).select("td").first().text());
                    country = (trChilds.get(3).select("b").first().text());
                    fone = (trChilds.get(4).select("td").first().text());
                    stadium = (trChilds.get(6).select("b").first().text());
                    urlImgFc = elementName.attr("src");
                }
            }

            if (div_er != null) {
                Element div_players = div_er.select("div.reztbl").first();
                if (div_players != null) {
                    Element player_child = div_players.select("tbody").first();
                    Elements listPlayers = player_child.select("tr");
                    for (int i = 2; i < listPlayers.size(); i++) {
                        FCInformation data = new FCInformation();
                        data.setName(listPlayers.get(i).select("td.nme").select("a").text());
                        data.setAvatarUrl(listPlayers.get(i).select("td.lgo").select("img").attr("src"));
                        data.setCountry(listPlayers.get(i).getElementsByIndexEquals(2).select("b").text());
                        data.setPosition(listPlayers.get(i).getElementsByIndexEquals(3).select("td").text());
                        data.setUrlInformation(listPlayers.get(i).select("td.nme").select("a").attr("href"));
                        mDatas.add(data);
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
        mTvName.setText(name);
        mTvName.setVisibility(View.VISIBLE);
        if (urlImgFc != null && !urlImgFc.equals(""))
            Picasso.with(FootBallClubInformationActivity.this).load(urlImgFc).into(mImgLogoFC);
        //set info
        mTvYear.setText(year);
        mTvAddress.setText(address);
        mTvCountry.setText(country);
        mTvPhoneNum.setText(fone);
        mTvStadium.setText(stadium);
        mAdapter.notifyDataSetChanged();
        mDialog.setVisibility(View.GONE);
    }
}
