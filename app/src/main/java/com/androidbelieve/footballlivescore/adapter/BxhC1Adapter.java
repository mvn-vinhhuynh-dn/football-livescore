package com.androidbelieve.footballlivescore.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.GroupC1;
import com.androidbelieve.footballlivescore.util.CheckLogoC1;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

/**
 * Created by phulx on 04/11/2015.
 */
public class BxhC1Adapter extends RecyclerView.Adapter<BxhC1Adapter.ViewHolder> implements StickyRecyclerHeadersAdapter<BxhC1Adapter.ViewHolderHeader>{
    private Context mContext;
    private ArrayList<GroupC1> mArraylist = new ArrayList<>();
    private ArrayList<String> mHeaders = new ArrayList<>();
    int a=3,b=4;

    public BxhC1Adapter(Context mContext, ArrayList<GroupC1> mArraylist, ArrayList<String> mHeaders) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
        this.mHeaders = mHeaders;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bxh,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        updateView(holder.tvPosition, position);
        holder.tvPosition.setText(mArraylist.get(position).getPosition());
        holder.tvTeamName.setText(mArraylist.get(position).getTeamName());
        holder.tvPlayedGames.setText(mArraylist.get(position).getPlayedGames());
        CheckLogoC1.setLogo(mContext, holder.imgLogo, mArraylist.get(position).getTeamName());
        holder.tvGoalDifference.setText(mArraylist.get(position).getGoals());
        holder.tvPoints.setText(mArraylist.get(position).getPoint());
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    //header
    @Override
    public long getHeaderId(int position) {
        Log.d("----pos: ",(long) position / 4+"");
        return (long) position / 4;
    }

    @Override
    public ViewHolderHeader onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ltd,parent,false);
        return new ViewHolderHeader(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolderHeader holder, int position) {


        holder.tvGroup.setText(mHeaders.get(position / 4));
    }

   public class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvPosition;
       TextView tvTeamName;
       TextView tvPlayedGames;
       TextView tvGoalDifference;
       TextView tvPoints;
       ImageView imgLogo;
        public ViewHolder(View itemView) {
            super(itemView);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvTeamName = (TextView) itemView.findViewById(R.id.tvTeamName);
            tvPlayedGames = (TextView) itemView.findViewById(R.id.tvPlayedGames);
            tvGoalDifference = (TextView) itemView.findViewById(R.id.tvGoalDifference);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
            imgLogo = (ImageView) itemView.findViewById(R.id.imgLogo);
        }
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        TextView tvGroup;
        public ViewHolderHeader(View itemView) {
            super(itemView);
            tvGroup = (TextView)itemView.findViewById(R.id.tv_time_ltd);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void updateView(TextView tvPosition, int position) {
        switch (position) {
            case 0:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 1:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 2:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 3:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 4:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 5:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 6:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 7:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 8:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 9:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 10:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 11:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 12:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 13:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 14:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 15:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 16:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 17:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 18:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 19:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 20:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 21:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 22:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 23:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 24:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 25:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 26:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 27:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            case 28:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 29:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bottom));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 30:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_top_five));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case 31:
                tvPosition.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_bot));
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;

            default:
                tvPosition.setBackground(null);
                tvPosition.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
    }
}
