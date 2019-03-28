package com.example.anantbhushanbatra.binbillings;

import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;

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

public class HttpManager {
    private static final String BASE_URL = "http://192.168.0.192:3000" ;
    ApiService apiService;

    public HttpManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public Call<ArrayList<Bin>> getNearbyBins(Location userLocation){
        Call<ArrayList<Bin>> binCall = apiService.getNearbyBins(userLocation.getLatitude(), userLocation.getLongitude(), 1);
        return binCall;

    }
}