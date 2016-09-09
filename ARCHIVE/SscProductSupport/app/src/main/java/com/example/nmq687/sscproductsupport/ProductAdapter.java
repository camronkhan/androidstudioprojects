package com.example.nmq687.sscproductsupport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, ArrayList<Product> products) {
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

        ViewHolder holder = (ViewHolder) row.getTag();

        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        Ion.with(holder.productImage).load(product.getImage());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());

        return row;
    }
}
