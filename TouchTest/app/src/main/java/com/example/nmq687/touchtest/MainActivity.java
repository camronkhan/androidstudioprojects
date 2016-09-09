package com.example.nmq687.touchtest;

import android.media.ImageReader;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView myImage;
    TextView label1, label2;
    float x=0, y=0;
    boolean imageSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = (ImageView) this.findViewById(R.id.imageView);
        label1 = (TextView) this.findViewById(R.id.textView);
        label2 = (TextView) this.findViewById(R.id.textView2);
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                x = event.getRawX();
                y = event.getRawY() - 200;

                if (x > myImage.getX() &&
                    x < (myImage.getX() + myImage.getWidth()) &&
                    y > myImage.getY() &&
                    y < (myImage.getY() + myImage.getHeight()))
                {
                    imageSelected = true;
                }
            }
            break;

            case MotionEvent.ACTION_MOVE:
            {
                x = event.getRawX() - (myImage.getWidth()/2);
                y = event.getRawY() - 200 - (myImage.getHeight()/2);

                label1.setText(String.valueOf(event.getRawX()));
                label2.setText(String.valueOf(event.getRawY()));

                if (imageSelected) {
                    myImage.setX(x);
                    myImage.setY(y);
                }
            }
            break;

            case MotionEvent.ACTION_UP:
            {
                imageSelected = false;
            }
            break;
        }

        return true;
    }
}
