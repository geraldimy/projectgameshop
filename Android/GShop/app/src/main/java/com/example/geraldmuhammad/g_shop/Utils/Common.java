package com.example.geraldmuhammad.g_shop.Utils;

import com.example.geraldmuhammad.g_shop.Database.DataSource.CartRepository;
import com.example.geraldmuhammad.g_shop.Database.DataSource.FavoriteRepository;
import com.example.geraldmuhammad.g_shop.Database.Local.GameRoomDatabase;
import com.example.geraldmuhammad.g_shop.Model.Category;
import com.example.geraldmuhammad.g_shop.Model.Order;
import com.example.geraldmuhammad.g_shop.Model.User;
import com.example.geraldmuhammad.g_shop.Retrofit.IGameShop;
import com.example.geraldmuhammad.g_shop.Retrofit.RetrofitClient;
import com.example.geraldmuhammad.g_shop.Retrofit.RetrofitScalarsClient;

public class Common {
    public static final String BASE_URL = "http://192.168.1.106/gameshoponline/";
    public static final String API_TOKEN_URL = "http://192.168.1.106/gameshoponline/braintree/main.php";

    public static User curentuUser = null;
    public static Category currentCategory = null;
    public static Order currentOrder = null;

    public static String platf = "-1";
    public static String edition = "-1";

    public static GameRoomDatabase gameRoomDatabase;
    public static CartRepository cartRepository;
    public static FavoriteRepository favoriteRepository;


    public static IGameShop getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IGameShop.class);
    }

    public static IGameShop getScalarsAPI()
    {
        return RetrofitScalarsClient.getScalarsClient(BASE_URL).create(IGameShop.class);
    }

    public static String convertCodeToStatus(int orderStatus) {
        switch (orderStatus)
        {
            case 0:
                return "Placed";
            case 1:
                return "Processing";
            case 2:
                return "Shipping";
            case 3:
                return "Shipped";
            case -1:
                return "Cancelled";
                default:
                    return "Order Error";
        }
    }

}
