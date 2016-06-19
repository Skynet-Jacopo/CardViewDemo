package com.example.liuqun.cardviewdemo;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqun on 6/19/2016.
 */
public class QuickAdapter extends BaseQuickAdapter<Beauty> {
    List<Beauty> mDatas = new ArrayList<>();
    public QuickAdapter(Context context) {
        super(context, R.layout.layout_item,null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Beauty beauty) {
        baseViewHolder.setText(R.id.desc,beauty.getDesc()).setText(R.id.who,
                beauty.getWho()).setText(R.id.item_text,beauty.getCreatedAt());
        Picasso.with(mContext).load(beauty.getUrl()).placeholder(R.mipmap.ic_launcher).error
                (R.mipmap.ic_launcher).into((ImageView) baseViewHolder.getView(R.id.item_pic));
    }
}
