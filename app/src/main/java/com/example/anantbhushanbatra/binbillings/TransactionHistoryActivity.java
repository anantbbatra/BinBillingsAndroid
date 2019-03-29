package com.example.anantbhushanbatra.binbillings;

import android.net.TrafficStats;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryActivity extends AppCompatActivity {
    private static final String TAG = "RechargeActivity";
    public static TransactionAdapter adapter;
    HttpManager httpManager;
    Call<ArrayList<Transaction>> transactionHistoryHttpQuery;
    ArrayList<Transaction> transactions;
    ListView transactionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        transactionListView = findViewById(R.id.lister);

        httpManager = new HttpManager();
        transactionHistoryHttpQuery = httpManager.getTransactionHistory(1);

        transactionHistoryHttpQuery.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                transactions = response.body();
                Log.e(TAG, Integer.toString(transactions.size()));
                populateList();
            }
            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

    public void populateList(){
        adapter = new TransactionAdapter(this, transactions);
        transactionListView.setAdapter(adapter);
    }

}
