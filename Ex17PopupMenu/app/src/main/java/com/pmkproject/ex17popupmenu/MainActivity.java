package com.pmkproject.ex17popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);

        //버튼이 롱클릭되는 것을 듣는 리스너 생성 및 설정
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(MainActivity.this,"LongClick",Toast.LENGTH_SHORT).show();

                //PopupMenu 띄우기

                //Menu 객체가 놓여질 수 있는 PopupMenu 객체 생성
                PopupMenu popup=new PopupMenu(MainActivity.this,btn2);

                //PopupMenu 객체 안에 Menu 객체에 MenuItem 추가
                getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                //팝업메뉴의 메뉴아이템을 클릭하는 것을 듣는 리스너 객체 생성 및 설정
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_info:
                                Toast.makeText(MainActivity.this,"information",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_delete:
                                Toast.makeText(MainActivity.this,"delete",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_modify:
                                Toast.makeText(MainActivity.this,"modify",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });


                popup.show();

                return true; // true 면 onClick이 발동하지 않음
            }
        });

    }

    public void clickBtn(View view) {
        Toast.makeText(this,"clicked!",Toast.LENGTH_SHORT).show();
    }
}
