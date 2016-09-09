package com.example.nmq687.collisiongame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    GameView gmvw;
    Paint drawPaint = new Paint();
    Paint drawText = new Paint();
    int backgroundColor = Color.BLACK;
    Bitmap dog, hotdog, bunny, squirrel;
    int dogX = 500, dogY = 500;
    int hotdogX = 400, hotdogY = 400, hotdogXSpeed = 10, hotdogYSpeed = 10;
    int bunnyX = 100, bunnyY = 100, bunnyXSpeed = 10, bunnyYSpeed = 10;
    int squirrelX = 200, squirrelY = 500, squirrelXSpeed = 10, squirrelYSpeed = 10;
    float touchX, touchY;
    boolean dogSelected = false;
    Rect dogRect, hotdogRect, bunnyRect, squirrelRect;
    SoundPool soundPool;
    int dogBark = -1, dogHowl = -1;
    long startTime;
    boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use GameView to display content on screen
        gmvw = new GameView(this);

        // Set content view to GameView
        this.setContentView(gmvw);

        // Create graphics
        dog = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        hotdog = BitmapFactory.decodeResource(getResources(), R.drawable.hotdog);
        bunny = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
        squirrel = BitmapFactory.decodeResource(getResources(), R.drawable.squirrel);

        // Initialize sound elements
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        AssetManager assetManager = getAssets();
        try {
            AssetFileDescriptor barkDescriptor = assetManager.openFd("bark.wav");
            dogBark = soundPool.load(barkDescriptor, 1);
            AssetFileDescriptor howlDescriptor = assetManager.openFd("howl.wav");
            dogHowl = soundPool.load(howlDescriptor, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        welcome();
    }

    public void welcome() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Welcome");
        myAlert.setMessage("Help Dexter stay on his diet as long has he can by avoiding delicious hotdogs, squirrels, and bunnies.");
        myAlert.setNeutralButton("Let's Diet!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameStarted = true;
                // Start timer
                startTime = System.nanoTime();
            }
        });

        myAlert.show();
    }

    public void gameOver() {

    }

    public void playBark() {
        soundPool.play(dogBark, 1, 1, 0, 0, 1);
    }

    public void playHowl() {
        soundPool.play(dogHowl, 1, 1, 0, 0, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gmvw.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gmvw.resume();
    }

    // GameView is a thread that periodically updates the screen
    public class GameView extends SurfaceView implements Runnable {

        // Create thread for ViewThread
        Thread ViewThread = null;

        // Create a holder for the SurfaceView (kind of an ID for the screen)
        SurfaceHolder holder;

        // Check if OK to run thread
        boolean threadOK = true;

        public GameView(Context context) {
            super(context);

            // Initialize holder
            holder = this.getHolder();
        }

        @Override
        public void run() {
            // Lock and post (double buffering)
            while (threadOK) {
                // Check if a holder exists
                if(!holder.getSurface().isValid()) {
                    continue;
                }

                // Otherwise, create secondary game canvas in background to work on
                Canvas gameCanvas = holder.lockCanvas();

                // Set object frames
                dogRect = new Rect(dogX, dogY, dogX + dog.getWidth(), dogY + dog.getHeight());
                hotdogRect = new Rect(hotdogX, hotdogY, hotdogX + hotdog.getWidth(), hotdogY + hotdog.getHeight());
                bunnyRect = new Rect(bunnyX, bunnyY, bunnyX + bunny.getWidth(), bunnyY + bunny.getHeight());
                squirrelRect = new Rect(squirrelX, squirrelY, squirrelX + squirrel.getWidth(), squirrelY + squirrel.getHeight());

                // Modify secondary canvas
                doDraw(gameCanvas);

                // Post modified secondary canvas to primary canvas
                holder.unlockCanvasAndPost(gameCanvas);
            }
        }

        // Run every time screen is redrawn
        protected void doDraw(Canvas canvas) {
            // Set background to fully colored
            drawPaint.setAlpha(255);

            // Draw color red on background
            canvas.drawColor(backgroundColor);

            // Display elapsed time
            drawText.setColor(Color.WHITE);
            drawText.setTextSize(60);
            final long elapsed = System.nanoTime() - startTime;
            final double seconds = ((double) elapsed / 1000000000);
            canvas.drawText("Elapsed Time: " + String.valueOf(new DecimalFormat("#.##").format(seconds)) + " s", 50, 100, drawText);

            // Draw graphics on the screen
            canvas.drawBitmap(dog, dogX, dogY, drawPaint);
            canvas.drawBitmap(hotdog, hotdogX, hotdogY, drawPaint);
            canvas.drawBitmap(bunny, bunnyX, bunnyY, drawPaint);
            canvas.drawBitmap(squirrel, squirrelX, squirrelY, drawPaint);

            // Collision check
            if (Rect.intersects(hotdogRect, bunnyRect)) {
                hotdogXSpeed *= -1;
                hotdogYSpeed *= -1;
                bunnyXSpeed *= -1;
                bunnyYSpeed *= -1;
            }
            if (Rect.intersects(hotdogRect, squirrelRect)) {
                hotdogXSpeed *= -1;
                hotdogYSpeed *= -1;
                squirrelXSpeed *= -1;
                squirrelYSpeed *= -1;
            }
            if (Rect.intersects(squirrelRect, bunnyRect)) {
                squirrelXSpeed *= -1;
                squirrelYSpeed *= -1;
                bunnyXSpeed *= -1;
                bunnyYSpeed *= -1;
            }
            if (Rect.intersects(dogRect, hotdogRect)) {
                hotdogXSpeed *= -1;
                hotdogYSpeed *= -1;
                gameOver();
            }
            if (Rect.intersects(dogRect, bunnyRect)) {
                bunnyXSpeed *= -1;
                bunnyYSpeed *= -1;
                gameOver();
            }
            if (Rect.intersects(dogRect, squirrelRect)) {
                squirrelXSpeed *= -1;
                squirrelYSpeed *= -1;
                gameOver();
            }

            // Move hotdog
            hotdogX += hotdogXSpeed;
            hotdogY += hotdogYSpeed;

            // Check if hotdog going off screen
            if (hotdogX < 0 || hotdogX > canvas.getWidth()) {
                // Reverse direction
                hotdogXSpeed *= -1;
            }
            if (hotdogY < 0 || hotdogY > canvas.getHeight()) {
                // Reverse direction
                hotdogYSpeed *= -1;
            }

            // Move bunny
            bunnyX += bunnyXSpeed;
            bunnyY += bunnyYSpeed;

            // Check if bunny going off screen
            if (bunnyX < 0 || bunnyX > canvas.getWidth()) {
                // Reverse direction
                bunnyXSpeed *= -1;
            }
            if (bunnyY < 0 || bunnyY > canvas.getHeight()) {
                // Reverse direction
                bunnyYSpeed *= -1;
            }

            // Move squirrel
            squirrelX += squirrelXSpeed;
            squirrelY += squirrelYSpeed;

            // Check if bunny going off screen
            if (squirrelX < 0 || squirrelX > canvas.getWidth()) {
                // Reverse direction
                squirrelXSpeed *= -1;
            }
            if (squirrelY < 0 || squirrelY > canvas.getHeight()) {
                // Reverse direction
                squirrelYSpeed *= -1;
            }
        }

        // Handle pause when app moves to background
        public void pause() {
            // Do not run thread
            threadOK = false;

            // Hold in tight loop until thread can be joined
            while (true) {
                try {
                    // Try to join thread
                    ViewThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ViewThread = null;
        }

        // Handle resume when app moves to foreground
        public void resume() {
            // OK to run thread
            threadOK = true;

            // Create a new thread
            ViewThread = new Thread(this);

            // Start thread
            ViewThread.start();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // Icon pressed
            case MotionEvent.ACTION_DOWN :
            {
                touchX = event.getRawX();
                touchY = event.getRawY() - 200;

                if (touchX > dogX &&
                        touchX < dogX + dog.getWidth() &&
                        touchY > dogY &&
                        touchY < dogY + dog.getHeight())
                {
                    dogSelected = true;
                }
            }
            break;

            // Icon moved
            case MotionEvent.ACTION_MOVE :
            {
                touchX = event.getRawX() - (dog.getWidth() / 2);
                touchY = event.getRawY() - 200 - (dog.getHeight() / 2);

                if (dogSelected) {
                    dogX = (int) touchX;
                    dogY = (int) touchY;
                }
            }
            break;

            // Icon released
            case MotionEvent.ACTION_UP :
            {
                dogSelected = false;
            }
            break;
        }
        return true;
    }
}
