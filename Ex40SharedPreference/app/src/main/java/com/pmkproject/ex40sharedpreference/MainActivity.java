package com.pmkproject.ex40sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_name,et_age;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        tv=findViewById(R.id.tv);
    }

    public void clickSave(View view) {
        String name=et_name.getText().toString();
        int age;
        try{
            age=Integer.parseInt(et_age.getText().toString());
        }catch (Exception e){
            age=0;
        }



        //Data.xml 파일에 데이터를 저장
        //SharedPreferences 객체 소환!!
        SharedPreferences pref=getSharedPreferences("Data",MODE_PRIVATE);

        //문서 작성으로 시작한다는 메소드
        //실행하면 문서에 글 작성을 해주는 Editor 객체가 리턴됨
        SharedPreferences.Editor editor =pref.edit();
        editor.putString("Name",name);
        editor.putInt("Age",age);

        et_name.setText("");
        et_age.setText("");

        //문서작성이 끝났다. 라고 메소드를 실행해야댐
        editor.commit();


    }

    public void clickLoad(View view) {
        SharedPreferences pref=getSharedPreferences("Data",MODE_PRIVATE);

        String name;
        int age;

        name=pref.getString("Name","익명"); //key(식별자), default value : 만약 값이 오지않았을 경우 기본값
        age=pref.getInt("Age",0);

        tv.setText(name+" : "+age);


    }


}
