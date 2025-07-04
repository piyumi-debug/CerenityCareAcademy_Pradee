package com.example.mystlyesphereapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WomenClothingActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_clothing);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        dbHelper = new DatabaseHelper(this);
        container = findViewById(R.id.productContainer);

        // Load all "Women" category products
        ArrayList<Product> womenProducts = dbHelper.getProductsByCategory("Women");

        // Display products
        for (Product product : womenProducts) {
            TextView tv = new TextView(this);
            tv.setText(product.getName() + "\n$" + product.getPrice());
            tv.setTextSize(18);
            tv.setPadding(16, 16, 16, 16);
            tv.setBackgroundResource(android.R.drawable.gallery_thumb);

            tv.setOnClickListener(v -> {
                // You can open detail screen here
            });

            container.addView(tv);
        }
    }
}