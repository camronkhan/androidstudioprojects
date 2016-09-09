package com.example.nmq687.switchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch switch1, switch2;
    TextView label1, label2;
    ImageView switchImage1, switchImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = (Switch) findViewById(R.id.switch1);
        if (switch1 != null) {
            switch1.setOnCheckedChangeListener(this);
        }

        switch2 = (Switch) findViewById(R.id.switch2);
        if (switch2 != null) {
            switch2.setOnCheckedChangeListener(this);
        }

        label1 = (TextView) findViewById(R.id.textView1);
        label2 = (TextView) findViewById(R.id.textView2);

        switchImage1 = (ImageView) findViewById(R.id.imageView1);
        switchImage2 = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        label1.setText(buttonView.getText());
        label2.setText(isChecked ? "on" : "off");

        if (buttonView.getId() == R.id.switch1 && isChecked) {
            switchImage1.setVisibility(View.VISIBLE);
        }
        if (buttonView.getId() == R.id.switch1 && !isChecked) {
            switchImage1.setVisibility(View.INVISIBLE);
        }

        if (buttonView.getId() == R.id.switch2 && isChecked) {
            switchImage2.setVisibility(View.VISIBLE);
        }
        if (buttonView.getId() == R.id.switch2 && !isChecked) {
            switchImage2.setVisibility(View.INVISIBLE);
        }
    }
}
