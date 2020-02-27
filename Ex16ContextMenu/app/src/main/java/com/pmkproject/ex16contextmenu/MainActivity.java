package com.pmkproject.ex16contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button  btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);

        //액티비티에게 btn 객체를 ContextMenu 로 등록
        this.registerForContextMenu(btn);
    }

    //ContextMenu 로 등록된 뷰가 롱클릭되면
    //ContextMenu 를 만드는 메소드가 자동으로 실행
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //menu 폴더안에 context.xml 문서를 읽어와서
        //Menu 객체를 만들어주는(부풀려주다 inflater) 객체를 Context 로 부터 얻어오기
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //컨텍스트메뉴의 아이템을 선택했을때
    //자동으로 실행되는 콜백메소드


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.menu_save:
                Toast.makeText(this,"save",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    //onclick 속성이 부여된 View 가 클릭되면
    //자동으로 클릭되는 메소드
    public void clickBtn(View view){
        Toast.makeText(this,"clicked!!",Toast.LENGTH_SHORT).show();

    }
}
