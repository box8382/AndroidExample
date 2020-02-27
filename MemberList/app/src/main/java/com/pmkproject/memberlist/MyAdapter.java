package com.pmkproject.memberlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<Member> members;
    LayoutInflater inflater;

    public MyAdapter(ArrayList<Member> members,LayoutInflater inflater){
        this.members=members;
        this.inflater=inflater;
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view==null) {
            view =inflater.inflate(R.layout.listview_member,null);
        }

        Member member=members.get(position);
        TextView name=view.findViewById(R.id.member_name);
        TextView nation=view.findViewById(R.id.member_nation);
        ImageView img_nation=view.findViewById(R.id.member_img_nation);
        ImageView img_gender=view.findViewById(R.id.member_img_gender);
        TextView clock=view.findViewById(R.id.member_clock);

        name.setText(member.name);
        nation.setText(member.nation);
        img_nation.setImageResource(member.img_nation);
        img_gender.setImageResource(member.img_gender);
        clock.setText(member.clock);

        return view;
    }
}
