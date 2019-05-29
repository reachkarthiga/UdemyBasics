package com.android.example.udemybasics;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoPlayer extends AppCompatActivity {

    boolean active;
    int stoppos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        final VideoView video = (VideoView)findViewById(R.id.video);

        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);

        MediaController media = new MediaController(this);

        video.setMediaController(media);

        media.setAnchorView(video);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams parms = (android.widget.LinearLayout.LayoutParams) video.getLayoutParams();
        parms.width = metrics.widthPixels;
        parms.height = metrics.heightPixels;
        parms.leftMargin = 0;
        video.setLayoutParams(parms);

        video.requestFocus();

        active = true;
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Toast.makeText(getApplicationContext(),"Video Completed",Toast.LENGTH_SHORT).show();
                video.seekTo(1);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }


}
