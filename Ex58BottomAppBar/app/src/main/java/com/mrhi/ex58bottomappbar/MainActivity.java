package com.mrhi.ex58bottomappbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bab;
    boolean isCenter= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bab= findViewById(R.id.bab);
        setSupportActionBar(bab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //옵션메뉴 만드는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bab_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void clickFab(View view) {
        isCenter= !isCenter;

        if(isCenter) bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        else bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

    }

    public void clickBtn(View view) {
        Snackbar.make(findViewById(R.id.snackbar_container), "This is Snackbar", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }
}
