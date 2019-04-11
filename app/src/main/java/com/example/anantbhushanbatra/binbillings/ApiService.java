package com.example.anantbhushanbatra.binbillings;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/bins")
    Call<ArrayList<Bin>> getNearbyBins(@Query("user_x") double user_x, @Query("user_y") double user_y, @Query("cust_id") int cust_id);

    @GET("/providerInfo")
    Call<Provider> getProviderInfo(@Query("provider_id") int provider_id);

    @GET("/userInfo")
    Call<User> getUserInfo(@Query("cust_id") int cust_id);

    @POST("/recharge")
    Call<RechargeReceipt> recharge(@Query("cust_id") int cust_id, @Query("amount") int amount);

    @POST("/unlock")
    Call<Transaction> getCode(@Query("cust_id") int cust_id, @Query("bin_id") int bin_id);

    @GET("/transactionsForAndroid")
    Call<ArrayList<Transaction>> getTransactionHistory(@Query("cust_id") int cust_id);

    @GET("/getStatus")
    Call<Integer> connectToBin();

    @GET("/getWeight")
    Call<Float> getWeight();


    @POST("/transact")
    Call<Transaction> completeTransaction(@Query("transaction_id") int transaction_id, @Query("weight") Float weight);


    @POST("/dispute")
    Call<Transaction> dispute(@Query("transaction_id") int transaction_id, @Query("comments") String customer_comments);
}
