package com.pmkproject.ex15optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //onCreate()메소드가 실행된 후에 자동으로 OptionMenu 를 만드는 작업을 하는 콜백 메소드가 발동
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //매개변수로 전달된 Menu 객체에게 MenuItem(메뉴항목)을 추가

        //1. Java 언어로 메뉴아이템 추가하기(잘 사용하지 않음)
        //groupID, itemID, order, menuTitle(보여줄 글씨)
//        menu.add(0,1,0,"aaa");
//        menu.add(0,2,0,"bbb");
        //위 처럼 java 로 하면 세세한 설정작업이 번거로움
        //그래서 XML 언어로 메뉴항목을 제작



        //2. XML 언어로 메뉴항목을 설계하고 추가하기
        //menu 폴더에 있는 option.xml 문서를 읽어와서
        //Menu 객체로 만들어주는 (부풀려주는 inflater) 객체를 얻어오기
        MenuInflater inflater=this.getMenuInflater();
        inflater.inflate(R.menu.option,menu);


        return super.onCreateOptionsMenu(menu);
    }

    //OptionMenu 의 메뉴항목을(MenuItem)을 선택했을 때 자동으로 실행되는 콜백 메소드

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        final int MENU_AAA=1;
//        final int MENU_BBB=2;

        //매개변수로 전달된 MenuItem 객체가 선택된 메뉴항목 임

        //선택된 MenuItem 을 구분하기 위해
        //메뉴항목에 설정된 id를 얻어오기
        int id=item.getItemId();

        switch (id){
            case R.id.menu_search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_add:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_help:
                Toast.makeText(this,"help",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

