package com.pmkproject.ex53listviewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<String> datas;
    LayoutInflater inflater;

    public MyAdapter(ArrayList<String> datas, LayoutInflater inflater) {
        this.datas = datas;
        this.inflater = inflater;
    }


    //데이터의 개수
    @Override
    public int getCount() {
        return datas.size();
    }

    //데이터 값?
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


        //재활용할 뷰가 없는가?
        if(view==null){
            view=inflater.inflate(R.layout.listview_item,viewGroup,false);

            //만들어진 뷰(view)를 보관
            ViewHolder holder=new ViewHolder(view);
            view.setTag(holder);
        }

        ViewHolder holder=(ViewHolder) view.getTag();

        String s=datas.get(i);
        holder.tv.setText(s);

        return view;
    }

    //이너 클래스
    class ViewHolder{

        //아이템뷰 안에 있는 뷰들
        TextView tv;

        public ViewHolder(View itemView) {
            tv=itemView.findViewById(R.id.tv);
        }
    }

}
