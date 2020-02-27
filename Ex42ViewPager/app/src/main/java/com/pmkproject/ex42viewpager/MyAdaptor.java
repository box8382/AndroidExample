package com.pmkproject.ex42viewpager;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdaptor extends PagerAdapter {

    ArrayList<Integer> datas;
    LayoutInflater inflater;

    public MyAdaptor(ArrayList<Integer> datas,LayoutInflater inflater) {
        this.datas = datas;
        this.inflater=inflater;
    }

    //총 page 수 를 리턴
    @Override
    public int getCount() {
        return datas.size();
    }

    //아답터가 만들어낼 Page(View)객체를 생성하는 코드를 작성하는 메소드
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View page=inflater.inflate(R.layout.page,null);

        ImageView iv=page.findViewById(R.id.iv);
        iv.setImageResource(datas.get(position));

        //만들어진 page(View)를 ViewPager 에 붙이기..?
        container.addView(page);

        //여기서 리턴한 View객체가 저 아래 isViewFromObject() 메소드에 전달됨
        return page;
    }


    //Viewpager에서 제거해야할 page(View)를 제거할 때 자동 실행되는 메소드


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Viewpager에서 해당하는 page 객체를 제거
        container.removeView((View)object);
    }

    //위 instantiateItem() 메소드가 실행된 후 리턴된 page(View)가 ViewPager에서 현재 보여질 아이템과
    //같은지 검증하는 메소드
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
