package com.example.nmq687.apptab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClockTab extends Fragment {

    TextView clockLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View clockInflater = inflater.inflate(R.layout.clock_gui, null, false);
        clockLabel = (TextView) clockInflater.findViewById(R.id.textClock);
        clockLabel.setText("Start");
        return clockInflater;
    }
}
