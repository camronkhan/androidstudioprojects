package com.example.nmq687.spinnertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView myLabel;
    Spinner mySpinner;

    public static final String[] items = {"Happy", "Grumpy", "Sneezy", "Sleepy", "Bashful", "Dopey", "Doc", "Depressed", "Sneaky", "Dumb", "Lethargic", "Anemic", "Boisterous", "Neglected"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLabel = (TextView) findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.spinner);

        final ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        myLabel.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
