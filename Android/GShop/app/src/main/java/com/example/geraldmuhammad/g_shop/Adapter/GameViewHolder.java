package com.example.geraldmuhammad.g_shop.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geraldmuhammad.g_shop.Interface.IItemClickListener;
import com.example.geraldmuhammad.g_shop.R;

public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView img_product;
    TextView txt_game_name,txt_price;

    IItemClickListener itemClickListener;

    ImageView btn_add_to_cart,btn_favorites;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);

        img_product = (ImageView)itemView.findViewById(R.id.image_product);
        txt_game_name = (TextView)itemView.findViewById(R.id.txt_game_name);
        txt_price = (TextView)itemView.findViewById(R.id.txt_price);
        btn_add_to_cart = (ImageView)itemView.findViewById(R.id.btn_add_cart);
        btn_favorites = (ImageView)itemView.findViewById(R.id.btn_favorite);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    itemClickListener.OnClick(v);
    }
}
