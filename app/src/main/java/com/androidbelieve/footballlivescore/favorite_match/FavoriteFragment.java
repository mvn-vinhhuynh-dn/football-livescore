package com.androidbelieve.footballlivescore.favorite_match;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.database.FavoriteMatch;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/26/15.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnAlarmNotification {
    @ViewById(R.id.recycle_favorite)
    RecyclerView mRecycleView;

    @ViewById(R.id.favorite_empty)
    LinearLayout mEmptyLayout;

    private FavoriteAdapter mAdapter;
    private List<FavoriteMatch> matchLists = new ArrayList<>();

    @AfterViews
    void afterViews() {
        configRecycleView();
//        setAdapter();
        getData();
    }

    private void getData() {
        matchLists = FavoriteMatch.listAll(FavoriteMatch.class);
        if (matchLists.size() > 0) {
            mEmptyLayout.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);
            setAdapter();
        }
    }

    private void configRecycleView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setAdapter() {
        mAdapter = new FavoriteAdapter(getActivity(), matchLists, this);
        mRecycleView.setAdapter(mAdapter);
        StickyRecyclerHeadersDecoration headersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecycleView.addItemDecoration(headersDecoration);
    }

    private void changeDataBase(Long id, boolean isOn) {
        if (isOn) {
            //Turn On
            FavoriteMatch favoriteMatch = FavoriteMatch.findById(FavoriteMatch.class, id);
            favoriteMatch.isNotification = 1; // modify the values
            favoriteMatch.save();
        } else {
            //Turn Off
            FavoriteMatch favoriteMatch = FavoriteMatch.findById(FavoriteMatch.class, id);
            favoriteMatch.isNotification = 0; // modify the values
            favoriteMatch.save();
        }
    }

    private void turnOnAlarm(String time, String title, int id) {
        // Set the alarm to start at hh:mm.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 22);

        Intent notifications = new Intent(getActivity().getApplicationContext(), NotificationMessage.class);
        notifications.putExtra("MATCH_TITLE", title);
        //This is alarm manager
        PendingIntent pi = PendingIntent.getBroadcast(getActivity(), id, notifications, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        Log.d("Vvvv", "on id = " + id);
    }

    private void turnOffAlarm(int id) {
        AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), NotificationMessage.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), id, intent, 0);
        if (alarmMgr != null) {
            alarmMgr.cancel(alarmIntent);
            alarmIntent.cancel();
            Log.d("Vvvv", "off id = " + id);
        }
    }

    @Override
    public void onAlarmSetup(int pos) {
        if (matchLists.get(pos).getIsNotification() == 1) {
            matchLists.get(pos).setIsNotification(0);
            changeDataBase(matchLists.get(pos).getId(), false);
            turnOffAlarm(matchLists.get(pos).getId().intValue());
        } else {
            matchLists.get(pos).setIsNotification(1);
            changeDataBase(matchLists.get(pos).getId(), true);
            turnOnAlarm(matchLists.get(pos).getTimeStart(), matchLists.get(pos).getHomeName() + " - " + matchLists.get(pos).getAwayName(), matchLists.get(pos).getId().intValue());
        }
        mAdapter.notifyDataSetChanged();
    }
}
