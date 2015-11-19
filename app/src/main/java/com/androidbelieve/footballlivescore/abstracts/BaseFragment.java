package com.androidbelieve.footballlivescore.abstracts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androidbelieve.footballlivescore.MainActivity_;


/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/6/15.
 */
public abstract class BaseFragment extends Fragment {
    protected OnBaseFragmentListener mOnBaseFragmentListener;
    protected Context mContext;
    //
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnBaseFragmentListener = (OnBaseFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBaseFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    protected void replaceFragment(Fragment fragment, boolean isBack) {
        if (getActivity() != null) {
            ((MainActivity_) getActivity()).changeFragment(fragment, isBack);
        }
    }

    public interface OnBaseFragmentListener {

    }
}