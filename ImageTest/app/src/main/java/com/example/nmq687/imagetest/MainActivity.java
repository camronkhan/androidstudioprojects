package com.example.nmq687.imagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ball;
    float ballX, ballY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball = (ImageView) findViewById(R.id.ball);
        ball.setX(ballX);
        ball.setY(ballY);
    }

    public void downButtonPressed(View view) {
        ballY += 25;
        ball.setY(ballY);
    }

    public void upButtonPressed(View view) {
        ballY -= 25;
        ball.setY(ballY);
    }

    public void rightButtonPressed(View view) {
        ballX += 25;
        ball.setX(ballX);
    }

    public void leftButtonPressed(View view) {
        ballX -= 25;
        ball.setX(ballX);
    }
}
