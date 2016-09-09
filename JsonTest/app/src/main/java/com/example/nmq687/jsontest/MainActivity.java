package com.example.nmq687.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.nmq687.jsontest.adapters.ProductAdapter;
import com.example.nmq687.jsontest.clients.ProductRestClient;
import com.example.nmq687.jsontest.models.Product;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends AppCompatActivity {

    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getProducts();
    }

    private void getProducts() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        ProductRestClient.get(MainActivity.this, "", headers.toArray(new Header[headers.size()]), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ArrayList<Product> productArray = new ArrayList<>();
                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, productArray);

                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        try {
                            productAdapter.add(new Product(data.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                productList = (ListView) findViewById(R.id.list_products);
                productList.setAdapter(productAdapter);
            }
        });
    }
}
