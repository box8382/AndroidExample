package com.pmkproject.ex20listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    //Adapter 객체 참조변수
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);

        //리스트뷰에 보여질 View 객체들을 만들어내는
        //작업을 수행하는 Adapter 객체 생성
        adapter=ArrayAdapter.createFromResource(this,R.array.datas,R.layout.list_item);
        //리스트뷰에 아답터객체를 연결
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //res 폴더 안에 있는 values 폴더 안에 있는 arrays.xml 안에 작성한 datas 라는 이름의
                //String 배열을 참조하기

                //res 창고 관리자 소환
                Resources res=MainActivity.this.getResources();

                //창고관리자에게 arrays.xml 문서 안에 있는 datas 라는 이름의 배열 객체 얻어오기
                String[] arr=res.getStringArray(R.array.datas);

                Toast.makeText(MainActivity.this,arr[position],Toast.LENGTH_SHORT).show();

            }
        });

        //아이템 롱 클릭 리스너 생성 및 설정
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"longClick",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
