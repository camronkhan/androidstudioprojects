package com.example.nmq687.randomcolortest;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView myView;
    TextView labelRed, labelGreen, labelBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (ImageView) findViewById(R.id.imageView);
        labelRed = (TextView) findViewById(R.id.labelRed);
        labelGreen = (TextView) findViewById(R.id.labelGreen);
        labelBlue = (TextView) findViewById(R.id.labelBlue);

        myView.setBackgroundColor(Color.argb(255, 75, 0, 100));
    }

    public void buttonPressed(View view) {
        Random rnd = new Random();
        int redValue = rnd.nextInt(255);
        int greenValue = rnd.nextInt(255);
        int blueValue = rnd.nextInt(255);

        myView.setBackgroundColor(Color.argb(255, redValue, greenValue, blueValue));

        labelRed.setText(String.valueOf(redValue));
        labelGreen.setText(String.valueOf(greenValue));
        labelBlue.setText(String.valueOf(blueValue));
    }
}
