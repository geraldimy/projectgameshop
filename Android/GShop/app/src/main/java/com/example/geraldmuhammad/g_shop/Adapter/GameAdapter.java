package com.example.geraldmuhammad.g_shop.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.geraldmuhammad.g_shop.Database.ModelDB.Cart;
import com.example.geraldmuhammad.g_shop.Database.ModelDB.Favorite;
import com.example.geraldmuhammad.g_shop.Interface.IItemClickListener;
import com.example.geraldmuhammad.g_shop.Model.Game;
import com.example.geraldmuhammad.g_shop.R;
import com.example.geraldmuhammad.g_shop.Utils.Common;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    Context context;
    List<Game> gameList;

    public GameAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.game_item_layout , null);
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GameViewHolder holder, final int position) {

        holder.txt_price.setText(new StringBuilder("$").append(gameList.get(position).Price));
        holder.txt_game_name.setText(gameList.get(position).Name);

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(position);
            }
        });

        Picasso.with(context)
                .load(gameList.get(position).Link)
                .into(holder.img_product);

        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void OnClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        if(Common.favoriteRepository.isFavorite(Integer.parseInt(gameList.get(position).ID))==1)
                holder.btn_favorites.setImageResource(R.drawable.ic_favorite_white_24dp);
        else
                holder.btn_favorites.setImageResource(R.drawable.ic_favorite_border_white_24dp);

        holder.btn_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.favoriteRepository.isFavorite(Integer.parseInt(gameList.get(position).ID))!=1)
                {
                    addOrRemoveFavorite(gameList.get(position),true);
                    holder.btn_favorites.setImageResource(R.drawable.ic_favorite_white_24dp);
                }
                else
                 {
                     addOrRemoveFavorite(gameList.get(position),false);
                     holder.btn_favorites.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                 }
            }
        });
    }

    private void addOrRemoveFavorite(Game game, boolean isAdd) {
        Favorite favorite = new Favorite();
        favorite.id = game.ID;
        favorite.link = game.Link;
        favorite.name = game.Name;
        favorite.price = game.Price;
        favorite.categoryId = game.CategoryId;

        if(isAdd)
            Common.favoriteRepository.insertFav(favorite);
        else
            Common.favoriteRepository.delete(favorite);
    }


    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout,null);

        ImageView img_product_dialog = (ImageView)itemView.findViewById(R.id.img_cart_product);
        final ElegantNumberButton txt_count=(ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog = (TextView)itemView.findViewById(R .id.txt_cart_product_name);

        EditText edt_comment = (EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_editionstd = (RadioButton)itemView.findViewById(R.id.rdi_editionstd);
        RadioButton rdi_editiongold = (RadioButton)itemView.findViewById(R.id.rdi_editiongold);
        RadioButton rdi_editionpre = (RadioButton)itemView.findViewById(R.id.rdi_editionpre);

        rdi_editionstd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.edition="Standard";
            }
        });

        rdi_editiongold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.edition="Gold";
            }
        });

        rdi_editionpre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.edition="Premium";
            }
        });

        RadioButton rdi_platformpc = (RadioButton)itemView.findViewById(R.id.rdi_platformpc);
        RadioButton rdi_platformps = (RadioButton)itemView.findViewById(R.id.rdi_platformps);
        RadioButton rdi_platformxbox = (RadioButton)itemView.findViewById(R.id.rdi_platformxbox);

        rdi_platformpc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.platf="PC";
            }
        });

        rdi_platformps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.platf="PS4";
            }
        });

        rdi_platformxbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.platf="XBOX";
            }
        });

        Picasso.with(context)
                .load(gameList.get(position).Link)
                .into(img_product_dialog);
        txt_product_dialog.setText(gameList.get(position).Name);

        builder.setView(itemView);
        builder.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((Common.platf != "PC") && (Common.platf != "PS4") && (Common.platf != "XBOX"))
                {
                    Toast.makeText(context,"Please Choose Your Platform Game", Toast.LENGTH_SHORT).show();
                    return;
                }

                if((Common.edition != "Standard") && (Common.edition != "Gold") && (Common.edition != "Premium"))
                {
                    Toast.makeText(context,"Please Choose Your Game Edition", Toast.LENGTH_SHORT).show();
                    return;
                }


                showConfirmDialog(position,txt_count.getNumber());
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showConfirmDialog(final int position, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.confirm_add_to_cart_layout,null);

        ImageView img_product_dialog = (ImageView)itemView.findViewById(R.id.img_product);
        final TextView txt_product_dialog = (TextView)itemView.findViewById(R .id.txt_cart_product_name);
        final TextView txt_product_price = (TextView)itemView.findViewById(R .id.txt_cart_product_price);
        TextView txt_platform = (TextView)itemView.findViewById(R .id.txt_platform);
        TextView txt_edition = (TextView)itemView.findViewById(R .id.txt_edition);


        Picasso.with(context).load(gameList.get(position).Link).into(img_product_dialog);
        txt_product_dialog.setText(new StringBuilder(gameList.get(position).Name).append(" x")
        .append(number).toString());



        txt_platform.setText(new StringBuilder("Platform: ").append(Common.platf).toString());

        txt_edition.setText(new StringBuilder("Edition: ").append(Common.edition).toString());



        double  price = (Double.parseDouble(gameList.get(position).Price)* Double.parseDouble(number));

        if(Common.edition == "Gold")
            price+=(13.00 * Double.parseDouble(number));

        if(Common.edition == "Premium")
            price+=(30.00 * Double.parseDouble(number));


        final double finalPrice = Math.round(price);

        txt_product_price.setText(new StringBuilder("$ ").append(finalPrice));

        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                try {
                    Cart cartItem = new Cart();
                    cartItem.name = gameList.get(position).Name;
                    cartItem.amount = Integer.parseInt(number);
                    cartItem.platf = Common.platf;
                    cartItem.edition = Common.edition;
                    cartItem.price = finalPrice;
                    cartItem.link = gameList.get(position).Link;

                    Common.cartRepository.insertToCart(cartItem);

                    Log.d("GER_DEBUG", new Gson().toJson(cartItem));
                    Toast.makeText(context, "Save To Cart", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(itemView);
        builder.show();


    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}
