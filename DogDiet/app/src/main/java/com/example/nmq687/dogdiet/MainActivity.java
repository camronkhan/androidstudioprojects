package com.example.nmq687.dogdiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Inflate frameLayout element
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);  // Reference frameLayout element
        GameView gameView = new GameView(this);  // Instantiate customer surface view
        frameLayout.addView(gameView);  // Add surface view to frameLayout
    }
}
