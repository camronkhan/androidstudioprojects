package com.example.nmq687.productsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set references to views
        ImageView pImage = (ImageView) findViewById(R.id.productDetailImage);
        TextView pName = (TextView) findViewById(R.id.productDetailName);
        TextView pDescription = (TextView) findViewById(R.id.productDetailDescription);
        TextView pSource = (TextView) findViewById(R.id.productDetailSource);
        TextView cName = (TextView) findViewById(R.id.productDetailCompany);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        //String productId = intent.getStringExtra("productId");
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        String productImage = intent.getStringExtra("productImage");
        String productSource = intent.getStringExtra("productSource");
        String companyName = intent.getStringExtra("companyName");
        //String companyWebsite = intent.getStringExtra("companyWebsite");

        // Set image and text
        Ion.with(pImage).placeholder(R.drawable.msi_logo_300x300).load(productImage);
        pName.setText(productName);
        pDescription.setText(productDescription);
        pSource.setText(productSource);
        cName.setText(companyName);
    }
}
