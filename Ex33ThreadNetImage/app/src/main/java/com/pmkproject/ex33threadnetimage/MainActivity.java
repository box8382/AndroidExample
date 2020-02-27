package com.pmkproject.ex33threadnetimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv);
    }

    public void clickBtn(View view) {
        //Network 상에 있는 이미지를 읽어와서 이미지뷰에 보여주기

        //Network 작업은 오래걸리는 작업으로 인지하므로..
        //MainThread 에서는 작업 수행할 수 없습니다
        //별도의 Thread 를 생성해서 네트워크 작업을 수행하도록.. 해야함
        new Thread(){
            @Override
            public void run() {
                //이미지의 경로
                String imgUrl="https://img.seoul.co.kr/img/upload/2017/10/07/SSI_20171007154542_V.jpg";

                //Stream을 열수 있는 해임달같은넘(URL) 객체 생성
                try {
                    URL url=new URL(imgUrl);

                    //무지개 로드(Stream)을 얻어오기
                    InputStream is=url.openStream();

                    //스트림을 통해 이미지를 읽어와서
                    //이미지뷰에 설정!
                    final Bitmap bm= BitmapFactory.decodeStream(is);

                    //UI변경(이미지변경)은 MainThread만 할 수 있음
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
