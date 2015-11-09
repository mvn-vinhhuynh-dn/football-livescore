package com.androidbelieve.drawerwithswipetabs.footballclubinfor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.FCInformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/4/15.
 */
public class FCInformationAdapter extends RecyclerView.Adapter<FCInformationAdapter.ViewHolder> {
    private ArrayList<FCInformation> mDatas = new ArrayList<>();
    private Context mContext;
    private ShowDetailsPlayer mListener;

    public FCInformationAdapter(Context context, ArrayList<FCInformation> datas, ShowDetailsPlayer showDetailsPlayer) {
        mContext = context;
        mListener = showDetailsPlayer;
        mDatas = datas;
    }

    @Override
    public FCInformationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fc_infor, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(FCInformationAdapter.ViewHolder holder, final int position) {
        holder.tvCountry.setText(mDatas.get(position).getCountry());
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvPos.setText(mDatas.get(position).getPosition());
        if (mDatas.get(position).getAvatarUrl() != null && !mDatas.get(position).getAvatarUrl().equals(""))
            Picasso.with(mContext).load(mDatas.get(position).getAvatarUrl()).into(holder.imgAvata);

        holder.lnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onShowDetails(mDatas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvata;
        TextView tvName;
        TextView tvCountry;
        TextView tvPos;
        LinearLayout lnParent;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvata = (ImageView) itemView.findViewById(R.id.imgAva_item);
            tvName = (TextView) itemView.findViewById(R.id.tvName_item);
            tvCountry = (TextView) itemView.findViewById(R.id.countr_item);
            tvPos = (TextView) itemView.findViewById(R.id.pos_item);
            lnParent = (LinearLayout) itemView.findViewById(R.id.lnParent_teamInfo);
        }
    }

    public interface ShowDetailsPlayer {
        void onShowDetails(FCInformation myData);
    }
}
