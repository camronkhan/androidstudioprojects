package com.example.nmq687.basicwidgets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    Button button1;
    CheckBox checkBox1;
    Switch switch1;
    RadioButton radioButton1, radioButton2, radioButton3;
    SeekBar seekBar1;
    EditText textBox1;
    TextView buttonLabel, checkBoxLabel, switchLabel, radioButtonLabel, sliderDynamicLabel, textBoxLabel;
    int sliderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Labels
        buttonLabel = (TextView) findViewById(R.id.buttonLabel);
        checkBoxLabel = (TextView) findViewById(R.id.checkBoxLabel);
        switchLabel = (TextView) findViewById(R.id.switchLabel);
        radioButtonLabel = (TextView) findViewById(R.id.radioButtonLabel);
        sliderDynamicLabel = (TextView) findViewById(R.id.sliderDynamicLabel);
        textBoxLabel = (TextView) findViewById(R.id.textBoxLabel);

        // Bind Button
        button1 = (Button) findViewById(R.id.button1);

        // Check Box Event Listener
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox1 != null) {
            checkBox1.setOnCheckedChangeListener(this);
        }

        // Switch Event Listener
        switch1 = (Switch) findViewById(R.id.switch1);
        if (switch1 != null) {
            switch1.setOnCheckedChangeListener(this);
        }

        // Radio Button Event Listeners
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        if (radioButton1 != null) {
            radioButton1.setOnCheckedChangeListener(this);
        }
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        if (radioButton2 != null) {
            radioButton2.setOnCheckedChangeListener(this);
        }
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        if (radioButton3 != null) {
            radioButton3.setOnCheckedChangeListener(this);
        }

        // Slider Event Listener
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        if (seekBar1 != null) {
            seekBar1.setOnSeekBarChangeListener(this);
        }

        // Initialize Slider
        sliderProgress = 0;

        // Text Box Event Listener
        textBox1 = (EditText) findViewById(R.id.textBox1);
        if (textBox1 != null) {
            textBox1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        textBoxLabel.setText(textBox1.getText());
                    }
                }
            });
        }
    }

    public void button1Pressed(View view) {
        buttonLabel.setText("Button was pressed");
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBox1:
                if (isChecked) {
                    checkBoxLabel.setText("CheckBox is checked");
                }
                if (!isChecked) {
                    checkBoxLabel.setText("CheckBox is unchecked");
                }
                break;
            case R.id.switch1:
                if (isChecked) {
                    switchLabel.setText("Switch is on");
                }
                if (!isChecked) {
                    switchLabel.setText("Switch is off");
                }
                break;
            case R.id.radioButton1:
                if (isChecked) {
                    radioButtonLabel.setText("RadioButton 1 is checked");
                }
                break;
            case R.id.radioButton2:
                if (isChecked) {
                    radioButtonLabel.setText("RadioButton 2 is checked");
                }
                break;
            case R.id.radioButton3:
                if (isChecked) {
                    radioButtonLabel.setText("RadioButton 3 is checked");
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        sliderProgress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        return;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        sliderDynamicLabel.setText(String.valueOf(sliderProgress) + "%");
    }
}
