package com.example.nmq687.jsontest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.nmq687.jsontest.R;
import com.example.nmq687.jsontest.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private static class ViewHolder {
        TextView product;
        TextView description;
        TextView image;
        TextView company;
        TextView companyWebsite;
        TextView source;
    }

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.product_list, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_list, parent, false);

            viewHolder.product = (TextView) convertView.findViewById(R.id.value_product_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.value_product_description);
            viewHolder.company = (TextView) convertView.findViewById(R.id.value_product_company);
            viewHolder.source = (TextView) convertView.findViewById(R.id.value_product_source);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.product.setText(product.getName());
        viewHolder.description.setText(product.getDescription());
        //viewHolder.image.setText(product.getImage());
        viewHolder.company.setText(product.getCompany());
        //viewHolder.companyWebsite.setText(product.getCompanyWebsite());
        viewHolder.source.setText(product.getSource());

        return convertView;
    }
}
