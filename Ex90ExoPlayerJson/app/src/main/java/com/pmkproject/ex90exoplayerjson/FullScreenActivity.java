package com.pmkproject.ex90exoplayerjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class FullScreenActivity extends AppCompatActivity {

    PlayerView pv;
    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        pv=findViewById(R.id.pv);
        player= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        pv.setPlayer(player);

        Intent intent=getIntent();
        String videoUrl=intent.getStringExtra("videoUrl");
        Long currentPos=intent.getLongExtra("currentPos",0);

        //미디어소스 객체 생성
        DataSource.Factory factory=new DefaultDataSourceFactory(this, "Ex90ExoPlayer");
        ProgressiveMediaSource.Factory mediafactory=new ProgressiveMediaSource.Factory(factory);

        ProgressiveMediaSource mediaSource=mediafactory.createMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

        player.seekTo(currentPos);

    }
}
