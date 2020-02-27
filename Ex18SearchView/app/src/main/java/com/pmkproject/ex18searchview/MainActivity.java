package com.pmkproject.ex18searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Option Menu 를 만드는 메소드
    //onCreate() 실행 후 자동으로 실행 됨
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //xml 에서 만든 메뉴를 Menu 객체로 만들어 붙이기
        getMenuInflater().inflate(R.menu.actionbar,menu);

        //SearchView 에 써있는 글씨 얻어오기
        //SearchView 에 의해 올라온 소프트키보드의 검색 버튼(돋보기모양버튼)을 클릭하는 것을
        //듣는 리스터 추가..

        //MenuItem 객체로 부터 SearchView 객체 참조하기
        MenuItem item=menu.findItem(R.id.menu_search);
        searchView=(SearchView)item.getActionView();

        //힌트적용하기
        searchView.setQueryHint("검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,query,Toast.LENGTH_SHORT).show();

                //서치뷰의 글씨를 없애기
                searchView.setQuery("",true);
                //서치뷰를 돋보기 모양으로 변경하기
                searchView.setIconified(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
