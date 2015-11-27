package com.androidbelieve.footballlivescore.favorite_match;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.database.FavoriteMatch;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/26/15.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends Fragment {
    @ViewById(R.id.recycle_favorite)
    RecyclerView mRecycleView;

    @ViewById(R.id.favorite_empty)
    LinearLayout mEmptyLayout;

    private FavoriteAdapter mAdapter;
    private List<FavoriteMatch> matchLists = new ArrayList<>();

    @AfterViews
    void afterViews() {
        configRecycleView();
//        setAdapter();
        getData();
    }

    private void getData() {
        matchLists = FavoriteMatch.listAll(FavoriteMatch.class);
        if (matchLists.size() > 0) {
            mEmptyLayout.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);
            setAdapter();
        }
    }

    private void configRecycleView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setAdapter() {
        mAdapter = new FavoriteAdapter(getActivity(), matchLists);
        mRecycleView.setAdapter(mAdapter);
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleView.addItemDecoration(headersDecoration);
    }

    public void notifyDatSetChange() {
        mAdapter.notifyDataSetChanged();
    }
}
