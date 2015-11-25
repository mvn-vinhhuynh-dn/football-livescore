package com.androidbelieve.footballlivescore.ChampionLeague;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.network.apis.AuthApi;
import com.androidbelieve.footballlivescore.network.core.Callback;
import com.androidbelieve.footballlivescore.stickydapterPrimer.BigramHeaderAdapter;
import com.androidbelieve.footballlivescore.stickydapterPrimer.InitialHeaderAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit.RetrofitError;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 03/11/2015.
 */
@EFragment(R.layout.ltd_c1_fragment)
public class LtdC1Fragment extends BaseFragment {
    @ViewById(R.id.recycle_ltd_c1)
    RecyclerView mRecycleView;
    @ViewById(R.id.dialog_c1)
    MaterialProgressBar mMaterialProgressBar;

    private LinearLayoutManager mLinearLayoutManager;
    private StickyHeadersItemDecoration top;
    private StickyHeadersItemDecoration overlay;
    private C1Adapter mAdapter;
    private ArrayList<LTD> mDatas = new ArrayList<>();
    private Tracker mTracker;

    @AfterViews
    void afterView() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        initView();
        setAdapter();
        getLTD();
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity()
                .getBaseContext());
        mRecycleView.setLayoutManager(mLinearLayoutManager);
    }

    private void setAdapter() {
        mAdapter = new C1Adapter(getActivity(), mDatas);
        top = new StickyHeadersBuilder()
                .setAdapter(mAdapter)
                .setRecyclerView(mRecycleView)
                .setStickyHeadersAdapter(new BigramHeaderAdapter(mDatas))
                .build();

        overlay = new StickyHeadersBuilder()
                .setAdapter(mAdapter)
                .setRecyclerView(mRecycleView)
                .setStickyHeadersAdapter(new InitialHeaderAdapter(mDatas), true)
                .build();
    }

    private void getLTD() {
        mMaterialProgressBar.setVisibility(View.VISIBLE);
        AuthApi.getLtdC1(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                mDatas.add(ltd);
                mMaterialProgressBar.setVisibility(View.GONE);
                mRecycleView.setAdapter(mAdapter);
                mRecycleView.addItemDecoration(overlay);
                mRecycleView.addItemDecoration(top);
                mRecycleView.getLayoutManager().scrollToPosition(getPos(mDatas));
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                mMaterialProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private int getPos(ArrayList<LTD> data) {
        int pos = 0;
        if (data != null && data.get(0).getFixtures().size() > 0) {
            for (int i = 0; i < data.get(0).getFixtures().size(); i++) {
                if (data.get(0).getFixtures().get(i).getStatus().equals("TIMED")) {
                    return i;
                }
            }
        }
        return pos;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("LTD-C1-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
