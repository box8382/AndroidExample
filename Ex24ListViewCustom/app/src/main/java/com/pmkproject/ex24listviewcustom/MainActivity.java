package com.pmkproject.ex24listviewcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터 참조변수
    ArrayList<Member> members=new ArrayList<Member>();
    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 data 추가작업
        members.add(new Member(R.drawable.flag_korea,"전현무","대한민국"));
        members.add(new Member(R.drawable.flag_canada,"기욤패트리","캐나다"));
        members.add(new Member(R.drawable.flag_china,"장위안","중국"));
        members.add(new Member(R.drawable.flag_usa,"타일러","미국"));
        members.add(new Member(R.drawable.flag_italy,"알베르토","이탈리아"));

        members.add(new Member(R.drawable.flag_korea,"전현무","대한민국"));
        members.add(new Member(R.drawable.flag_canada,"기욤패트리","캐나다"));
        members.add(new Member(R.drawable.flag_china,"장위안","중국"));
        members.add(new Member(R.drawable.flag_usa,"타일러","미국"));
        members.add(new Member(R.drawable.flag_italy,"알베르토","이탈리아"));

        listView=findViewById(R.id.listview);
        //대량의 데이터를 보여줄 View를 만들어내는
        //작업을 수행한느 Adapter객체 생성
        LayoutInflater inflater=getLayoutInflater();
        adapter=new MyAdapter(members,inflater);
        listView.setAdapter(adapter);
    }
}
