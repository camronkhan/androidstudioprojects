package com.example.nmq687.doggydiet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Dog {
    private int posX;
    private int posY;
    private Context context;
    private Bitmap image;
    private Rect frame;

    public Dog(Context context) {
        posX = 0;
        posY = 0;
        this.context = context;
        image = null;
        frame = null;
    }


    /*
        METHODS
     */

    public void initialize(Canvas canvas) {
        // Initial pos is center of screen
        posX = canvas.getWidth() / 2;
        posY = canvas.getHeight() / 2;

        // Set image
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.dog);

        // Set frame
        frame = new Rect(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }

    public void move(int x, int y) {
        // Update position
        posX = x;
        posY = y;

        // Update frame
        frame = new Rect(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }


    /*
        GETTERS & SETTERS
     */

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

    public Bitmap getImage() {
        return image;
    }

    public Rect getFrame() {
        return frame;
    }
}
