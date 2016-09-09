package com.example.nmq687.autocompletetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView myTextbox;
    //private static final String[] myArray = {"Camron", "Toni", "Frank", "Stephanie", "Jenna", "Bob", "Val" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextbox = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        String[] myArray = getResources().getStringArray(R.array.word_dict);

        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < myArray.length; i++) {
            list.add(myArray[i]);
        }

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, list);
        myTextbox.setThreshold(2);
        myTextbox.setAdapter(myAdapter);
    }
}
