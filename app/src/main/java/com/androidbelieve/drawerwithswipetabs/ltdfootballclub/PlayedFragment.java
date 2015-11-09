package com.androidbelieve.drawerwithswipetabs.ltdfootballclub;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.LtdFootballClub;
import com.androidbelieve.drawerwithswipetabs.util.DividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/5/15.
 */
@EFragment(R.layout.fragment_played)
public class PlayedFragment extends Fragment {
    private SchedulePlayedAdapter mAdapter;

    @ViewById(R.id.recycle_view_played)
    RecyclerView mRecycleView;

    @AfterViews
    void afterViews() {
        initView();
        setAdapter();
    }

    private void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
    }

    private ArrayList<LtdFootballClub> mDatasPlayed = new ArrayList<>();

    public void setAdapter() {
        mAdapter = new SchedulePlayedAdapter(getActivity(), mDatasPlayed);
        mRecycleView.setAdapter(mAdapter);
    }

    public void notifyDataSetChange(ArrayList<LtdFootballClub> mDatas) {
        mDatasPlayed.addAll(mDatas);
        mAdapter.notifyDataSetChanged();
    }
}
