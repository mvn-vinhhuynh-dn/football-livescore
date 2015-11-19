package com.androidbelieve.footballlivescore.ltd_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.views.ABaseTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Ratan on 7/27/2015.
 */
@EFragment(R.layout.ltd_tab_layout)
public class LTDTabFragment extends BaseFragment {
    @ViewById(R.id.tabs)
    TabLayout tabLayout;
    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    @AfterViews
    void afterView() {
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(new PremierLeagueFragment_(), "PrimerLeague");
        mAdapter.addFragment(new LaligaFragment_(), "Laliga");
        mAdapter.addFragment(new BundesligaFragment_(), "Bundesliga");
        mAdapter.addFragment(new SeriaFragment_(), "Seria");
        mAdapter.addFragment(new Ligue1Fragment_(), "Ligue1");
        viewPager.setAdapter(mAdapter);
    }

    public void getCurrentPage() {
        Fragment fragment;
        fragment = mAdapter.getItem(mViewPager.getCurrentItem());
        if (fragment != null && fragment instanceof PremierLeagueFragment_) {
            ((PremierLeagueFragment) fragment).gotoNextMatch();
        } else if (fragment != null && fragment instanceof SeriaFragment) {
            ((SeriaFragment) fragment).gotoNextMatch();
        } else if (fragment != null && fragment instanceof BundesligaFragment) {
            ((BundesligaFragment) fragment).gotoNextMatch();
        }
        if (fragment != null && fragment instanceof LaligaFragment) {
            ((LaligaFragment) fragment).gotoNextMatch();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public class ZoomOutSlideTransformer extends ABaseTransformer {

        @Override
        protected void onTransform(View view, float position) {
            view.setPivotX(position < 0 ? 0 : view.getWidth());
            view.setPivotY(view.getHeight() / 2f);
            float scale = position < 0 ? 1f + position : 1f - position;
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
    }
}
