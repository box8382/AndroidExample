package com.pmkproject.ex69cameratest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv);
    }

    public void clickPhoto(View view) {
        //Camera 앱을 실행시키는 Intent생성
        Intent intent=new Intent(); //묵시적 인텐트
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        //카메라에서 사진을 찍고 사진을 돌려받기 위해서
        startActivityForResult(intent,100);


    }
    //startActivityForResult()로 실행한
    //액티비티가 종료된 후 자동으로 실행되는
    //콜백 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                //카메라앱에서 사진을 가지고왔다면 (정상적인절차)
                if(resultCode==RESULT_OK){
                    //캡처한 사진 이미지를 이미지뷰에 보여주기

                    //디바이스 마다 프로그램을 통해서 실행한 카메라 앱의 사진캡쳐방식이 다름
                    //마시멜로우? 부터 앱이 캡처한 이미지를 파일로 저장하지 않고 Bitmap이미지 데이터로
                    //결과를 돌려주는 디바이스들이 많음

                    //결과물을 가져온 Intent객체(3번째 파라미터: data)에게 결과 받기
                    Uri uri=data.getData(); //uri:리소스의 경로를 관리하는 객체

                    if(uri!=null){ //보안의 이유때문에 사진파일을 저장하지않음 [과거방식]
                        Toast.makeText(this, "uri로 사진정보 흭득", Toast.LENGTH_SHORT).show();
                        // iv.setImageURI(uri); //사진은 해상도, 그래픽이 좋기때문에 뻑이갈 위험이있음
                        Glide.with(this).load(uri).into(iv);
                    }

                    else{ //Bitmap이미지 데이터만 줄때[현재방식]
                        Toast.makeText(this, "Bitmap객체로 사진정보 흭득", Toast.LENGTH_SHORT).show();
                        //Bitmap 객체를 Intent로 부터 Extra 데이터로 받아와서 Bundle에 넣어주면 됨?
                        Bundle bundle=data.getExtras();
                        Bitmap bm=(Bitmap) bundle.get("data");

                        Glide.with(this).load(bm).into(iv);
                    }


                }
                break;
        }
    }


}
