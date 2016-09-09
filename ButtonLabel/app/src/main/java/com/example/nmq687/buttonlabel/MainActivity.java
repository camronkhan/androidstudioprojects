package com.example.nmq687.buttonlabel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView label1;
    TextView label2;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = (TextView) findViewById(R.id.hello);
        label2 = (TextView) findViewById(R.id.textView);
        myButton = (Button) findViewById(R.id.button1);


        label1.setText("Floyd & Dexter");
        label2.setText("I was just overwritten at onCreate time");
    }

    public void button1Pressed (View view) {
        label2.setText("You pressed the button");
        myButton.setText("You friggin pressed me");
    }
}
