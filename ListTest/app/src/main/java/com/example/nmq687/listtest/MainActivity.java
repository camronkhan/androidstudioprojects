package com.example.nmq687.listtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String[] items = { "Happy", "Grumpy", "Sneezy", "Sleepy", "Bashful", "Dopey", "Doc" };

    TextView myRedLabel, myBlueLabel;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRedLabel = (TextView) findViewById(R.id.textView);
        myBlueLabel = (TextView) findViewById(R.id.textView2);
        myList = (ListView) findViewById(R.id.listView);

        final ArrayList <String> list = new ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myRedLabel.setText(items[position]);
                myBlueLabel.setText(String.valueOf(myList.getCheckedItemCount()));

                if (myList.getCheckedItemPositions().get(1)) {
                    myRedLabel.setText("Item at location 1 is checked");
                }
            }
        });
    }
}
