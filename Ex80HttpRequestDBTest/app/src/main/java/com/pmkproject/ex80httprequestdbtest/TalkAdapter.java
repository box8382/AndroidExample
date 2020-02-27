package com.pmkproject.ex80httprequestdbtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TalkAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<TalkItem> datas;

    public TalkAdapter(LayoutInflater inflater, ArrayList<TalkItem> datas) {
        this.inflater = inflater;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }
        TalkItem item = datas.get(i);

        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvDate = view.findViewById(R.id.tv_date);
        TextView tvMsg = view.findViewById(R.id.tv_msg);
        ImageView iv = view.findViewById(R.id.iv);

        tvName.setText(item.name);
        tvDate.setText(item.date);
        tvMsg.setText(item.msg);
        //네트워크에 있는 이미지 읽어오기
        Glide.with(view).load(item.imgPath).into(iv);

        return view;
    }
}
