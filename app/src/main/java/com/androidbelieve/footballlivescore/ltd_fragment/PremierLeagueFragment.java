package com.androidbelieve.footballlivescore.ltd_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.acitivities.MatchDetailActivity_;
import com.androidbelieve.footballlivescore.interfaces.ItemStickyClick;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.network.apis.AuthApi;
import com.androidbelieve.footballlivescore.network.core.Callback;
import com.androidbelieve.footballlivescore.stickydapterPrimer.BigramHeaderAdapter;
import com.androidbelieve.footballlivescore.stickydapterPrimer.InitialHeaderAdapter;
import com.androidbelieve.footballlivescore.stickydapterPrimer.PrimerAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

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
    private Tracker mTracker;

    @AfterViews
    void afterViews() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

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
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
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

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("LTD-PrimerLeague Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onStop() {
        super.onStop();
        //Stop the analytics tracking
        GoogleAnalytics.getInstance(getActivity()).reportActivityStop(getActivity());
    }
}
