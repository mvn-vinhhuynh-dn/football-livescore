package com.androidbelieve.drawerwithswipetabs.troll_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.Troll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 *
 * Created by phulx on 05/11/2015.
 */
public class TrollAdapter extends RecyclerView.Adapter<TrollAdapter.ViewHolderTroll> {
    private Context mContext;
    private ArrayList<Troll> mArraylist = new ArrayList<>();

    public TrollAdapter(Context mContext, ArrayList<Troll> mArraylist) {
        this.mContext = mContext;
        this.mArraylist = mArraylist;
    }

    @Override
    public ViewHolderTroll onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_troll,parent,false);
        return new ViewHolderTroll(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTroll holder, int position) {
        holder.tvTroll.setText(mArraylist.get(position).getContent());
        Picasso.with(mContext).load(mArraylist.get(position).getImg()).into(holder.imgTroll);
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    class ViewHolderTroll extends RecyclerView.ViewHolder {
        ImageView imgTroll;
        TextView tvTroll;
        public ViewHolderTroll(View itemView) {
            super(itemView);
            imgTroll = (ImageView)itemView.findViewById(R.id.imgTroll);
            tvTroll = (TextView)itemView.findViewById(R.id.tvTitleTroll);
        }
    }
}
