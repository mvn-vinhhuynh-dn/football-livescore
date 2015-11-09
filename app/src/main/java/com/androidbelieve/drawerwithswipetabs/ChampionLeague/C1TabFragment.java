package com.androidbelieve.drawerwithswipetabs.ChampionLeague;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.abstracts.BaseFragment;
import com.androidbelieve.drawerwithswipetabs.ltd_fragment.BundesligaFragment_;
import com.androidbelieve.drawerwithswipetabs.ltd_fragment.LaligaFragment_;
import com.androidbelieve.drawerwithswipetabs.ltd_fragment.Ligue1Fragment_;
import com.androidbelieve.drawerwithswipetabs.ltd_fragment.PremierLeagueFragment_;
import com.androidbelieve.drawerwithswipetabs.ltd_fragment.SeriaFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 *
 * Created by phulx on 03/11/2015.
 */
@EFragment(R.layout.c1_tab_fragment)
public class C1TabFragment extends BaseFragment {
    @ViewById(R.id.tabsC1)
    TabLayout tabLayout;
    @ViewById(R.id.viewpagerC1)
    ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @AfterViews
    void afterViews(){
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(1);
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(new LtdC1Fragment_(), "Lịch Thi Đấu");
        mAdapter.addFragment(new BxhC1Fragment_(), "Bảng Xếp Hạng");
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
