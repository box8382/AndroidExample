package com.pmkproject.ex85firebasechatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter {

    ArrayList<MessageItem> messageItems;
    LayoutInflater inflater;

    public ChatAdapter(ArrayList<MessageItem> messageItems, LayoutInflater inflater) {
        this.messageItems = messageItems;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int i) {
        return messageItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //현재 보여줄 번째의 데이터로 뷰를 생성
        MessageItem item=messageItems.get(i);

        //재활용할 뷰는 사용하지 않음
        View itemView=null;

        //메세지가 내 메세지인가??
        if(item.getName().equals(G.nickname)){
            itemView=inflater.inflate(R.layout.my_msgbox,viewGroup,false);
        }else{
            itemView=inflater.inflate(R.layout.other_msgbox,viewGroup,false);
        }

        //만들어진 itemView에 값들 설정
        CircleImageView iv=itemView.findViewById(R.id.iv);
        TextView tvName=itemView.findViewById(R.id.tv_name);
        TextView tvMsg=itemView.findViewById(R.id.tv_msg);
        TextView tvTime=itemView.findViewById(R.id.time);

        Glide.with(itemView).load(item.getProfileUrl()).into(iv);
        tvName.setText(item.getName());
        tvMsg.setText(item.getMessage());
        tvTime.setText(item.getTime());

        return itemView;
    }
}
