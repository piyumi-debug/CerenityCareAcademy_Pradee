package com.example.mystlyesphereapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Link UI buttons with their IDs
        Button btnAdd = findViewById(R.id.btnAddProduct);
        Button btnView = findViewById(R.id.btnViewProducts);
        Button btnManage = findViewById(R.id.btnManageUsers);

        // Set onClick actions
        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddProductActivity.class));
        });

        btnView.setOnClickListener(v -> {
            startActivity(new Intent(this, ProductListActivity.class));
        });

        btnManage.setOnClickListener(v -> {
            startActivity(new Intent(this, UserListActivity.class));
        });
    }

    // Logout button handler
    public void onLogoutClick(View view) {
        finish(); // Close current activity
        startActivity(new Intent(this, LoginActivity.class));
    }
}
