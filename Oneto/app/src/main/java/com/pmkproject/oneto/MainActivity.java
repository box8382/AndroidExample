package com.pmkproject.oneto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView start;
    LinearLayout bg;
    ImageView[] btn=new ImageView[12];
    int[] arr=new int[12];

    int num=1;

    int level=0;

    void ran(){
        Random rnd=new Random();
        for(int i=0;i<arr.length;i++){
            arr[i]=rnd.nextInt(12)+1;
            for(int j=0;j<i;j++){
                if(arr[i]==arr[j]){
                    i--;
                    break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        start=findViewById(R.id.start);
        bg=findViewById(R.id.bg);

        ran();

        for(int i=0;i<btn.length;i++){
            btn[i]=findViewById(R.id.num01+i);
            btn[i].setImageResource(R.drawable.num01+arr[i]-1);
            btn[i].setTag(arr[i]);
            btn[i].setOnClickListener(listener);
        }


        start.setOnClickListener(new View.OnClickListener() { //스타트버튼 눌렀을경우
            @Override
            public void onClick(View view) {
                for(int i=0;i<btn.length;i++){
                    btn[i].setVisibility(View.VISIBLE);
                    start.setImageResource(R.drawable.ing);
                    start.setClickable(false);
                    tv.setText("숫자를 순서대로 누르세요!");
                }
            }
        });
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(num==(int)view.getTag()){ //내가 누른숫자랑 맞춰야할 숫자가 동일한경우
                view.setVisibility(View.INVISIBLE);
                num++;

                if(num>=13) {
                    num=1;
                    level++;
                    ran();
                    if(level==1) {
                        tv.setText("알파벳 순으로 누르세요!");
                        for (int i = 0; i < btn.length; i++) {
                            btn[i].setImageResource(R.drawable.alpa01 + arr[i] - 1);
                            btn[i].setTag(arr[i]);
                            btn[i].setVisibility(View.VISIBLE);
                            bg.setBackgroundResource(R.drawable.bg2);
                        }
                    }
//                    if(level==2){
//                        tv.setText("십이지신 순으로 누르세요!");
//                        for (int i = 0; i < btn.length; i++) {
//                            btn[i].setImageResource(R.drawable.cha01 + arr[i] - 1);
//                            btn[i].setTag(arr[i]);
//                            btn[i].setVisibility(View.VISIBLE);
//                            bg.setBackgroundResource(R.drawable.bg3);
//                        }
//                    }
                    if(level==2){
                        tv.setVisibility(View.INVISIBLE);
                        start.setVisibility(View.INVISIBLE);
                        bg.setBackgroundResource(R.drawable.bg4);
                    }
//
                }

            }

        }
    };



}
