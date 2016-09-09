package com.example.nmq687.dogdiet;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private SurfaceHolder holder;
    private AnimationArena arena;
    private boolean isRunning;

    public GameThread(SurfaceHolder holder, Context context) {
        isRunning = true;
        this.holder = holder;
        arena = new AnimationArena(context);
    }

    public void run() {
        try {
            while (isRunning) {
                Canvas canvas = holder.lockCanvas();
                arena.update(canvas);
                arena.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void endGame() {
        isRunning = false;
    }
}
