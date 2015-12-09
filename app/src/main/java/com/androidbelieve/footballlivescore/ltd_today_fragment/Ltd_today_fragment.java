package com.androidbelieve.footballlivescore.ltd_today_fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.adapter.LtdTodayAdapter;
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
 * Created by phulx on 11/11/2015.
 */
@EFragment(R.layout.ltd_today_fragment)
public class Ltd_today_fragment extends BaseFragment {
    private Tracker mTracker;
    private int headerID = 0;
    private String typeID = "";
    private Document document;
    private boolean checkprocess = true;

    private ArrayList<LtdToday> mArraylist = new ArrayList<>();
    private LtdTodayAdapter mAdapter;
    private final String BASE_URL = "http://livescore.bongdaplus.vn";
    @ViewById(R.id.recycle_ltd_today)
    RecyclerView mRecycleLtdToday;
    @ViewById(R.id.process_dialog_ltd_today)
    MaterialProgressBar mProgressDialog;
    @ViewById(R.id.RefreshLtdToday)
    SwipeRefreshLayout mRefreshLayout;
    @ViewById(R.id.tvLtdFail)
    TextView mTvLtdFail;


    @AfterViews
    void afterView() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        configRecycleView();
        setAdapter();
        loadData();

        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setColorSchemeResources(R.color.orange);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        checkprocess = false;
                        loadData();
                        mRefreshLayout.setRefreshing(false);
                    }

                }, 4000);

            }
        });
    }

    private void setAdapter() {
        mAdapter = new LtdTodayAdapter(getActivity(), mArraylist);
        mRecycleLtdToday.setAdapter(mAdapter);
        //Add header sticky
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleLtdToday.addItemDecoration(headersDecoration);
    }

    private void configRecycleView() {
        //Config recycleView
        mRecycleLtdToday.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    @Background
    public void loadData() {
        if (checkprocess) {
            mProgressDialog.setVisibility(View.VISIBLE);
        }

        Connection con = Jsoup.connect(BASE_URL)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                .timeout(10000);
        try {
            Connection.Response resp = con
                    .ignoreHttpErrors(true).followRedirects(true).execute();
            if (resp.statusCode() == 200) {
                document = con.get();
                if (document != null) {
                    if (getdata()) {
                        setUiApplication();
                    }
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
                    if (getdata()) {
                        setUiApplication();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean getdata() {
        mArraylist.clear();
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
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 16
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 93
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 95
                        || Integer.valueOf(tr.get(i).attr("xcup")) == 12) {

                    if (tr.get(i).attr("class").equals("cups")) {
                        headerID++;
                        typeID = tr.get(i).text();
                    } else if (!tr.get(i).attr("xstate").equals("3")) {
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
                        if (tr.get(i + 1).attr("class").equals("odds")) {

                            Elements oddrow = tr.get(i + 1).select("table.oddrow").first().select("tr");
                            Elements td_hdp_all = oddrow.get(0).select("td.hdp");
                            Elements td_oue_all = oddrow.get(0).select("td.oue");
                            Elements td_hdp_h1 = oddrow.get(1).select("td.hdp");
                            Elements td_oue_h1 = oddrow.get(1).select("td.oue");

                            LtdToday.Catran catran = ltdToday.new Catran();
                            LtdToday.Hiep1 hiep1 = ltdToday.new Hiep1();

                            catran.setHdp_rte(td_hdp_all.get(0).text());
                            catran.setHdp_1(td_hdp_all.get(1).text());
                            catran.setHdp_2(td_hdp_all.get(2).text());
                            catran.setOue_rte(td_oue_all.get(0).text());
                            catran.setOue_1(td_oue_all.get(1).text());
                            catran.setOue_2(td_oue_all.get(2).text());

                            hiep1.setHdp_rte(td_hdp_h1.get(0).text());
                            hiep1.setHdp_1(td_hdp_h1.get(1).text());
                            hiep1.setHdp_2(td_hdp_h1.get(2).text());
                            hiep1.setOue_rte(td_oue_h1.get(0).text());
                            hiep1.setOue_1(td_oue_h1.get(1).text());
                            hiep1.setOue_2(td_oue_h1.get(2).text());

                            ltdToday.setCatran(catran);
                            ltdToday.setHiep1(hiep1);
                        }
                        mArraylist.add(ltdToday);
                    }
                }
            }
        }
        if (mArraylist.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @UiThread
    public void setUiApplication() {
        if (mArraylist.size() == 0) {
            mTvLtdFail.setVisibility(View.VISIBLE);
        } else {
            mTvLtdFail.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
        if (checkprocess) {
            mProgressDialog.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("LTD-Today-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
