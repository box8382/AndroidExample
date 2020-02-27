package com.pmkproject.ex49appbartablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[3];
    String[] pageTitles={"Home","Theme","Talk"};

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);

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

    //ViewPager와 연동된 TabLayout의 Tab버튼들의
    //글씨를 리턴해주는 메소드


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return pageTitles[position];
    }
}
