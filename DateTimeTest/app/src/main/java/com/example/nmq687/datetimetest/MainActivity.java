package com.example.nmq687.datetimetest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    TextView lbl1, lbl2, lbl3, lbl4, lbl5;
    DateFormat fmtDateandTime = DateFormat.getDateTimeInstance();
    Chronometer myChrono;
    long chronoBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbl1 = (TextView) findViewById(R.id.textView);
        lbl2 = (TextView) findViewById(R.id.textView2);
        lbl3 = (TextView) findViewById(R.id.textView3);
        lbl4 = (TextView) findViewById(R.id.textView4);
        lbl5 = (TextView) findViewById(R.id.textView5);

        myChrono = (Chronometer) findViewById(R.id.chronometer);

        updateInfo();
    }

    public void updateInfo() {
        lbl1.setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
        lbl2.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
        lbl3.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        lbl4.setText((String.valueOf(cal.get(Calendar.YEAR))));
        lbl5.setText(fmtDateandTime.format(cal.getTime()));
    }

    public void changeTime(View view) {
        new TimePickerDialog(this, t, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            updateInfo();
        }
    };

    public void changeDate(View view) {
        new DatePickerDialog(this, d, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateInfo();
        }
    };

    public void startCR(View view) {
        myChrono.start();
    }

    public void stopCR(View view) {
        myChrono.stop();
    }

    public void resetCR(View view) {
        myChrono.setBase(SystemClock.elapsedRealtime());
    }
}
