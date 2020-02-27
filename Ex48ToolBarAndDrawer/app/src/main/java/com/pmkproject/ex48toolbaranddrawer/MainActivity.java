package com.pmkproject.ex48toolbaranddrawer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바를 툴바로 대체하기
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.layout_drawer);
        navigationView=findViewById(R.id.navigation);

        //네비게이션 뷰의 메뉴아이콘의 색조 제거
        navigationView.setItemIconTintList(null);

        //드로우어 조절용 토글버튼 객체 생성
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        //액션바에 제목이 자동표시되지 않도록...
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
}
