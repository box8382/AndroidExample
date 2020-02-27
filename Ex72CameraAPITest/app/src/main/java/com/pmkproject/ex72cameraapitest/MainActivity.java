package com.pmkproject.ex72cameraapitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyCarmeraView cv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv=findViewById(R.id.cv);
        iv=findViewById(R.id.iv);

        //동적퍼미션 작업
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkCallingOrSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED
            || checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                String[] permisstions={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permisstions,10);
            }else{
                cv.setVisibility(View.VISIBLE);
            }
        }
    }

    public void clickCapture(View view) {
        cv.camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                //카메라가 취득한 사진을 byte[] 로 줌
                //byte[]을 Bitmap객체로 생성
                Bitmap bm=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                iv.setImageBitmap(bm);

                //파일 입출력을 이용해서 byte[]을 파일로 저장하기
                //외부메모리 저장하기 예제를 참고해서 ...!
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 10:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED || grantResults[1]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "이 앱 사용불가", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    cv.setVisibility(View.VISIBLE);

                }
                break;
        }
    }
}
