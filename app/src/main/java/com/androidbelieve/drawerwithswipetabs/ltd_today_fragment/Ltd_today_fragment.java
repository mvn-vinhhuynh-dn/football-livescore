package com.androidbelieve.drawerwithswipetabs.ltd_today_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.abstracts.BaseFragment;
import com.androidbelieve.drawerwithswipetabs.adapter.LtdTodayAdapter;
import com.androidbelieve.drawerwithswipetabs.models.LtdToday;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by phulx on 11/11/2015.
 */
@EFragment(R.layout.ltd_today_fragment)
public class Ltd_today_fragment extends BaseFragment {

    private int headerID = 0;
    private String typeID = "";
    private String classCup;
    private String classAlt;
    private String classOdds;
    private Document document;

    private ArrayList<LtdToday> mArraylist = new ArrayList<>();
    private LtdTodayAdapter mAdapter;
    private final String BASE_URL = "http://livescore.bongdaplus.vn";
    @ViewById(R.id.recycle_ltd_today)
    RecyclerView mRecycleLtdToday;
    @ViewById(R.id.process_dialog_ltd_today)
    MaterialProgressBar mProgressDialog;


    @AfterViews
    void afterView() {
        configRecycleView();
        setAdapter();
        loadData();
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

    private void getdata(){
        Elements element = document.select("div.fixtbl").first().select("tbody").first().select("tr");
        classCup = document.select("div.fixtbl").first().select("tbody").first().select("tr.cups").first().toString();
        classAlt = document.select("div.fixtbl").first().select("tbody").first().select("tr.alt").first().toString();
        classOdds = document.select("div.fixtbl").first().select("tbody").first().select("tr.odds").first().toString();
        for (int i = 1; i < element.size(); i++) {
            if (element.get(i).toString().contains(classCup.substring(0, 16))) {
                headerID++;
                typeID = element.get(i).text();
            } else if ((element.get(i).toString().contains(classAlt.substring(0, 15))) || (element.get(i).toString().contains("<tr xcup="))) {
                LtdToday ltdToday = new LtdToday();

                ltdToday.setHeaderId(headerID);
                ltdToday.setTypeId(typeID);
                ltdToday.setDate(element.get(i).select("td.cal").first().text());
                ltdToday.setHomeName(element.get(i).select("td.hme").first().text());
                String linkHome = element.get(i).select("td.hme").first().select("a").first().attr("href");
                ltdToday.setLinkHome(BASE_URL + linkHome.substring(0, 9) + "/cau-thu" + linkHome.substring(9, linkHome.length()));
                ltdToday.setImgHome(element.get(i).select("img").first().attr("src"));
                ltdToday.setTime(element.get(i).select("td.sco").text());
                ltdToday.setLinkSoccer(BASE_URL + element.get(i).select("td.sco").select("a").first().attr("href"));
                Elements aways = element.get(i).select("td.awy");
                ltdToday.setImgAway(aways.get(0).select("img").first().attr("src"));
                ltdToday.setAwayName(aways.get(1).select("a").first().text());
                String linkAway = aways.get(1).select("a").first().attr("href");
                ltdToday.setLinkAway(BASE_URL + linkAway.substring(0, 9) + "/cau-thu" + linkAway.substring(9, linkAway.length()));
                if (element.get(i + 1).toString().contains(classOdds.substring(0, 16))) {

                    Elements oddrow = element.get(i+1).select("table.oddrow").first().select("tr");
                    Elements td_hdp_all = oddrow.get(0).select("td.hdp");
                    Elements td_oue_all = oddrow.get(0).select("td.oue");
                    Elements td_hdp_h1 = oddrow.get(1).select("td.hdp");
                    Elements td_oue_h1 = oddrow.get(1).select("td.oue");

                    LtdToday.Catran catran = ltdToday. new Catran();
                    LtdToday.Hiep1 hiep1 = ltdToday. new Hiep1();

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

    @UiThread
    public void setUiApplication() {
        mAdapter.notifyDataSetChanged();
        mProgressDialog.setVisibility(View.GONE);
    }

}
