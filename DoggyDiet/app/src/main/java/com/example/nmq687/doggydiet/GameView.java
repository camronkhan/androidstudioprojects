package com.example.nmq687.doggydiet;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private final int REVERSE = -1;
    private Thread ViewThread = null;
    private SurfaceHolder holder;   // ID for surface view
    private boolean threadRunning = true;
    private Paint paint = new Paint();
    private Paint paintText = new Paint();
    private int backgroundColor = Color.BLACK;
    private ArrayList<Food> foods = new ArrayList<>();
    private Random rand = new Random();
    private boolean gameStarted = false;
    private Dog dexter;
    private boolean dogSelected = false;
    private float touchX, touchY;
    SoundPool soundPool;
    int dogHowl;
    long startTime;
    Context context;


    /***** CONSTRUCTORS *****/
    public GameView(Context context) {
        super(context);
        holder = this.getHolder();
        this.context = context;

        // Initialize images
        initializeImages(context);

        // Initialize sound elements
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        AssetManager assetManager = context.getAssets();
        try {
            AssetFileDescriptor howlDescriptor = assetManager.openFd("howl.wav");
            dogHowl = soundPool.load(howlDescriptor, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startTime = System.nanoTime();
    }


    /***** THREAD UTILITIES *****/

    // Run thread
    @Override
    public void run() {
        // Check if OK to lock screen
        while(threadRunning == true) {
            // Check if holder is present
            if (!holder.getSurface().isValid()) {
                continue;
            }
            // Lock and post to canvas
            Canvas gameCanvas = holder.lockCanvas();  // Create a secondary view to work on
            doDraw(gameCanvas);  // Make changes
            holder.unlockCanvasAndPost(gameCanvas);  // Update main screen with changes
        }
    }

    // Pause thread
    public void pause() {
        threadRunning = false;
        while(true) {
            try {
                ViewThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        ViewThread = null;
    }

    // Resume thread
    public void resume() {
        threadRunning = true;
        ViewThread = new Thread(this);  // Create thread
        ViewThread.start();     // Start thread
    }


    /***** METHODS *****/

    // Gets called every time screen is redrawn
    protected void doDraw(Canvas canvas) {
        paint.setAlpha(255);  // Make color completely opaque (=255)
        canvas.drawColor(backgroundColor);  // Paints background color

        // Score keeper
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(60);
        final long elapsed = System.nanoTime() - startTime;
        final double seconds = ((double) elapsed / 1000000000);
        canvas.drawText("Score: " + String.valueOf(new DecimalFormat("#").format(seconds / 10)), 50, 100, paintText);

        // If game hasn't started, initialize positions and velocities
        if (!gameStarted) {
            // Set initial positions and velocities
            for (Food food : foods) {
                food.initialize(canvas, rand);
            }
            dexter.initialize(canvas);
        }
        gameStarted = true;

        // Draw bitmaps
        for (Food food : foods) {
            canvas.drawBitmap(food.getImage(), food.getPosX(), food.getPosY(), paint);
        }
        canvas.drawBitmap(dexter.getImage(), dexter.getPosX(), dexter.getPosY(), paint);

        // Collision check
        for (Food food : foods) {
            if (Rect.intersects(food.getFrame(), dexter.getFrame())) {
                food.collide();
                soundPool.play(dogHowl, 1, 1, 0, 0, 1);
                startTime = System.nanoTime();
            }
        }

        // Move bitmaps
        for (Food food : foods) {
            food.move(canvas);
        }
    }


    /***** HELPERS *****/

    // Initialize images
    private void initializeImages(Context context) {
        // Init food
        for (int i = 0; i < 30; i++) {
            Food temp = new Food(context);
            foods.add(temp);
        }

        // Init dog
        dexter = new Dog(context);

    }


    /***** TOUCH HANDLERS *****/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
            {
                touchX = event.getRawX();
                touchY = event.getRawY() - 200;

                if (
                        touchX > dexter.getPosX() &&
                                touchX < dexter.getPosX() + dexter.getImage().getWidth() &&
                                touchY > dexter.getPosY() &&
                                touchY < dexter.getPosY() + dexter.getImage().getHeight()
                        ) {
                    dogSelected = true;
                }
            }
            break;

            case MotionEvent.ACTION_MOVE :
            {
                touchX = event.getRawX() - (dexter.getImage().getWidth() / 2);
                touchY = event.getRawY() - 200 - (dexter.getImage().getHeight() / 2);

                if (dogSelected) {
                    dexter.move((int) touchX, (int) touchY);
                }
            }
            break;

            case MotionEvent.ACTION_UP :
            {
                dogSelected = false;
            }
            break;
        }
        return true;
    }
}
