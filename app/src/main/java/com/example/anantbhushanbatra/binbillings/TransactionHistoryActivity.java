package com.example.anantbhushanbatra.binbillings;

import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        transactionListView = findViewById(R.id.lister);

        httpManager = new HttpManager();
        transactionHistoryHttpQuery = httpManager.getTransactionHistory(1);

        AdapterView.OnItemClickListener listenerForTransactionClick = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Intent openTransaction = new Intent(TransactionHistoryActivity.this, ViewTransaction.class);
                Transaction chosen = adapter.getItem(position);
                openTransaction.putExtra("chosen", chosen);
                startActivity(openTransaction);
            }
        };

        transactionListView.setOnItemClickListener(listenerForTransactionClick);

        transactionHistoryHttpQuery.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                transactions = response.body();
                Log.e(TAG, response.toString());
                Log.e(TAG,response.message());
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
