package com.example.nmq687.radiotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7;
    TextView lbl1, lbl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        if (rb1 != null) {
            rb1.setOnCheckedChangeListener(this);
        }

        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        if (rb2 != null) {
            rb2.setOnCheckedChangeListener(this);
        }

        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        if (rb3 != null) {
            rb3.setOnCheckedChangeListener(this);
        }

        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        if (rb4 != null) {
            rb4.setOnCheckedChangeListener(this);
        }

        rb5 = (RadioButton) findViewById(R.id.radioButton5);
        if (rb5 != null) {
            rb5.setOnCheckedChangeListener(this);
        }

        rb6 = (RadioButton) findViewById(R.id.radioButton6);
        if (rb6 != null) {
            rb6.setOnCheckedChangeListener(this);
        }

        rb7 = (RadioButton) findViewById(R.id.radioButton7);
        if (rb7 != null) {
            rb7.setOnCheckedChangeListener(this);
        }

        lbl1 = (TextView) findViewById(R.id.textView1);
        lbl2 = (TextView) findViewById(R.id.textView2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.radioButton1:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 1");
                }
                break;
            case R.id.radioButton2:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 1");
                }
                break;
            case R.id.radioButton3:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 1");
                }
                break;
            case R.id.radioButton4:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 1");
                }
                break;
            case R.id.radioButton5:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 2");
                }
                break;
            case R.id.radioButton6:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 2");
                }
                break;
            case R.id.radioButton7:
                if (isChecked) {
                    lbl1.setText(buttonView.getText());
                    lbl2.setText("Group 2");
                }
                break;
        }
    }
}
