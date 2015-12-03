package com.androidbelieve.footballlivescore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.ChampionLeague.C1TabFragment;
import com.androidbelieve.footballlivescore.ChampionLeague.C1TabFragment_;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.bxh_fragment.BXHTabFragment;
import com.androidbelieve.footballlivescore.bxh_fragment.BXHTabFragment_;
import com.androidbelieve.footballlivescore.favorite_match.FavoriteFragment;
import com.androidbelieve.footballlivescore.favorite_match.FavoriteFragment_;
import com.androidbelieve.footballlivescore.ltd_fragment.LTDTabFragment;
import com.androidbelieve.footballlivescore.ltd_fragment.LTDTabFragment_;
import com.androidbelieve.footballlivescore.ltd_today_fragment.LtdTodayTabFragment;
import com.androidbelieve.footballlivescore.ltd_today_fragment.LtdTodayTabFragment_;
import com.androidbelieve.footballlivescore.news_fragment.NewsTabsFragment;
import com.androidbelieve.footballlivescore.news_fragment.NewsTabsFragment_;
import com.androidbelieve.footballlivescore.troll_fragment.TrollFragment;
import com.androidbelieve.footballlivescore.troll_fragment.TrollFragment_;
import com.androidbelieve.footballlivescore.util.CheckConnection;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements BaseFragment.OnBaseFragmentListener, View.OnTouchListener {
    private static final String TAG = "MainActivity";
    @ViewById(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.shitstuff)
    NavigationView mNavigationView;

    @ViewById(R.id.toolbar)
    Toolbar mToolBar;

    @ViewById(R.id.lldrawer)
    LinearLayout mLldrawer;

    private Fragment mContent;
    @ViewById(R.id.imgMove)
    ImageView mImgBall;
    private float mDy;
    private float mDx;
    private boolean isMoveImgBall = false;
    private CheckConnection mCheckConnection;
    private String mToDate;

    @AfterViews
    void afterViews() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        mToDate = df.format(c.getTime());


        mCheckConnection = new CheckConnection(this);
        if (mCheckConnection.hasConnection()) {
            intViews();
            clickMenu();
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.app_name,
                    R.string.app_name);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        } else {
            showAlertDialog(this, "No Conection");
        }

//        setDrawerLayoutMargin();
    }

    //    private void setDrawerLayoutMargin() {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        if (android.os.Build.VERSION.SDK_INT < 21) {
//            layoutParams.setMargins(0, 0, 0, 0);
//        } else {
//            layoutParams.setMargins(0, -100, 0, 0);
//        }
//        mNavigationView.setLayoutParams(layoutParams);
//
//    }

    private void intViews() {
        LtdTodayTabFragment ltdTodayTabFragment = new LtdTodayTabFragment_();
        changeFragment(ltdTodayTabFragment, true);
        mToolBar.setTitle("Lịch Thi Đấu Hôm Nay");
        mImgBall.setOnTouchListener(this);
    }

    private void clickMenu() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_ltdToday) {
                    LtdTodayTabFragment ltdTodayTabFragment = new LtdTodayTabFragment_();
                    changeFragment(ltdTodayTabFragment, true);
                    mToolBar.setTitle("Lịch Thi Đấu Hôm Nay");
                }

                if (menuItem.getItemId() == R.id.nav_item_ltd) {
                    LTDTabFragment tabFragment = new LTDTabFragment_();
                    changeFragment(tabFragment, true);
                    mToolBar.setTitle("Lịch Thi Đấu");
                }

                if (menuItem.getItemId() == R.id.nav_item_bxh) {
                    BXHTabFragment kqTabFragment = new BXHTabFragment_();
                    changeFragment(kqTabFragment, true);
                    mToolBar.setTitle("Bảng Xếp Hạng");
                }

                if (menuItem.getItemId() == R.id.nav_item_news) {
                    NewsTabsFragment newsTabsFragment = new NewsTabsFragment_();
                    changeFragment(newsTabsFragment, true);
                    mToolBar.setTitle("Tin Tức Bóng Đá");
                }

                if (menuItem.getItemId() == R.id.nav_item_c1) {
                    C1TabFragment c1TabFragment = new C1TabFragment_();
                    changeFragment(c1TabFragment, true);
                    mToolBar.setTitle("Champion League / C1");
                }

                if (menuItem.getItemId() == R.id.nav_item_troll) {
                    TrollFragment trollFragment = new TrollFragment_();
                    changeFragment(trollFragment, true);
                    mToolBar.setTitle("Troll Bóng Đá");
                }
                if (menuItem.getItemId() == R.id.nav_item_favorite) {
//                    PaintActivity_.intent(MainActivity.this).start();
                    FavoriteFragment favoriteFragment = new FavoriteFragment_();
                    changeFragment(favoriteFragment, true);
                    mToolBar.setTitle("Favorite Match");
                }
                return false;
            }

        });
    }

    // Change fragment with animation
    public void changeFragment(Fragment fragment, boolean isBack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.containerView, fragment);
        mContent = fragment;
        //Add to back stack
        if (!isBack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDx = view.getX() - event.getRawX();
                mDy = view.getY() - event.getRawY();
                isMoveImgBall = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isMoveImgBall = true;
                view.animate()
                        .x(event.getRawX() + mDx)
                        .y(event.getRawY() + mDy)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                if (!isMoveImgBall) {
                    Fragment myFragment = getSupportFragmentManager().findFragmentById(R.id.containerView);
                    if (myFragment instanceof LTDTabFragment_) {
                        ((LTDTabFragment) myFragment).getCurrentPage();
                    } else if (myFragment instanceof BXHTabFragment_) {
                    }
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public void showAlertDialog(Context context, String title) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                afterViews();
            }
        });
        alertDialog.show();
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment curentFragment = getVisibleFragment();
        if (curentFragment instanceof TrollFragment_) {
            ((TrollFragment) curentFragment).onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

