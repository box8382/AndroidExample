package com.pmkproject.ex56bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv=findViewById(R.id.bnv);
        rl=findViewById(R.id.rl);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //원래는 보통 아이템을 클릭할 때마다
                    //Fragment를 바꾸는게 일반적임
                    case R.id.bnv_aa:
                        rl.setBackgroundColor(Color.BLACK);
                        break;
                    case R.id.bnv_bb:
                        rl.setBackgroundColor(Color.RED);
                        break;
                    case R.id.bnv_cc:
                        rl.setBackgroundColor(Color.GRAY);
                        break;
                    case R.id.bnv_dd:
                        rl.setBackgroundColor(Color.CYAN);
                        break;

                }
                //리턴값이 true가 아니면 동작하지 않음
                return false;
            }
        });
    }
}
