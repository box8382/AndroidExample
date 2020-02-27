package com.pmkproject.matchpic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rnd=new Random(); //랜덤값
    ImageView[] btns=new ImageView[5]; //배열로 캐릭터 5개
    int[] rndint=new int[5];    //랜덤값 저장할 인트형의 배열

    Toast t;
    boolean star=true; //재시작 불가능

    ImageView pat; //펫말 제어 멤버변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pat=findViewById(R.id.pat);

        for(int i=0;i<btns.length;i++){
            btns[i]=findViewById(R.id.btn01+i);
            btns[i].setOnClickListener(listener);
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(pat.getTag()==view.getTag()){
                shuffle();
                regame();
            }else{
                if(t==null) {
                    t=null;
                }
                    t = Toast.makeText(MainActivity.this, "다시 잘 봐!", Toast.LENGTH_SHORT);
                    t.show();
            }
        }
    };


    public void start(View view) {
        if(star) {
            shuffle();
            regame();
            star=false;
        }else{
            if(t==null) {
                t=null;
            }
            t = Toast.makeText(MainActivity.this, "한번만 눌러!", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void shuffle(){
        for(int i=0;i<rndint.length;i++){
            rndint[i]=rnd.nextInt(5)+1;
            for(int j=0;j<i;j++){
                if(rndint[i]==rndint[j]){
                    i--;
                    break;
                }
            }
        }
    }

    public void regame(){
        for(int i=0;i<btns.length;i++){
            btns[i].setTag(rndint[i]);
            for(int j=0;j<btns.length;j++){
                if((int)btns[i].getTag()==j+1) btns[i].setImageResource(R.drawable.a_ele+j);
            }
        }
        int num=rnd.nextInt(5)+1;
        pat.setImageResource(R.drawable.b_ele+num-1);
        pat.setTag(num);
    }

    public void how(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("게임설명");
        builder.setMessage(R.string.explain);
        builder.setPositiveButton("확인", null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}
