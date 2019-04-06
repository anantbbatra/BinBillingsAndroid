package com.example.anantbhushanbatra.binbillings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeActivity extends AppCompatActivity {
    HttpManager httpManager;
    Call<User> balanceHttpQuery;
    Call<RechargeReceipt> rechargeHttpQuery;
    Provider provider;
    private static final String TAG = "RechargeActivity";
    TextView balance;
    EditText rechargeAmount;
    Button five, ten, twenty, pay;
    User user;
    RechargeReceipt receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recharge_screen);
        balance = (TextView) findViewById(R.id.balance);
        rechargeAmount = (EditText) findViewById(R.id.rechargeAmount);
        pay = (Button) findViewById(R.id.rechargeInitiate);

        httpManager = new HttpManager();
        balanceHttpQuery = httpManager.getUserInfo(1);

        balanceHttpQuery.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                populateViews(user.getBalance());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

        pay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!rechargeAmount.getText().toString().matches("")) {
                    rechargeHttpQuery = httpManager.recharge(1, Integer.parseInt(rechargeAmount.getText().toString()));

                    rechargeHttpQuery.enqueue(new Callback<RechargeReceipt>() {
                        @Override

                        public void onResponse(Call<RechargeReceipt> call, Response<RechargeReceipt> response) {
                            receipt = response.body();
                            populateViews(receipt.getNewBalance());
                        }

                        @Override
                        public void onFailure(Call<RechargeReceipt> call, Throwable t) {
                            Log.e(TAG, t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }
    public void populateViews(Float accountBalance){
        balance.setText(Float.toString(accountBalance));
        if (accountBalance<5){
            balance.setBackground(getResources().getDrawable(R.drawable.red_circle));
        }
        if (accountBalance>5){
            balance.setBackground(getResources().getDrawable(R.drawable.green_circle));
        }
    }
}
