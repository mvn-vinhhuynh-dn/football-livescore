package com.androidbelieve.footballlivescore.ltd_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.acitivities.MatchDetailActivity_;
import com.androidbelieve.footballlivescore.interfaces.ItemStickyClick;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.network.apis.AuthApi;
import com.androidbelieve.footballlivescore.network.core.Callback;
import com.androidbelieve.footballlivescore.stickyadapterBundesliga.BundesligaAdapter;
import com.androidbelieve.footballlivescore.stickydapterPrimer.BigramHeaderAdapter;
import com.androidbelieve.footballlivescore.stickydapterPrimer.InitialHeaderAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit.RetrofitError;

/**
 * Created by phulx on 22/10/2015.
 */
@EFragment(R.layout.ltd_bundesliga_fragment)
public class BundesligaFragment extends Fragment implements ItemStickyClick {
    @ViewById(R.id.recycle_ltd_bundes)
    RecyclerView mRecycleView;

    @ViewById(R.id.dialog_bundes)
    MaterialProgressBar mDialog;

    @ViewById(R.id.ltd_bundes_empty)
    LinearLayout mEmptyLayout;

    private BundesligaAdapter mAdapter;
    private ArrayList<LTD> mDatas = new ArrayList<>();
    private StickyHeadersItemDecoration top;
    private StickyHeadersItemDecoration overlay;

    @AfterViews
    void afterViews() {
        initView();
        setAdapter();
        getLTD();
    }

    private void setAdapter() {
        mAdapter = new BundesligaAdapter(getActivity(), mDatas, this);
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

    private void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
        mEmptyLayout.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);
    }

    private void getLTD() {
        mDialog.setVisibility(View.VISIBLE);
        AuthApi.getLtdBundesliga(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                mDatas.add(ltd);
                mDialog.setVisibility(View.GONE);
                mEmptyLayout.setVisibility(View.GONE);
                mRecycleView.setVisibility(View.VISIBLE);
                mRecycleView.setAdapter(mAdapter);
                mRecycleView.addItemDecoration(overlay);
                mRecycleView.addItemDecoration(top);
                mRecycleView.getLayoutManager().scrollToPosition(getPos(mDatas));
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                mDialog.setVisibility(View.GONE);
            }
        });
    }

    public void gotoNextMatch() {
        if (mDatas != null && mDatas.size() > 0) {
            mRecycleView.getLayoutManager().scrollToPosition(getPos(mDatas));
        }
    }

    private int getPos(ArrayList<LTD> data) {
        int pos = 0;
        if (data != null && data.get(0).getFixtures().size() > 0) {
            for (int i = 0; i < data.get(0).getFixtures().size(); i++) {
                if (data.get(0).getFixtures().get(i).getStatus().equals("TIMED")) {
                    Log.d("vvvv", "pos is: " + i);
                    return i;
                }
            }
        }
        return pos;
    }

    @Override
    public void onItemStickyClick(String homeName, String difName, String url) {
        MatchDetailActivity_.intent(getActivity())
                .extra("URL_HISTORY", url)
                .extra("HOME_NAME", homeName)
                .extra("DIF_NAME", difName)
                .start();
    }
}