package com.pmkproject.ex49appbartablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TabLayout tabLayout;

    ViewPager pager;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigation);

        //Toolbar 를 액션바로 대체하기
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //navigationView 아이콘 색에 색조 제거
        navigationView.setItemIconTintList(null);


        //삼선 메뉴 만들기
        drawerLayout=findViewById(R.id.layout_drawer);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //토글에 메뉴가 눌렸는지 듣는 리스너
        drawerLayout.addDrawerListener(drawerToggle);
        //동기화 작업
        drawerToggle.syncState();

        tabLayout=findViewById(R.id.layout_tab);

//        //탭버튼(tab객체) 생성
//        TabLayout.Tab tab=null;
//        tab=tabLayout.newTab().setText("Home").setIcon(R.mipmap.ic_launcher);
//        tabLayout.addTab(tab);
//        tab=tabLayout.newTab().setText("Theme").setIcon(R.mipmap.ic_launcher);
//        tabLayout.addTab(tab);
//        tab=tabLayout.newTab().setText("Talk").setIcon(R.mipmap.ic_launcher);
//        tabLayout.addTab(tab);


        //ViewPager 만들고 Fragment와 연동
        pager=findViewById(R.id.pager);
        adapter=new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);



        //TabLayout과 ViewPager를 연동
        tabLayout.setupWithViewPager(pager);
        //※주의!※ ViewPager와 연동하게 되면 위에 직접 코드로 추가했던 Tab객체는 무시됨
        //대신 ViewPager에서 Tab버튼의 글씨를 선정

        //아이콘을 삽입하고 싶다면..
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_launcher_foreground);


        //제목줄에 서브제목 설정하기
        getSupportActionBar().setSubtitle("Home");

        //탭 변경 리스너 (탭이 바뀔떄마다 서브 제목 바꾸기)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportActionBar().setSubtitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        //navigationView 에 리스너 달기
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_aa:
                        break;
                    case R.id.menu_bb:
                        break;
                    case R.id.menu_cc:
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
