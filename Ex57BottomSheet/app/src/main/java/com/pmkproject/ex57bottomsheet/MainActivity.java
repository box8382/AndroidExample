package com.pmkproject.ex57bottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //다이얼로그 객체 생성
        bottomSheetDialog=new BottomSheetDialog(this);

        //다이얼로그에서 보여줄 View 객체 생성
        View view1=getLayoutInflater().inflate(R.layout.bottom_dialog,null);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog);

        //아웃사이드 터치로 캔슬여부
        bottomSheetDialog.setCanceledOnTouchOutside(false);


        //다이얼로그 안에있는 리스트뷰 참조하기
        ListView listView=view1.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, i, Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();

        //안됑.......................................................................................
    }
}
