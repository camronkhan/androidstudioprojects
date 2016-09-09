package com.example.nmq687.sqlpersistence;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
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

    }

    public void setObjectState() {

    }
}
