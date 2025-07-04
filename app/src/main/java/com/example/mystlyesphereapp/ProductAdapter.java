package com.example.mystlyesphereapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> productList;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvPrice, tvDescription, tvCategory;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            tvDescription = itemView.findViewById(R.id.tvProductDescription);
            tvCategory = itemView.findViewById(R.id.tvProductCategory);
        }
    }

    public ProductAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_card, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("Rs. " + product.getPrice());
        holder.tvDescription.setText(product.getDescription());
        holder.tvCategory.setText(product.getCategory());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
