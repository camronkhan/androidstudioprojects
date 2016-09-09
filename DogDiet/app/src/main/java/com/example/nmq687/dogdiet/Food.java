package com.example.nmq687.dogdiet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Food {
    private final int POSITIVE_VELOCITY = 10;
    private final int NEGATIVE_VELOCITY = -10;
    private final int REVERSE = -1;
    private static int nextId = 0;
    private int id;
    private int posX;
    private int posY;
    private int velX;
    private int velY;
    private Context context;
    private Bitmap image;
    private Rect frame;

    /*
        CONSTRUCTORS
     */
    public Food(Context context) {
        id = nextId++;
        posX = 0;
        posY = 0;
        velX = 0;
        velY = 0;
        this.context = context;
        image = null;
        frame = null;
    }


    /*
        METHODS
     */

    // Initialize positions and velocities
    public void initialize(Canvas canvas, Random randomizer) {
        // Constants
        final int PIZZA = 0;
        final int HOTDOG = 1;
        final int SQUIRREL = 2;
        final int BUNNY = 3;

        // Set position
        posX = randomizer.nextInt(canvas.getWidth());
        posY = randomizer.nextInt(canvas.getHeight());

        // Set velocity
        if (randomizer.nextInt(2) == 0) {
            velX = POSITIVE_VELOCITY;
        } else {
            velX = NEGATIVE_VELOCITY;
        }
        if (randomizer.nextInt(2) == 0) {
            velY = POSITIVE_VELOCITY;
        } else {
            velY = NEGATIVE_VELOCITY;
        }

        // Set image
        int img = randomizer.nextInt(4);
        switch (img) {
            case PIZZA :
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.pizza);
                break;
            case HOTDOG :
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.hotdog);
                break;
            case SQUIRREL :
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.squirrel);
                break;
            case BUNNY :
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.bunny);
                break;
        }

        // Set frame
        frame = new Rect(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }

    // Move item
    public void move(Canvas canvas) {
        // Update image position
        posX += velX;
        posY += velY;

        // Update frame position
        frame = new Rect(posX, posY, posX + image.getWidth(), posY + image.getHeight());

        // Change direction if wall collision
        if (posX < 0 || posX > canvas.getWidth()) {
            velX *= REVERSE;
        }
        if (posY < 0 || posY > canvas.getHeight()) {
            velY *= REVERSE;
        }
    }

    // Collision
    public void collide() {
        velX *= REVERSE;
        velY *= REVERSE;
    }

    // Draw
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(image, posX, posY, paint);
    }


    /*
        GETTERS & SETTERS
     */

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int p) {
        posX = p;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int p) {
        posY = p;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int v) {
        velX = v;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int v) {
        velY = v;
    }

    public Bitmap getImage() {
        return image;
    }

    public Rect getFrame() {
        return frame;
    }
}
