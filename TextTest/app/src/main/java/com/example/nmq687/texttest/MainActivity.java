package com.example.nmq687.texttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText field1, field2, field3, field4;
    TextView label1, label2, label3, label4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = (EditText) findViewById(R.id.editText1);
        field2 = (EditText) findViewById(R.id.editText2);
        field3 = (EditText) findViewById(R.id.editText3);
        field4 = (EditText) findViewById(R.id.editText4);

        label1 = (TextView) findViewById(R.id.textView2);
        label2 = (TextView) findViewById(R.id.textView3);
        label3 = (TextView) findViewById(R.id.textView4);
        label4 = (TextView) findViewById(R.id.textView5);

        field1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    label1.setText(field1.getText());
                }
            }
        });

        field2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    label2.setText(field2.getText());
                }
            }
        });

        field3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    label3.setText(field3.getText());
                }
            }
        });

        field4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    label4.setText(field4.getText());
                }
            }
        });
    }

    public void buttonPressed(View view) {
        label1.setText(field1.getText());
        label2.setText(field2.getText());
        label3.setText(field3.getText());
        label4.setText(field4.getText());
    }
}
