package com.pmkproject.ex30activity6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDial(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);

        Uri uri=Uri.parse("tel:01022458382");
        intent.setData(uri);

        startActivity(intent);
    }

    public void clickSMS(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:01022458382,01011114444,01055555555"));

        intent.putExtra("sms_body","안녕~");

        startActivity(intent);
    }


    public void clickWeb(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.naver.com"));
        startActivity(intent);
    }

    public void clickGallery(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); //갤러리때만 쓸수있음 image/png 면 png 파일만 보여줌 *이기떄문에 다보여줌 jpg,png 등등
        startActivity(intent);
    }

    public void clickCamera(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}
