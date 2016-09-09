package com.example.nmq687.dogdiet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class AnimationArena {
    private Dog dexter;
    private ArrayList<Food> foods;
    private Random randomizer;
    private boolean gameStarted;

    public AnimationArena(Context context) {
        // Initialize foods
        foods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            foods.add(new Food(context));
        }

        // Initialize dog
        dexter = new Dog(context);

        // Initialize randomizer
        randomizer = new Random();
    }

    public void update(Canvas canvas) {
        for (Food food : foods) {
            food.move(canvas);
        }
    }

    public void draw(Canvas canvas) {
        // Wipe canvas
        canvas.drawRGB(0,0,0);

        // If game hasn't started, initialize positions and velocities
        if (!gameStarted) {
            for (Food food : foods) {
                food.initialize(canvas, randomizer);
            }
            dexter.initialize(canvas);
        }
        gameStarted = true;

        // Draw bitmaps
        for (Food food : foods) {
            food.draw(canvas);
        }
        dexter.draw(canvas);
    }

}
