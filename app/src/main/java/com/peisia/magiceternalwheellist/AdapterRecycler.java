package com.peisia.magiceternalwheellist;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
public class AdapterRecycler extends RecyclerView.Adapter <AdapterRecycler.ItemViewHolder> {
    private static final int TYPE_0 = 0;    // 현재 선택된 아이템
    private static final int TYPE_1 = 1;    // 현재 선택된 아이템의 직전 직후 아이템.
    private static final int TYPE_2 = 2;    // 현재 선택된 아이템의 전전, 후후 아이템.
    private int currentNo;
    ArrayList<RecyclerItem> mItems;
    public AdapterRecycler(ArrayList<RecyclerItem> items){
        mItems = items;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
            return new ItemViewHolder(view);
        } else if(viewType == TYPE_1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_2, parent, false);
            return new ItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_3, parent, false);
            return new ItemViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String name = mItems.get(position).getCategoryName();
        holder.mImg.setVisibility(View.VISIBLE);
        switch (name){
            case "m0":
                holder.mImg.setVisibility(View.GONE);
                break;
            case "m1":
                holder.mImg.setImageResource(R.drawable.c1);
                break;
            case "m2":
                holder.mImg.setImageResource(R.drawable.c2);
                break;
            case "m3":
                holder.mImg.setImageResource(R.drawable.c3);
                break;
            case "m4":
                holder.mImg.setImageResource(R.drawable.c4);
                break;
            case "m5":
                holder.mImg.setImageResource(R.drawable.c5);
                break;
            case "m6":
                holder.mImg.setImageResource(R.drawable.c6);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.item_img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 2){
            return TYPE_0;
        } else if(position == 1 || position == 3){
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    public void setCurrentNo(int currentNo) {
        this.currentNo = currentNo;
    }
    public void setItems(ArrayList<RecyclerItem> items) {
        mItems = items;
    }
}