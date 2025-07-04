package com.example.mystlyesphereapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShopFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout womenCategory = view.findViewById(R.id.womenCategory);
        LinearLayout menCategory = view.findViewById(R.id.painkillersCategory);
        LinearLayout kidsCategory = view.findViewById(R.id.personalcareCategory);
        LinearLayout accessoriesCategory = view.findViewById(R.id.herbalCategory);

        womenCategory.setOnClickListener(v -> openCategory("Vitamins"));
        menCategory.setOnClickListener(v -> openCategory("Pain Relief"));
        kidsCategory.setOnClickListener(v -> openCategory("Personal Care"));
        accessoriesCategory.setOnClickListener(v -> openCategory("Herbal"));
    }

    private void openCategory(String category) {
        Intent intent = new Intent(getActivity(), ProductCategoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
