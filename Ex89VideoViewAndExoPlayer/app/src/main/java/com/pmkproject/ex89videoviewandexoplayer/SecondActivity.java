package com.pmkproject.ex89videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class SecondActivity extends AppCompatActivity {

    //video Uri
    Uri videoUri=Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");

    PlayerView pv;
    //실제 비디오를 플레이 하는 객체의 참조변수
    SimpleExoPlayer simpleExoPlayer;

    //컨트롤러 뷰 참조변수
    PlayerControlView pcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pv=findViewById(R.id.playerview);
        pcv=findViewById(R.id.pcv);

    }

    //화면에 보이기 시작할때
    @Override
    protected void onStart() {
        super.onStart();
        //SimpleExoPlayer 객체 생성
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());

        //플레이어뷰에게 플레이어 설정
        pv.setPlayer(simpleExoPlayer);
        //플레이어 컨트롤 뷰 와 플레이어 연동
        pcv.setPlayer(simpleExoPlayer);

        //비디오 데이터 소스를 관리하는  `DataSource 객체를 만들어주는 팩도리 객체 생성
        DataSource.Factory factory=new DefaultDataSourceFactory(this, "Ex89VideoAndExoPlayer");
        //비디오 데이터를 Uri로 부터 추출해서 DataSource 객체(CD or LP판 같은) 생성
        ProgressiveMediaSource mediaSource=new ProgressiveMediaSource.Factory(factory).createMediaSource(videoUri);

        //만들어진 비디오 데이터 소스객체인 mediaSource을 플레이어 객체 에게 전달하여 준비하도록![로딩하도록!]
        simpleExoPlayer.prepare(mediaSource);

        //로딩이 끝나면 준비가 되었을 때 자동 실행되도록
        simpleExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //플레이어 뷰 및 플레이어 객체 초기화
        pv.setPlayer(null);
        simpleExoPlayer.release();
        simpleExoPlayer=null;
    }
}
