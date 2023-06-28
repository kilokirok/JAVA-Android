package com.example.module;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private Module tv;
    MediaPlayer sound;


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        sound = MediaPlayer.create(this, R.raw.s2);
        sound.setLooping(true);
        setContentView(R.layout.activity_main);
        Module tv = new Module(this);

        setContentView(tv);
    }

    @Override
    public void onResume() {

        super.onResume();

        if (Prefs.sound(this)) {

            sound.start();


        }
        tv.restartapp();
    }

    @Override
    protected void onPause() {

        super.onPause();

        if (Prefs.sound(this)) {

            sound.pause();

        }
        tv.pauseapp();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (Prefs.sound(this)) {

            sound.release();

        }
    }



}