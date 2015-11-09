package com.androidbelieve.drawerwithswipetabs.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.BXH;
import com.androidbelieve.drawerwithswipetabs.util.CheckTeamNameSetLogo;

import java.util.ArrayList;

/**
 * Created by phulx on 24/10/2015.
 */
public class BxhLigue1Adapter extends RecyclerView.Adapter<BxhLigue1Adapter.ViewHolder> {
    private Context mConText;
    private ArrayList<BXH> mDatas = new ArrayList<>();

    public BxhLigue1Adapter(Context context, ArrayList<BXH> datas) {
        mConText = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bxh, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        updateView(holder.tvPosition, position);
        if (mDatas.get(0).getStanding().get(position).getPosition() % 2 == 0) {
            holder.LnParent.setBackgroundColor(mConText.getResources().getColor(R.color.gray));
        } else {
            holder.LnParent.setBackgroundColor(mConText.getResources().getColor(R.color.white));
        }
        holder.tvPosition.setText(String.valueOf(mDatas.get(0).getStanding().get(position).getPosition()));
        holder.tvTeamName.setText(mDatas.get(0).getStanding().get(position).getTeamName());
        holder.tvPlayedGames.setText(String.valueOf(mDatas.get(0).getStanding().get(position).getPlayedGames()));
        CheckTeamNameSetLogo.setLogo(mConText, holder.imgLogo, mDatas.get(0).getStanding().get(position).getTeamName());
        holder.tvGoalDifference.setText(String.valueOf(mDatas.get(0).getStanding().get(position).getGoalDifference()));
        holder.tvPoints.setText(mDatas.get(0).getStanding().get(position).getPoints() + "");
    }

    @Override
    public int getItemCount() {
        if (mDatas.size() != 0)
            return mDatas.get(0).getStanding().size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPosition;
        TextView tvTeamName;
        TextView tvPlayedGames;
        TextView tvGoalDifference;
        TextView tvPoints;
        ImageView imgLogo;
        LinearLayout LnParent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvTeamName = (TextView) itemView.findViewById(R.id.tvTeamName);
            tvPlayedGames = (TextView) itemView.findViewById(R.id.tvPlayedGames);
            tvGoalDifference = (TextView) itemView.findViewById(R.id.tvGoalDifference);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
            imgLogo = (ImageView) itemView.findViewById(R.id.imgLogo);
            LnParent = (LinearLayout) itemView.findViewById(R.id.lnParent);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void updateView(TextView textView, int pos) {
        switch (pos) {
            case 0:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bottom));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 1:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bottom));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 2:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bottom));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 3:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_top));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 4:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_top_five));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 17:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bot));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 18:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bot));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            case 19:
                textView.setBackground(ContextCompat.getDrawable(mConText, R.drawable.bg_bot));
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.white));
                break;
            default:
                textView.setBackground(null);
                textView.setTextColor(ContextCompat.getColor(mConText, R.color.black));
        }
    }
}
