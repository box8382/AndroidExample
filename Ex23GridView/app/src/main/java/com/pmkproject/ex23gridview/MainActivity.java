package com.pmkproject.ex23gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter adapter;

    //대량의 데이터 참조변수
    ArrayList<String> datas=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 Datas
        datas.add("aaa");
        datas.add("bbb");
        datas.add("ccc");
        datas.add("ddd");
        datas.add("eee");
        datas.add("fff");

        gridView=findViewById(R.id.gridview);
        
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datas);
        gridView.setAdapter(adapter);

    }
}
