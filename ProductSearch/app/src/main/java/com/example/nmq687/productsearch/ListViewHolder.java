package com.example.nmq687.productsearch;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewHolder {
    ImageView productImage;
    TextView productName;
    TextView productDescription;

    ListViewHolder(View base) {
        this.productImage = (ImageView) base.findViewById(R.id.productImage);
        this.productName = (TextView) base.findViewById(R.id.productName);
        this.productDescription = (TextView) base.findViewById(R.id.productDescription);
    }
}