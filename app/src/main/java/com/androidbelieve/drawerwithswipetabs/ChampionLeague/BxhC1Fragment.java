package com.androidbelieve.drawerwithswipetabs.ChampionLeague;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.abstracts.BaseFragment;
import com.androidbelieve.drawerwithswipetabs.adapter.BxhC1Adapter;
import com.androidbelieve.drawerwithswipetabs.models.GroupC1;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by phulx on 03/11/2015.
 */
@EFragment(R.layout.bxh_c1_fragment)
public class BxhC1Fragment extends BaseFragment {
    private ArrayList<GroupC1> mArraylists;
    private ArrayList<String> mHeaders;
    private BxhC1Adapter mAdapter;
    @ViewById(R.id.recycleBXHC1)
    RecyclerView mRecycleview;
    @ViewById(R.id.progress_dialog_c1)
    MaterialProgressBar mProgressBar;

    @AfterViews
    void afterView() {
        mProgressBar.setVisibility(View.VISIBLE);
        mArraylists = new ArrayList<>();
        mHeaders = new ArrayList<>();
        mRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
        loadData();
    }

    @Background
    public void loadData() {
        Document document = null;
        try {
            document = Jsoup.connect("http://bongdaplus.vn/chuyen-muc/36/champions-league.bdplus#menu")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document != null) {
            getdata(document);
            setUiApplication();
        }
    }

    @UiThread
    public void setUiApplication() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mAdapter = new BxhC1Adapter(getActivity(),mArraylists,mHeaders);
        mRecycleview.setAdapter(mAdapter);

        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleview.addItemDecoration(headersDecoration);
    }

    private void getdata(Document document) {
        Element element = document.select("div.stnscrl").first();
        Element tbody = element.select("tbody").first();
        Elements tr = tbody.select("tr");

        for (int i = 1; i < tr.size(); i++) {
            if (i != 1 && i != 6 && i != 11 && i != 16 && i != 21 && i != 26 && i != 31 && i != 36) {
                GroupC1 groupC1 = new GroupC1();
                Element group = tr.get(i).select("tr").first();
                Element position = group.select("td.idx").first();
                Element teamName = group.select("td.team").first();
                Elements sub = group.select("td.sub");
                Element playedGames = sub.get(0).select("td").first();
                Element goals = sub.get(1).select("td").first();
                Element point = group.select("td.pnt").first();

                groupC1.setPosition(position.text());
                groupC1.setTeamName(teamName.text());
                groupC1.setPlayedGames(playedGames.text());
                groupC1.setGoals(goals.text());
                groupC1.setPoint(point.text());

                mArraylists.add(groupC1);
            } else {
                Element group = tr.get(i).select("tr.grp").first();
                mHeaders.add(group.text());
            }
        }

    }

}
