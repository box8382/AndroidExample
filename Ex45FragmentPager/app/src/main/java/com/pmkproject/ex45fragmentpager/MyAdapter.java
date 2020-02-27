package com.pmkproject.ex45fragmentpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[3];

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);

        //Fragment 객체들 생성
        fragments[0]=new Page1Fragment();
        fragments[1]=new Page2Fragment();
        fragments[2]=new Page3Fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
