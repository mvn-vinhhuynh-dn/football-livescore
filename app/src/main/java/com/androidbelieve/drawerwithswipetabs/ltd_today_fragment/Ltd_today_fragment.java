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

    private ArrayList<LtdToday> mArraylist;
    private LtdTodayAdapter mAdapter;

    @ViewById(R.id.recycle_ltd_today)
    RecyclerView mRecycleLtdToday;
    @ViewById(R.id.process_dialog_ltd_today)
    MaterialProgressBar mProgressDialog;


    @AfterViews
    void afterview() {
        mProgressDialog.setVisibility(View.VISIBLE);
        mArraylist = new ArrayList<>();
        mAdapter = new LtdTodayAdapter(getActivity(), mArraylist);
        mRecycleLtdToday.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
        mRecycleLtdToday.setAdapter(mAdapter);
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleLtdToday.addItemDecoration(headersDecoration);

        loadData();
    }

    @Background
    public void loadData() {
        Document document = null;
        try {
            document = Jsoup.connect("http://livescore.bongdaplus.vn/")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document != null) {
            getdata(document);
            setUiApplication();
        }
    }

    private void getdata(Document document) {

        int headerID = 0;
        String typeID = "";

        Elements element = document.select("div.fixtbl").first().select("tbody").first().select("tr");

        String classCup = document.select("div.fixtbl").first().select("tbody").first().select("tr.cups").first().toString();
        String classAlt = document.select("div.fixtbl").first().select("tbody").first().select("tr.alt").first().toString();

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
                ltdToday.setLinkHome("http://livescore.bongdaplus.vn" + linkHome.substring(0, 9) + "/cau-thu" + linkHome.substring(9, linkHome.length()));
                ltdToday.setImgHome(element.get(i).select("img").first().attr("src"));
                ltdToday.setTime(element.get(i).select("td.sco").text());
                ltdToday.setLinkSoccer("http://livescore.bongdaplus.vn" + element.get(i).select("td.sco").select("a").first().attr("href"));
                Elements aways = element.get(i).select("td.awy");
                ltdToday.setImgAway(aways.get(0).select("img").first().attr("src"));
                ltdToday.setAwayName(aways.get(1).select("a").first().text());
                String linkAway = aways.get(1).select("a").first().attr("href");
                ltdToday.setLinkAway("http://livescore.bongdaplus.vn" + linkAway.substring(0, 9) + "/cau-thu" + linkAway.substring(9, linkAway.length()));
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
