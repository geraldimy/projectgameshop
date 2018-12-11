package com.example.geraldmuhammad.g_shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geraldmuhammad.g_shop.GameActivity;
import com.example.geraldmuhammad.g_shop.Interface.IItemClickListener;
import com.example.geraldmuhammad.g_shop.Model.Category;
import com.example.geraldmuhammad.g_shop.R;
import com.example.geraldmuhammad.g_shop.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.category_layout, null);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        Picasso.with(context)
                .load(categories.get(position).Link)
                .into(holder.img_product);

        holder.txt_category.setText(categories.get(position).Name);

        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void OnClick(View v) {
                Common.currentCategory = categories.get(position);
                context.startActivity(new Intent(context, GameActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
