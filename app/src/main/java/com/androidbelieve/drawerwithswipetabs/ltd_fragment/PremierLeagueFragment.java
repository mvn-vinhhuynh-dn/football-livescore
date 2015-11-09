package com.androidbelieve.drawerwithswipetabs.ltd_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.acitivities.MatchDetailActivity_;
import com.androidbelieve.drawerwithswipetabs.interfaces.ItemStickyClick;
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
 * Created by phulx on 22/10/2015.
 */
@EFragment(R.layout.ltd_primerleague_fragment)
public class PremierLeagueFragment extends Fragment implements ItemStickyClick {
    private ArrayList<LTD> mDatas = new ArrayList<>();

    private StickyHeadersItemDecoration top;
    private StickyHeadersItemDecoration overlay;
    private PrimerAdapter personAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    @ViewById(R.id.recycle_ltd_pri)
    RecyclerView mRecycleView;

    @ViewById(R.id.dialog_pri)
    MaterialProgressBar mMaterialProgressBar;

    @ViewById(R.id.ltd_pri_empty)
    LinearLayout mEmptyLayout;

    @AfterViews
    void afterViews() {
        initView();
        setAdapter();
        getLTD();
    }

    private void getLTD() {
        mMaterialProgressBar.setVisibility(View.VISIBLE);
        AuthApi.getLtdPrimerLueage(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                mDatas.add(ltd);
                mMaterialProgressBar.setVisibility(View.GONE);
                mEmptyLayout.setVisibility(View.GONE);
                mRecycleView.setVisibility(View.VISIBLE);
                mRecycleView.setAdapter(personAdapter);
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

    private void setAdapter() {
        personAdapter = new PrimerAdapter(getActivity(), mDatas, this);
        top = new StickyHeadersBuilder()
                .setAdapter(personAdapter)
                .setRecyclerView(mRecycleView)
                .setStickyHeadersAdapter(new BigramHeaderAdapter(mDatas))
                .build();

        overlay = new StickyHeadersBuilder()
                .setAdapter(personAdapter)
                .setRecyclerView(mRecycleView)
                .setStickyHeadersAdapter(new InitialHeaderAdapter(mDatas), true)
                .build();
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity()
                .getBaseContext());
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        mEmptyLayout.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);
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

    public void gotoNextMatch() {
        if (mDatas != null && mDatas.size() > 0) {
            Log.d("vvvv", "pos is: " + getPos(mDatas) + "--name is" + mDatas.get(0).getFixtures().get(getPos(mDatas)).getHomeTeamName());
            mRecycleView.getLayoutManager().scrollToPosition(getPos(mDatas));
        }
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
