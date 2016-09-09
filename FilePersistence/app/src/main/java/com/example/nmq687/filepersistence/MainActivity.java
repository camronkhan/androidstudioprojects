package com.example.nmq687.filepersistence;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    String filename = "persist";
    CheckBox cb1, cb2, cb3;
    RadioButton rb1, rb2, rb3;
    EditText tb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox) this.findViewById(R.id.checkBox);
        cb2 = (CheckBox) this.findViewById(R.id.checkBox2);
        cb3 = (CheckBox) this.findViewById(R.id.checkBox3);
        rb1 = (RadioButton) this.findViewById(R.id.radioButton);
        rb2 = (RadioButton) this.findViewById(R.id.radioButton2);
        rb3 = (RadioButton) this.findViewById(R.id.radioButton3);
        tb1 = (EditText) this.findViewById(R.id.editText);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);

        getObjectState();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setObjectState();
    }

    public void saveText(View view) {
        setObjectState();
    }

    public void getObjectState() {
        File myFile = getFileStreamPath(filename);

        if (myFile.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(myFile));

                cb1.setChecked(Boolean.valueOf(in.readLine()));
                cb2.setChecked(Boolean.valueOf(in.readLine()));
                cb3.setChecked(Boolean.valueOf(in.readLine()));
                rb1.setChecked(Boolean.valueOf(in.readLine()));
                rb2.setChecked(Boolean.valueOf(in.readLine()));
                rb3.setChecked(Boolean.valueOf(in.readLine()));
                tb1.setText(in.readLine());

                in.close();

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setObjectState() {
        String cb1Str = String.valueOf(cb1.isChecked()) + "\n";
        String cb2Str = String.valueOf(cb2.isChecked()) + "\n";
        String cb3Str = String.valueOf(cb3.isChecked()) + "\n";
        String rb1Str = String.valueOf(rb1.isChecked()) + "\n";
        String rb2Str = String.valueOf(rb2.isChecked()) + "\n";
        String rb3Str = String.valueOf(rb3.isChecked()) + "\n";
        String tb1Str = tb1.getText() + "\n";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

            outputStream.write(cb1Str.getBytes());
            outputStream.write(cb2Str.getBytes());
            outputStream.write(cb3Str.getBytes());
            outputStream.write(rb1Str.getBytes());
            outputStream.write(rb2Str.getBytes());
            outputStream.write(rb3Str.getBytes());
            outputStream.write(tb1Str.getBytes());

            outputStream.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
