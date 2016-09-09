package com.example.nmq687.spritecanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    GameView gv;
    Paint drawPaint = new Paint();
    int backgroundColor = Color.RED;
    Bitmap graphic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        gv = new GameView(this);
        this.setContentView(gv);

        graphic1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gv.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gv.resume();
    }

    public class GameView extends SurfaceView implements Runnable{

        Thread ViewThread = null;
        SurfaceHolder holder;
        boolean threadOK = true;

        public GameView(Context context) {
            super(context);
            holder = this.getHolder();
        }

        @Override
        public void run() {
            while(threadOK == true) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }
                Canvas gameCanvas = holder.lockCanvas();
                myDraw(gameCanvas);
                holder.unlockCanvasAndPost(gameCanvas);
            }
        }

        protected void myDraw(Canvas canvas) {
            drawPaint.setAlpha(255);
            canvas.drawColor(backgroundColor);
            canvas.drawBitmap(graphic1, 200, 200, drawPaint);
        }

        public void pause() {
            threadOK = false;
            while(true) {
                try {
                    ViewThread.join();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ViewThread = null;
        }

        public void resume() {
            threadOK = true;
            ViewThread = new Thread(this);
            ViewThread.start();
        }
    }
}
