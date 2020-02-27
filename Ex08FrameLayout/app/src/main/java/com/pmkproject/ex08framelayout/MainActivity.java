package com.pmkproject.ex08framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutKorea;
    LinearLayout layoutJapan;
    LinearLayout layoutChina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutKorea.findViewById(R.id.layout_korea);
        layoutJapan.findViewById(R.id.layout_japan);
        layoutChina.findViewById(R.id.layout_china);

    }

    public void clickBtn(View view) {

        layoutKorea.setVisibility(View.GONE);
        layoutJapan.setVisibility(View.GONE);
        layoutChina.setVisibility(View.GONE);

        int id=view.getId();
        switch (id){
            case R.id.layout_korea:
                layoutKorea.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_japan:
                layoutJapan.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_china:
                layoutChina.setVisibility(View.VISIBLE);
                break;
        }
    }
}
