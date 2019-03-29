package com.example.anantbhushanbatra.binbillings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatesInfoActivity extends AppCompatActivity {
    HttpManager httpManager;
    Call<Provider> ratesHttpQuery;
    Provider provider;
    private static final String TAG = "RechargeActivity";
    TextView providerNameView, redRateView, greenRateView, brownRateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provider_rates_activity);
        providerNameView = (TextView) findViewById(R.id.provider_name);
        redRateView = (TextView) findViewById(R.id.redRate);
        greenRateView = (TextView) findViewById(R.id.greenRate);
        brownRateView = (TextView) findViewById(R.id.brownRate);
        redRateView.setInputType(InputType.TYPE_NULL);
        greenRateView.setInputType(InputType.TYPE_NULL);
        brownRateView.setInputType(InputType.TYPE_NULL);
        providerNameView.setInputType(InputType.TYPE_NULL);

        httpManager = new HttpManager();

        ratesHttpQuery = httpManager.getProviderInfo(12);

        ratesHttpQuery.enqueue(new Callback<Provider>() {
            @Override
            public void onResponse(Call<Provider> call, Response<Provider> response) {
                provider = response.body();
                Log.e(TAG, Integer.toString(response.code()));
                populateViews();
            }
            @Override
            public void onFailure(Call<Provider> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

    }

    public void populateViews(){
        providerNameView.setText(provider.getProviderName());
        redRateView.setText(Float.toString(provider.getRed()));
        greenRateView.setText(Float.toString(provider.getGreen()));
        brownRateView.setText(Float.toString(provider.getBrown()));
    }

}