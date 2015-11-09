package com.androidbelieve.drawerwithswipetabs.ChampionLeague;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.abstracts.BaseFragment;
import com.androidbelieve.drawerwithswipetabs.models.LTD;
import com.androidbelieve.drawerwithswipetabs.network.apis.AuthApi;
import com.androidbelieve.drawerwithswipetabs.network.core.Callback;
import com.androidbelieve.drawerwithswipetabs.stickydapterPrimer.BigramHeaderAdapter;
import com.androidbelieve.drawerwithswipetabs.stickydapterPrimer.InitialHeaderAdapter;
import com.androidbelieve.drawerwithswipetabs.stickydapterPrimer.PrimerAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit.RetrofitError;

/**
 * Created by phulx on 03/11/2015.
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

    @AfterViews
    void afterView(){
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
            public void failure(RetrofitError error, com.androidbelieve.drawerwithswipetabs.network.Error myError) {
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
}
