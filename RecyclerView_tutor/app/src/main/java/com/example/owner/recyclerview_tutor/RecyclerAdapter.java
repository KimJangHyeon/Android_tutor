package com.example.owner.recyclerview_tutor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-10-14.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    ArrayList<RecyclerItem> mItems;

    public RecyclerAdapter(ArrayList<RecyclerItem> items){
        mItems = items;
    }


    //create new view holder
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mNameTv.setText(mItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.itemNameTv);
        }
    }
}
