package com.example.anantbhushanbatra.binbillings;



import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
//10.0.2.2

public class HttpManager {
    private static final String BASE_URL = "http://192.168.0.193:3001" ;
    private static final String BASE_URL_INTERFACE = "http://192.168.0.193:3002" ;

    ApiService apiService;
    ApiService interfaceApiService;

    public HttpManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_URL_INTERFACE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        interfaceApiService = retrofit2.create(ApiService.class);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public Call<ArrayList<Bin>> getNearbyBins(Location userLocation){
        Call<ArrayList<Bin>> binCall = apiService.getNearbyBins(userLocation.getLatitude(), userLocation.getLongitude(), 1);
        return binCall;
    }

    public Call<Provider> getProviderInfo(int provider_id){
        Call<Provider> providerCall = apiService.getProviderInfo(provider_id);
        return providerCall;
    }


    public Call<User> getUserInfo(int cust_id){
        Call<User> providerCall = apiService.getUserInfo(cust_id);
        return providerCall;
    }

    public Call<RechargeReceipt> recharge(int cust_id, Integer amount){
        Call<RechargeReceipt> rechargeCall = apiService.recharge(cust_id, amount);
        return rechargeCall;
    }

    public Call<ArrayList<Transaction>> getTransactionHistory(int cust_id){
        Call<ArrayList<Transaction>> transactionHistoryCall = apiService.getTransactionHistory(cust_id);
        return transactionHistoryCall;
    }

    public Call<Transaction> dispute(int transaction_id, String customer_comment) {
        Call<Transaction> disputeCall = apiService.dispute(transaction_id, customer_comment);
        return disputeCall;
    }
    public Call<Integer> connectToBin(){
        Call<Integer> binAvailable = interfaceApiService.connectToBin();
        return binAvailable;
    }

    public Call<Transaction> getCode(int cust_id, int bin_id){
        Call<Transaction> startTransaction = interfaceApiService.getCode(cust_id, bin_id);
        return startTransaction;
    }

    public Call<Float> getWeight(){
        Call<Float> getWeight = interfaceApiService.getWeight();
        return getWeight;
    }

    public Call<Transaction> completeTransaction(Integer transaction_id, Float weight){
        Call<Transaction> completeTransaction = apiService.completeTransaction(transaction_id, weight);
        return completeTransaction;
    }
}