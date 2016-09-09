package com.example.nmq687.doggydiet;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        this.setContentView(gameView);
        welcome();
    }

    public void welcome() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Welcome");
        myAlert.setMessage("Help Dexter stick to his diet.  Earn 1 point for every 10 seconds he avoids delicious pizza, hotdogs, squirrels, and bunnies.");
        myAlert.setNeutralButton("Let's Go!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        myAlert.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
