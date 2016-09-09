package com.example.nmq687.soundtest;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int mySound = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        AssetManager assetManager = getAssets();

        try {
            AssetFileDescriptor descriptor = assetManager.openFd("gameover.wav");
            mySound = soundPool.load(descriptor, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSound(View view) {
        soundPool.play(mySound, 1, 1, 0, 0, 1);
    }

    public void playLeft(View view) {
        soundPool.play(mySound, 1, 0, 0, 0, 1);
    }

    public void playRight(View view) {
        soundPool.play(mySound, 0, 1, 0, 0, 1);
    }

    public void playFast(View view) {
        soundPool.play(mySound, 1, 1, 0, 0, 2);
    }
}
