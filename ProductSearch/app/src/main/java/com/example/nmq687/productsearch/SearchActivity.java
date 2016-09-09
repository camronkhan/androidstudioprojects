package com.example.nmq687.productsearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class SearchActivity extends AppCompatActivity {

    ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set reference to the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set reference to the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    // Handle the intent by passing the search query to getProductList()
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getProductList(query);
        } else {
            getProductList("");
        }
    }

    // Given the query string, return JSON data containing the product list
    private void getProductList(String query) {
        String queryParam = "?name=" + query;
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        // Handle JSON data returned from REST client
        JsonHttpResponseHandler jsonResHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ArrayList<Product> productArray = new ArrayList<>();
                final ProductAdapter productAdapter = new ProductAdapter(SearchActivity.this, productArray);
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        try {
                            productAdapter.add(new Product(data.getJSONObject(i)));
                        } catch (JSONException e ) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Bind ListView to ProductAdapter
                productList = (ListView) findViewById(android.R.id.list);
                productList.setAdapter(productAdapter);

                // Set listener for product selections
                productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Product product = productAdapter.getItem(position);
                        Log.d("floydexter", "Search: " + product.getName());
                        getProductDetail(product);
                    }
                });
            }
        };
        // Send request to via the REST client to get product list
        RestClient.get(SearchActivity.this, queryParam, headers.toArray(new Header[headers.size()]), null, jsonResHandler);
    }

    // Send intent to display product details
    private void getProductDetail(Product product) {
        // Get product attributes
        String productId = product.getId();
        String productName = product.getName();
        String productDescription = product.getDescription();
        String productImage = product.getImage();
        String productSource = product.getSource();
        String companyName = product.getCompany();
        String companyWebsite = product.getCompanyWebsite();

        // Create intent to display product details
        Intent productDetails = new Intent(this, DetailActivity.class);

        // Pass data to detail activity
        productDetails.putExtra("productId", productId);
        productDetails.putExtra("productName", productName);
        productDetails.putExtra("productDescription", productDescription);
        productDetails.putExtra("productImage", productImage);
        productDetails.putExtra("productSource", productSource);
        productDetails.putExtra("companyName", companyName);
        productDetails.putExtra("companyWebsite", companyWebsite);

        // Launch detail activity
        startActivity(productDetails);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchIcon).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
