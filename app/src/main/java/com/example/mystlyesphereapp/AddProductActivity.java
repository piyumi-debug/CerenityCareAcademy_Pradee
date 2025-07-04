package com.example.mystlyesphereapp;
import android.database.sqlite.SQLiteDatabase;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    EditText etProductName, etProductPrice, etProductDescription;
    Spinner spinnerCategory;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductDescription = findViewById(R.id.etProductDescription);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSaveProduct);

        // Set up category spinner
        String[] categories = {"Vitamins", "Pain-killers", "Self Care", "Herbal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = etProductName.getText().toString();
            String priceStr = etProductPrice.getText().toString();
            String desc = etProductDescription.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Fill required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            DatabaseHelper dbHelper = new DatabaseHelper(AddProductActivity.this);
            Product product = new Product(name, price, desc, category);
            dbHelper.addProduct(product);  // ✅ Cleaner version
            Toast.makeText(this, "Product Added:\n" + name + "\n$" + price, Toast.LENGTH_SHORT).show();
        });

    }
}