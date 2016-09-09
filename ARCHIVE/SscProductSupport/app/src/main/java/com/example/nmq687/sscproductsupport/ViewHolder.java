package com.example.nmq687.sscproductsupport;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    ImageView productImage;
    TextView productName;
    TextView productDescription;
    TextView productCompany;

    ViewHolder(View base) {
        this.productImage = (ImageView) base.findViewById(R.id.productImage);
        this.productName = (TextView) base.findViewById(R.id.productName);
        this.productDescription = (TextView) base.findViewById(R.id.productDescription);
    }
}
