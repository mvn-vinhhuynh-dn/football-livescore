package com.androidbelieve.footballlivescore.bxh_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Ratan on 7/27/2015.
 */
@EFragment(R.layout.bxh_tab_layout)
public class BXHTabFragment extends BaseFragment {
    @ViewById(R.id.tabsBXH)
    TabLayout tabLayout;
    @ViewById(R.id.viewpagerBXH)
    ViewPager viewPager;

    @AfterViews
    void afterView() {

        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new BXHPremierLeagueFragment_(), "PrimerLeague");
        adapter.addFragment(new BXHLaligaFragment_(), "Laliga");
        adapter.addFragment(new BXHBundesligaFragment_(), "Bundesliga");
        adapter.addFragment(new BXHSeriaFragment_(), "Seria");
        adapter.addFragment(new BXHLigue1Fragment_(), "Ligue1");
        viewPager.setAdapter(adapter);
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
}
