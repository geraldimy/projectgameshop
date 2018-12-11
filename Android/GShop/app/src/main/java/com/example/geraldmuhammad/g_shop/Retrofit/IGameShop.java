package com.example.geraldmuhammad.g_shop.Retrofit;

import com.example.geraldmuhammad.g_shop.Model.Banner;
import com.example.geraldmuhammad.g_shop.Model.Category;
import com.example.geraldmuhammad.g_shop.Model.CheckUserResponse;
import com.example.geraldmuhammad.g_shop.Model.Game;
import com.example.geraldmuhammad.g_shop.Model.Order;
import com.example.geraldmuhammad.g_shop.Model.Store;
import com.example.geraldmuhammad.g_shop.Model.User;

import java.util.List;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IGameShop {

    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse> checkExistsUser (@Field("phone") String phone);

    @FormUrlEncoded
    @POST ("register.php")
    Call <User> registerNewUser  (@Field("phone") String phone,
                                  @Field("name") String name,
                                  @Field("address") String address,
                                  @Field("birthdate") String birthdate);

    @FormUrlEncoded
    @POST ("getgame.php")
    Observable<List<Game>> getGame   (@Field("categoryid") String categoryID);

    @FormUrlEncoded
    @POST ("getuser.php")
    Call <User> getUserInformation (@Field("phone") String phone);


    @GET("getbanner.php")
    io.reactivex.Observable<List<Banner>> getBanner();

    @GET("getcategory.php")
    io.reactivex.Observable<List<Category>> getCategory();

    @Multipart
    @POST("upload.php")
    Call<String> uploadFile(@Part MultipartBody.Part phone, @Part MultipartBody.Part file);

    @GET("getallgames.php")
    io.reactivex.Observable<List<Game>> getAllGames();

    @FormUrlEncoded
    @POST ("submitorder.php")
    Call <String> submitOrder(@Field("price") float orderPrice,
                              @Field("orderDetail") String orderDetail,
                              @Field("comment") String comment,
                              @Field("address") String address,
                              @Field("phone") String phone,
                              @Field("paymentMethod") String paymentMethod);

    @FormUrlEncoded
    @POST ("braintree/checkout.php")
    Call <String> payment(@Field("nonce") String nonce,
                              @Field("amount") String amount);

    @FormUrlEncoded
    @POST ("getorder.php")
    Observable<List<Order>> getOrder(@Field("userPhone") String userPhone,
                                     @Field("status")String status);

    @FormUrlEncoded
    @POST ("cancelorder.php")
    Call <String> cancelOrder(@Field("orderId") String orderId,
                          @Field("userPhone") String userPhone);


    @FormUrlEncoded
    @POST("getnearbystore.php")
    Observable<List<Store>> getNearbyStore(@Field("lat") String lat,
                                           @Field("lng") String lng);

}
