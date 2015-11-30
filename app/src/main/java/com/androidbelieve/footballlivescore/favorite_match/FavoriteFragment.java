package com.androidbelieve.footballlivescore.favorite_match;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.database.FavoriteMatch;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/26/15.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnAlarmNotification {
    public static final String MATCH_TITLE = "MATCH_TITLE";
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

    private void turnOnAlarm(String time, String title, int id) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, Integer.parseInt(getCurentTime(time).substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(getCurentTime(time).substring(5, 7)));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getCurentTime(time).substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(getCurentTime(time).substring(11, 13)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(getCurentTime(time).substring(14, 16)));

        Intent notifications = new Intent(getActivity().getApplicationContext(), NotificationMessage.class);
        notifications.putExtra(MATCH_TITLE, title);
        //This is alarm manager
        PendingIntent pi = PendingIntent.getBroadcast(getActivity(), id, notifications, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void turnOffAlarm(int id) {
        AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), NotificationMessage.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), id, intent, 0);
        if (alarmMgr != null) {
            alarmMgr.cancel(alarmIntent);
            alarmIntent.cancel();
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
            try {
                turnOnAlarm(matchLists.get(pos).getTimeStart(), matchLists.get(pos).getHomeName() + " - " + matchLists.get(pos).getAwayName(), matchLists.get(pos).getId().intValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public String getConvertDate(String adate) {
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = form.parse(adate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("yyyy:MM:dd:HH:mm", Locale.getDefault());
        String newDateStr = postFormater.format(date);
        return newDateStr;
    }

    public String getCurentTime(String time) throws ParseException {
        // From TimeZone Etc/UTC
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        TimeZone tz = TimeZone.getTimeZone("Etc/UTC");
        formatter.setTimeZone(tz);
        Date date = formatter.parse(time);


        // To TimeZone America/New_York
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        sdfAmerica.setTimeZone(tzInAmerica);

        String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
        return sDateInAmerica;
    }
}
