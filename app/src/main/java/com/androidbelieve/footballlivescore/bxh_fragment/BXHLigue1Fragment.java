package com.androidbelieve.footballlivescore.bxh_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.adapter.BxhLigue1Adapter;
import com.androidbelieve.footballlivescore.footballclubinfor.FootBallClubInformationActivity_;
import com.androidbelieve.footballlivescore.models.BXH;
import com.androidbelieve.footballlivescore.network.apis.AuthApi;
import com.androidbelieve.footballlivescore.network.core.Callback;
import com.androidbelieve.footballlivescore.util.CheckTeamNameSetLogo;
import com.androidbelieve.footballlivescore.util.RecyclerItemClickListener;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit.RetrofitError;

/**
 * Created by phulx on 22/10/2015.
 */
@EFragment(R.layout.bxh_legue1_fragment)
public class BXHLigue1Fragment extends Fragment {
    @ViewById(R.id.recycleBXHLigue1)
    RecyclerView mRecycleBXHLigue1;
    @ViewById(R.id.progress_dialog_one)
    MaterialProgressBar mProgressBar;
    private BxhLigue1Adapter mAdapter;
    private ArrayList<BXH> mDatas = new ArrayList<>();
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private Tracker mTracker;
    @AfterViews
    void afterViews() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

        initView();
        setAdapter();
        getLigue1Rank();
    }

    private void getLigue1Rank() {
        mProgressBar.setVisibility(View.VISIBLE);
        AuthApi.getBxhLigue1(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                mDatas.add(bxh);
                mScaleInAnimationAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        mRecycleBXHLigue1.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    private void setAdapter() {
        mAdapter = new BxhLigue1Adapter(getActivity(), mDatas);
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mScaleInAnimationAdapter.setDuration(500);
        mScaleInAnimationAdapter.setFirstOnly(false);
        mRecycleBXHLigue1.setAdapter(mScaleInAnimationAdapter);

        mRecycleBXHLigue1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleBXHLigue1, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FootBallClubInformationActivity_.intent(getActivity())
                        .extra("NAME_FC", CheckTeamNameSetLogo.getUrl(getActivity(), mDatas.get(0).getStanding().get(position).getTeamName()))
                        .extra("NAME", mDatas.get(0).getStanding().get(position).getTeamName())
                        .start();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("BXH-LeagueOne-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
