package com.pmkproject.ex42viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    //1. 대량의 데이터들
    ArrayList<Integer> datas=new ArrayList<>();
    MyAdaptor adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);

        //대량의 데이터들 추가
        for(int i=0;i<10;i++) {
            datas.add(R.drawable.gametitle_01+i);
        }

        adaptor=new MyAdaptor(datas,getLayoutInflater());
        pager.setAdapter(adaptor);

        //Page를 조금이라도 움직일때마다 자동으로 발동하는 메소드를 보유한
        //리스너를 추가


        //화면을 옆으로 넘길때마다 불러지는 메소드
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setRotationY(position*90);
                page.setScaleX((1-Math.abs(position))/2+0.5f);
                page.setScaleY((1-Math.abs(position))/2+0.5f);
                page.setAlpha(1-Math.abs(position));
            }
        });

    }

    public void clickPrev(View view) {
        //현재 pager가 보여주는 페이지 index
        int index=pager.getCurrentItem();

        //특정페이지로 이동(이전페이지 : 현재페이지-1)
        pager.setCurrentItem(index-1,true);
    }

    public void clickNext(View view) {
        int index=pager.getCurrentItem();
        pager.setCurrentItem(index+1,true);//다음페이지
    }
}
