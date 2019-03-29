package com.example.anantbhushanbatra.binbillings;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/bins")
    Call<ArrayList<Bin>> getNearbyBins(@Query("user_x") double user_x, @Query("user_y") double user_y, @Query("cust_id") int cust_id);

    @GET("/providerInfo")
    Call<Provider> getProviderInfo(@Query("provider_id") int provider_id);
}
