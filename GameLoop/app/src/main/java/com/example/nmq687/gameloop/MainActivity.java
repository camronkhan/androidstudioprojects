package com.example.nmq687.gameloop;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer = new Timer();
    ImageView image;
    int imageX = 0, imageY = 0, speed = 10, directionX = 1, directionY = 1, screenX, screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) this.findViewById(R.id.imageView);

        // Declare frames per second
        final int FPS = 40;

        TimerTask updateGame = new UpdateGameTask();
        timer.scheduleAtFixedRate(updateGame, 0, 1000/FPS);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;
    }

    class UpdateGameTask extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageX += (speed * directionX);
                    imageY += (speed * directionY);

                    image.setX(imageX);
                    image.setY(imageY);

                    if ((imageX + image.getWidth()) > screenX || imageX < 0) {
                        directionX = directionX * -1;
                    }

                    if ((imageY + image.getHeight()) > screenY || imageY < 0) {
                        directionY = directionY * -1;
                    }
                }
            });
        }
    }
}
