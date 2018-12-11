package com.example.geraldmuhammad.g_shop.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geraldmuhammad.g_shop.Interface.IItemClickListener;
import com.example.geraldmuhammad.g_shop.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    ImageView img_product;
    TextView txt_category;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        img_product = (ImageView)itemView.findViewById(R.id.image_product);
        txt_category = (TextView)itemView.findViewById(R.id.txt_category);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v);
    }
}
