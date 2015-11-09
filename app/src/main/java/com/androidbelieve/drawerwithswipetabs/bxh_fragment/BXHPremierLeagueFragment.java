package com.androidbelieve.drawerwithswipetabs.bxh_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.adapter.BXHPrimerLeagueAdapter;
import com.androidbelieve.drawerwithswipetabs.footballclubinfor.FootBallClubInformationActivity_;
import com.androidbelieve.drawerwithswipetabs.models.BXH;
import com.androidbelieve.drawerwithswipetabs.network.apis.AuthApi;
import com.androidbelieve.drawerwithswipetabs.network.core.Callback;
import com.androidbelieve.drawerwithswipetabs.util.CheckTeamNameSetLogo;
import com.androidbelieve.drawerwithswipetabs.util.RecyclerItemClickListener;

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
@EFragment(R.layout.bxh_primerleague_fragment)
public class BXHPremierLeagueFragment extends Fragment {
    @ViewById(R.id.recycleBXHPrimerLeague)
    RecyclerView mRecycleBXHPrimerLeague;
    @ViewById(R.id.progress_dialog_primer)
    MaterialProgressBar mProgressBar;
    private BXHPrimerLeagueAdapter mAdapter;
    private ArrayList<BXH> mDatas = new ArrayList<>();
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;

    @AfterViews
    void afterViews() {
        initView();
        setAdapter();
        getPrimerLeagueRank();
    }

    private void getPrimerLeagueRank() {
        mProgressBar.setVisibility(View.VISIBLE);
        AuthApi.getBxhPrimerLeague(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                mDatas.add(bxh);
                mScaleInAnimationAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.drawerwithswipetabs.network.Error myError) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        mRecycleBXHPrimerLeague.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
    }

    private void setAdapter() {
        mAdapter = new BXHPrimerLeagueAdapter(getActivity(), mDatas);
        // Add animation
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mScaleInAnimationAdapter.setDuration(500);
        mScaleInAnimationAdapter.setFirstOnly(false);
        mRecycleBXHPrimerLeague.setAdapter(mScaleInAnimationAdapter);

        mRecycleBXHPrimerLeague.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleBXHPrimerLeague, new RecyclerItemClickListener.OnItemClickListener() {
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
}
