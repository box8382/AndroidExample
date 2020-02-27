package com.pmkproject.kakaomaptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


public class MainActivity extends AppCompatActivity {

    GoogleApiClient googleApiClient;
    FusedLocationProviderClient providerClient;

    double lat;
    double lng;

    MapView mapView;
    MapPOIItem maker=null;
    MapPoint mapPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView=new MapView(this);
        ViewGroup mapViewContainer=findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setZoomLevel(5, true);



        //api23버전부터 실행
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                String[] check = {Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(check, 10);
            }else{
                location();
            }
        }





    }

    public void location(){
        GoogleApiClient.Builder builder=new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                LocationRequest request=new LocationRequest();
                request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                request.setInterval(1000);//갱신지간
                providerClient.requestLocationUpdates(request,locationCallback, Looper.myLooper());
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        });
        googleApiClient=builder.build();
        googleApiClient.connect();
        providerClient=LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==10){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //동적퍼미션 수락했을시 할 콜백메소드
                location();
            }
        }
    }

    LocationCallback locationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Location location = locationResult.getLastLocation();
            lat = location.getLatitude();
            lng = location.getLongitude();

            //좌표값 지정 하는곳
            mapPoint = MapPoint.mapPointWithGeoCoord(lat, lng);

            //카카오맵 마커
            if (maker==null){
                maker = new MapPOIItem();
            }else{
                mapView.removePOIItem(maker);
            }
            maker.setItemName("Me");
            maker.setMapPoint(mapPoint);
            maker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            mapView.addPOIItem(maker);
        }
    };

    public void clickMe(View view) {
        mapView.setMapCenterPoint(mapPoint, true);
    }
}
