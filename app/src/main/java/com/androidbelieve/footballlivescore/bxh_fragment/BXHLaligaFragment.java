package com.androidbelieve.footballlivescore.bxh_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.adapter.BxhLaligaAdapter;
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
@EFragment(R.layout.bxh_liga_fragment)
public class BXHLaligaFragment extends BaseFragment {
    @ViewById(R.id.recycleBXHLaliga)
    RecyclerView mRecycleBXHLaliga;
    @ViewById(R.id.progress_dialog_liga)
    MaterialProgressBar mProgressBar;
    private BxhLaligaAdapter mAdapter;
    private ArrayList<BXH> mDatas = new ArrayList<>();
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private Tracker mTracker;

    @AfterViews
    void afterViews() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        initView();
        setAdapter();
        getLaligaRank();
    }

    private void getLaligaRank() {
        mProgressBar.setVisibility(View.VISIBLE);
        AuthApi.getBxhLaliga(new Callback<BXH>() {
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
        mRecycleBXHLaliga.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    private void setAdapter() {
        mAdapter = new BxhLaligaAdapter(getActivity(), mDatas);
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mScaleInAnimationAdapter.setDuration(500);
        mScaleInAnimationAdapter.setFirstOnly(false);
        mRecycleBXHLaliga.setAdapter(mScaleInAnimationAdapter);

        mRecycleBXHLaliga.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleBXHLaliga, new RecyclerItemClickListener.OnItemClickListener() {
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
        mTracker.setScreenName("BXH-Laliga-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
