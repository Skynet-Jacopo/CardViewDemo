package com.example.liuqun.cardviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqun on 6/19/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
     List<Beauty> mDatas = new ArrayList<>();
    private MainActivity mMainActivity;

    public HomeAdapter(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mMainActivity).inflate(R.layout.layout_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.time.setText(mDatas.get(position).getCreatedAt());
        holder.desc.setText(mDatas.get(position).getDesc());
        holder.who.setText("作者:" + mDatas.get(position).getWho());
        Picasso.with(mMainActivity).load(mDatas.get(position).getUrl
                ()).placeholder(R.mipmap.ic_launcher).error
                (R.mipmap.ic_launcher).into
                (holder.iv);

    }

    //封装加数据的方法-  多条
    public void appendData(List<Beauty> data, boolean isClearOld) {
        if (data == null)
            return;
        if (isClearOld)
            mDatas.clear();
        mDatas.addAll(data);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time, desc, who;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.desc);
            who = (TextView) view.findViewById(R.id.who);
            time = (TextView) view.findViewById(R.id.item_text);
            iv = (ImageView) view.findViewById(R.id.item_pic);
        }
    }
}
