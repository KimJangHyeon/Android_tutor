package com.example.owner.crawler_recycler.Adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.owner.crawler_recycler.Model.KeyWordInfo;
import com.example.owner.crawler_recycler.R;

import java.util.ArrayList;

/**
 * Created by Owner on 2018-01-03.
 */

public class KeyWordAdapter extends RecyclerView.Adapter<KeyWordAdapter.ViewHolder> {
    ArrayList<KeyWordInfo> data;

    //아이템 클릭 콜백 등록
    private ItemClick itemClick;

    public interface ItemClick {
        public void onClick(KeyWordInfo kw,int position);
        public void onLongClick(KeyWordInfo kw, int position);
    }

    //아이템 클릭 시 실행될 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keyword;
        private RelativeLayout item_bg;
        View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            keyword = (TextView) view.findViewById(R.id.keyword_txt);
            item_bg = (RelativeLayout) view.findViewById(R.id.item_bg);
        }
    }

    public KeyWordAdapter(ArrayList<KeyWordInfo> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_keyword, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = position%data.size();
        final KeyWordInfo dt = data.get(position);
        holder.keyword.setText(data.get(position).getKeyword());

        //add 이면
        if(data.get(position).getFunct()){
            holder.item_bg.setBackgroundResource(R.drawable.menubtn_mint_square);
            holder.keyword.setTextColor(Color.WHITE);
        }
        else{
            holder.item_bg.setBackgroundResource(R.drawable.item_white_square);
            holder.keyword.setTextColor(Color.BLACK);
        }

        final int finalPosition = position;
        final int finalPosition1 = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(dt, finalPosition1);
                }
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(itemClick != null){
                    itemClick.onLongClick(dt, finalPosition1);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position){
        return position;
//        return position%strings.size();
    }
}
