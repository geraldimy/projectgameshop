package com.example.geraldmuhammad.g_shop.Database.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.geraldmuhammad.g_shop.Database.ModelDB.Cart;
import com.example.geraldmuhammad.g_shop.Database.ModelDB.Favorite;

@Database(entities = {Cart.class, Favorite.class},version = 1)
public abstract class GameRoomDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract FavoriteDAO favoriteDAO();

    private static  GameRoomDatabase instance;

    public static  GameRoomDatabase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context,GameRoomDatabase.class,"GameShopDB")
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }

}
