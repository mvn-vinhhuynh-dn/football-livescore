package com.androidbelieve.footballlivescore.ltdfootballclub;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.LtdFootballClub;
import com.androidbelieve.footballlivescore.util.DividerItemDecoration;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/5/15.
 */
@EFragment(R.layout.fragment_playing)
public class PlayingFragment extends Fragment {
    private SchedulePlayingAdapter mAdapter;
    private Tracker mTracker;
    @ViewById(R.id.recycle_view_playing)
    RecyclerView mRecycleView;

    @AfterViews
    void afterViews() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        initView();
        setAdapter();
    }

    private void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
    }

    private ArrayList<LtdFootballClub> mDatasPlaying = new ArrayList<>();

    public void setAdapter() {
        mAdapter = new SchedulePlayingAdapter(getActivity(), mDatasPlaying);
        mRecycleView.setAdapter(mAdapter);
    }

    public void notifyDataSetChange(ArrayList<LtdFootballClub> mDatas) {
        mDatasPlaying.addAll(mDatas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("LTD-Playing-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
