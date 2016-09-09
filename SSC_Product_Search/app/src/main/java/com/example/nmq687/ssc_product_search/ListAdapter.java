package com.example.nmq687.ssc_product_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.koushikdutta.ion.Ion;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Product> {

    public ListAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Product product = getItem(position);

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.row, parent, false);
        }

        ListViewHolder holder = (ListViewHolder) row.getTag();

        if (holder == null) {
            holder = new ListViewHolder(row);
            row.setTag(holder);
        }

        Ion.with(holder.productImage).load(product.getImage());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());

        return row;
    }
}

