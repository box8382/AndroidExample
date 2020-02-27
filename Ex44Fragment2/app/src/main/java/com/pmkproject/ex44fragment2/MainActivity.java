package com.pmkproject.ex44fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //id 가 container인 녀석에게 MyFragment를 추가

        //Fragment 관리자를 소환
        FragmentManager fragmentManager=getSupportFragmentManager();

        //Fragment 에 동적추가, 삭제, 재배치 작업을 시작한다 라고 말해야댐
        FragmentTransaction ft=fragmentManager.beginTransaction();//작업자 리턴


        //begin 과 commit 사이에 작업을 코딩
        //[어디에 붙일건지 id],[누구를 붙일건지 객체]
        MyFragment fragment=new MyFragment();

        //Fragment 에 데이터를 전송하는 기능
        Bundle bundle=new Bundle();//보따리 같은 객체?
        bundle.putString("Name","sam");
        bundle.putInt("Age",20);
        fragment.setArguments(bundle);

        ft.add(R.id.container,fragment);

        //백스택에 넣지 않고 뒤로가기를 누르면 액티비티가 바로꺼짐
        //왜? Fragment 가 스택에 저장되지 않으므로
        ft.addToBackStack(null);

        //트랙잭션 작업이 완료되었다고 명령
        ft.commit(); //완료보고?


    }
}
