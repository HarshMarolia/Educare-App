package com.example.educare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class musicx extends AppCompatActivity {
    SeekBar volBar, songBar;
    MediaPlayer mediaPlayer;

    public void play(View view){
        mediaPlayer.start();
    }
    public void pause(View view){
        mediaPlayer.pause();
    }
    public void stop(View view){
        mediaPlayer.stop();
    }

    public void goback(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicx);
        volBar = findViewById(R.id.seekBar);
        songBar = findViewById(R.id.seekBar2);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volBar.setProgress(currVol);
        volBar.setMax(maxVol);
        volBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
//        mediaPlayer.start();
        songBar.setMax(mediaPlayer.getDuration());
        songBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                songBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 1000);

        // Code to hide title bar
        getSupportActionBar().hide();
    }
}