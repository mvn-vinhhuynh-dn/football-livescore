package com.androidbelieve.footballlivescore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.BXH;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/28/15.
 */
public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.ViewHolder> {
    private Context mConText;
    private ArrayList<BXH> mDatas = new ArrayList<>();

    public HistoryDetailAdapter(Context context) {
        mConText = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_against, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 15;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

