package com.example.nmq687.ssc_product_support;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListViewHolder {
    ImageView productImage;
    TextView productName;
    TextView productDescription;

    ProductListViewHolder(View base) {
        this.productImage = (ImageView) base.findViewById(R.id.productImage);
        this.productName = (TextView) base.findViewById(R.id.productName);
        this.productDescription = (TextView) base.findViewById(R.id.productDescription);
    }
}
