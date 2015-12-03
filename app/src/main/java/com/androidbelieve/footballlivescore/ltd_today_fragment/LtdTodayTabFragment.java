package com.androidbelieve.footballlivescore.ltd_today_fragment;

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
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 12/3/15.
 */
@EFragment(R.layout.ltd_today_tab_layout)
public class LtdTodayTabFragment extends BaseFragment{
    @ViewById(R.id.tabsToday)
    TabLayout tabLayout;
    @ViewById(R.id.viewpagerToday)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    @AfterViews
    void afterView(){
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(new Ltd_today_fragment_(), "Sắp Diễn Ra");
        mAdapter.addFragment(new Kq_today_fragment_(), "Kết Quả");
        viewPager.setAdapter(mAdapter);
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
