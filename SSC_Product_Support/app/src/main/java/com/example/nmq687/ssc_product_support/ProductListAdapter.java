package com.example.nmq687.ssc_product_support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.koushikdutta.ion.Ion;
import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<Product> {

    public ProductListAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.product_list_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Product product = getItem(position);

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.product_list_row, parent, false);
        }

        ProductListViewHolder holder = (ProductListViewHolder) row.getTag();

        if (holder == null) {
            holder = new ProductListViewHolder(row);
            row.setTag(holder);
        }

        Ion.with(holder.productImage).load(product.getImage());
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());

        return row;
    }
}

