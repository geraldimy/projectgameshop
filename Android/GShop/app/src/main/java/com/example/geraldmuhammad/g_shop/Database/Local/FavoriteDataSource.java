package com.example.geraldmuhammad.g_shop.Database.Local;

import com.example.geraldmuhammad.g_shop.Database.DataSource.IFavoriteDataSource;
import com.example.geraldmuhammad.g_shop.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDataSource {

    private FavoriteDAO favoriteDAO;
    private  static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDAO favoriteDAO) {
        this.favoriteDAO = favoriteDAO;
    }

    public static  FavoriteDataSource getInstance(FavoriteDAO favoriteDAO)
    {
        if(instance == null)
            instance = new FavoriteDataSource(favoriteDAO);
         return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavItems() {
        return favoriteDAO.getFavItems();
    }

    @Override
    public int isFavorite(int itemId) {
        return favoriteDAO.isFavorite(itemId);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        favoriteDAO.insertFav(favorites);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDAO.delete(favorite);
    }
}
