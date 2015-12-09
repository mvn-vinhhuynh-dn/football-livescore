package com.androidbelieve.footballlivescore.ltd_today_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.adapter.KqTodayAdapter;
import com.androidbelieve.footballlivescore.models.LtdToday;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 12/3/15.
 */
@EFragment(R.layout.kq_today_fragment)
public class Kq_today_fragment extends BaseFragment {
    private Tracker mTracker;
    private int headerID = 0;
    private String typeID = "";
    private String classCup;
    private String classAlt;
    private String classOdds;
    private Document document;

    private ArrayList<LtdToday> mArraylist = new ArrayList<>();
    private KqTodayAdapter mAdapter;
    private final String BASE_URL = "http://livescore.bongdaplus.vn";
    @ViewById(R.id.recycle_kq_today)
    RecyclerView mRecycleKqToday;
    @ViewById(R.id.process_dialog_kq_today)
    MaterialProgressBar mProgressDialog;
    @ViewById(R.id.tvFail)
    TextView mTvFail;


    @AfterViews
    void afterView() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        configRecycleView();
        setAdapter();
        loadData();
    }

    private void setAdapter() {
        mAdapter = new KqTodayAdapter(getActivity(), mArraylist);
        mRecycleKqToday.setAdapter(mAdapter);
        //Add header sticky
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleKqToday.addItemDecoration(headersDecoration);
    }

    private void configRecycleView() {
        //Config recycleView
        mRecycleKqToday.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    @Background
    public void loadData() {
        mProgressDialog.setVisibility(View.VISIBLE);

        Connection con = Jsoup.connect(BASE_URL)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                .timeout(10000);
        try {
            Connection.Response resp = con
                    .ignoreHttpErrors(true).followRedirects(true).execute();
            if (resp.statusCode() == 200) {
                document = con.get();
                if (document != null) {
                    getdata();
                }
            } else if (resp.statusCode() == 307) {
                String sUrl = "";
                String sNewUrl = resp.header("Location");
                if (sNewUrl != null && sNewUrl.length() > 7)
                    sUrl = sNewUrl;
                resp = Jsoup.connect(sUrl).
                        timeout(10000).execute();
                document = resp.parse();
                if (document != null) {
                    getdata();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setUiApplication();
    }

    private void getdata() {
        Element element = document.select("div.fixtbl").first().select("tbody").first();
        Elements tr = element.select("tr");

        for (int i = 1; i < tr.size(); i++) {
            if (!tr.get(i).attr("xcup").equals("")) {

                if (Integer.valueOf(tr.get(i).attr("xcup")) == 10
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 18
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 8
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 9
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 13
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 7
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 16) {

                    if (tr.get(i).attr("class").equals("cups")) {
                        headerID++;
                        typeID = tr.get(i).text();
                    } else if (tr.get(i).attr("xstate").equals("3")) {
                        LtdToday ltdToday = new LtdToday();

                        ltdToday.setHeaderId(headerID);
                        ltdToday.setTypeId(typeID);
                        ltdToday.setDate(tr.get(i).select("td.cal").first().text());
                        ltdToday.setHomeName(tr.get(i).select("td.hme").first().text());
                        String linkHome = tr.get(i).select("td.hme").first().select("a").first().attr("href");
                        ltdToday.setLinkHome(BASE_URL + linkHome.substring(0, 9) + "/cau-thu" + linkHome.substring(9, linkHome.length()));
                        ltdToday.setImgHome(tr.get(i).select("img").first().attr("src"));
                        ltdToday.setTime(tr.get(i).select("td.sco").text());
                        ltdToday.setLinkSoccer(BASE_URL + tr.get(i).select("td.sco").select("a").first().attr("href"));
                        Elements aways = tr.get(i).select("td.awy");
                        ltdToday.setImgAway(aways.get(0).select("img").first().attr("src"));
                        ltdToday.setAwayName(aways.get(1).select("a").first().text());
                        String linkAway = aways.get(1).select("a").first().attr("href");
                        ltdToday.setLinkAway(BASE_URL + linkAway.substring(0, 9) + "/cau-thu" + linkAway.substring(9, linkAway.length()));

                        mArraylist.add(ltdToday);
                    }
                }
            }
        }
    }

    @UiThread
    public void setUiApplication() {
        if (mArraylist.size() == 0) {
            mTvFail.setVisibility(View.VISIBLE);
        } else {
            mTvFail.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
        mProgressDialog.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("KQ-Today-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
