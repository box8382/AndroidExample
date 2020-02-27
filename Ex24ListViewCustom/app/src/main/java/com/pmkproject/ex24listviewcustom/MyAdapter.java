package com.pmkproject.ex24listviewcustom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//BaseAdapter 클래스는 추상 메소드를 보유하고 있음!!
public class MyAdapter extends BaseAdapter {

    //멤버변수
    ArrayList<Member> members;
    LayoutInflater inflater;

    //생성자 메소드
    public MyAdapter(ArrayList<Member> members,LayoutInflater inflater){
        this.members=members;
        this.inflater=inflater;
    }

    //이 아답터객체가 만들어낼 View 객체의 개수를 리턴
    @Override
    public int getCount() {
        return members.size();
    }

    //멤버의 값을 리턴하는곳 position 은 방번호
    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    //position 과 비슷한 원리인데 방번호 0,1,2 이런식으로 하고싶지않을때 사용(잘사용하지않음)
    @Override
    public long getItemId(int position) {
        return position;
    }

    //리스트뷰에 보여질 항목하나의 View 를 만들어내는 메소드
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //xml 로 View 모양을 설계하고 이를 객체로 만들어서 리턴함

        //listview_item.xml 문서를 읽어와서
        //View 객체로 만들어주는(부풀리다 inflate) 객체를 이용하여 View 객체 생성

        //XML -> View 객체로 부풀려주는 LayoutInflate 라는 직원이 필요함
        //1. new View
        //재활용할 View 가 없는가?
        if(view==null){
            view=inflater.inflate(R.layout.listview_item,null);
        }

        //2. bind View : 만들어진 뷰와 데이터를 연결

        //현재 번째(position 번째) View에 보여줄
        //데이터를 얻어오기
        Member member=members.get(position);
        ImageView iv=view.findViewById(R.id.item_iv);
        TextView tvName=view.findViewById(R.id.item_tv_name);
        TextView tvNation=view.findViewById(R.id.item_tv_nation);

        iv.setImageResource(member.imgID);
        tvName.setText(member.name);
        tvNation.setText(member.nation);

        //만들어진 뷰를 리턴하며 리스트뷰가 화면에 보여줌
        return view;
    }
}


