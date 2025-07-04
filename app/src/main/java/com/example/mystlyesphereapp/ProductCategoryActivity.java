package com.example.mystlyesphereapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductCategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private DatabaseHelper dbHelper;
    private TextView categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        recyclerView = findViewById(R.id.recyclerView);
        categoryTitle = findViewById(R.id.categoryTitle);
        dbHelper = new DatabaseHelper(this);

        String category = getIntent().getStringExtra("category");
        categoryTitle.setText(category);

        ArrayList<Product> products = dbHelper.getProductsByCategory(category);

        adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
