package com.pmkproject.ex68googlemaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 관리자 객체 소환
        FragmentManager fragmentManager=getSupportFragmentManager();

        //관리자에게 xml에 있는 Fragment를 찾아와달라고 함
        SupportMapFragment mapFragment=(SupportMapFragment) fragmentManager.findFragmentById(R.id.frag_map);

        //Fragment에게 맵 가져오도록
        mapFragment.getMapAsync(this);


    }

    //구글 맵 로딩이 완료되면 실행되는 콜백 메소드
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap; //다른곳에서 사용하기 위해 맴버변수에 참조값을 넣어줌

        LatLng seoul=new LatLng(37.56,126.97);
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(seoul);
        markerOptions.title("서울입니다");
        markerOptions.snippet("나");

        //marker 맵에 추가하기
        gMap.addMarker(markerOptions);

        //지도 위치 지정 및 줌
        gMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoul,15));

        //대표적인 맵 설정
        UiSettings settings=gMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
    }

}
