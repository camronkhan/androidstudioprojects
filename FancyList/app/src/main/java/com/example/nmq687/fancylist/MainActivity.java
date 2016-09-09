package com.example.nmq687.fancylist;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends ListActivity {

    TextView selection;

    private static final String[] faction = {"federation","ferengi","federation","klingon","romulan","klingon","romulan","ferengi","ferengi","romulan","romulan","klingon","federation", "federation","romulan","federation","romulan","klingon","klingon","federation","klingon"};
    private static final String[] name = {"Kirk","Bok","Spock","Warf","Bochra","Martok","Almak","Brunt","Dr. Farek","Koval","Bochra","Bo'rak","Scottie","Cartwright","Nanclus","Data","Kimara Cretak","Duras","Atul","Picard","Azetbur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.textView);
        setListAdapter(new IconAdapter());
    }

    public void onListItemClick(ListView parent, View view, int position, long id) {
        selection.setText(faction[position]);
    }

    class IconAdapter extends ArrayAdapter<String> {
        IconAdapter() {
            super(MainActivity.this, R.layout.row, name);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }

            ViewHolder holder = (ViewHolder) row.getTag();

            if (holder == null) {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }

            holder.label.setText(name[position]);

            switch(faction[position]) {
                case "klingon":
                    holder.label.setTextColor(Color.RED);
                    holder.icon.setImageResource(R.drawable.klingon);
                    break;
                case "federation":
                    holder.label.setTextColor(Color.BLUE);
                    holder.icon.setImageResource(R.drawable.federation);
                    break;
                case "romulan":
                    holder.label.setTextColor(Color.GRAY);
                    holder.icon.setImageResource(R.drawable.romulan);
                    break;
                case "ferengi":
                    holder.label.setTextColor(Color.GREEN);
                    holder.icon.setImageResource(R.drawable.ferengi);
                    break;
                default:
                    holder.label.setTextColor(Color.BLACK);
            }

            return row;
        }
    }
}
