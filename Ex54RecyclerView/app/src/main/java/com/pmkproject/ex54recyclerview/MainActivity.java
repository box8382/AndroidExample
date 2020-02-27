package com.pmkproject.ex54recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> datas=new ArrayList<>();

    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas.add("aaa");
        datas.add("bbb");
        datas.add("ccc");
        datas.add("ddd");
        datas.add("eee");
        datas.add("fff");
        datas.add("ggg");
        datas.add("hhh");
        datas.add("iii");
        datas.add("jjj");

        recyclerView=findViewById(R.id.recyclerview);

        adapter=new MyAdapter(datas,this);
        recyclerView.setAdapter(adapter);

        //GridLayoutManager 적용해 보기
        GridLayoutManager LayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(LayoutManager);

    }
}
