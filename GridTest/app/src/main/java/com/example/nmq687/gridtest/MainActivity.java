package com.example.nmq687.gridtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView myLabel;
    GridView myGrid;

    private static final String[] items = { "Happy", "Grumpy", "Sneezy", "Sleepy", "Bashful", "Dopey", "Doc" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLabel = (TextView) findViewById(R.id.textView);
        myGrid = (GridView) findViewById(R.id.gridView);

        /*final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }*/

        //final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        //myGrid.setAdapter(myAdapter);
        myGrid.setAdapter(new ImageAdapter(this));


        myGrid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myLabel.setText(items[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ImageView imageView;
            if (view == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(200,200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            } else {
                imageView = (ImageView) view;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        private Integer[] mThumbIds = { R.drawable.happy, R.drawable.bashful, R.drawable.doc, R.drawable.dopey, R.drawable.grumpy, R.drawable.sleepy, R.drawable.sneezy };
    }
}
