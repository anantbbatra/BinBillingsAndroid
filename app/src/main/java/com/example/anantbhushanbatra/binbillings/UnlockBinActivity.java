package com.example.anantbhushanbatra.binbillings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnlockBinActivity extends AppCompatActivity {
    HttpManager httpManager = new HttpManager();
    int bin_id;

    String bin_choice;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin_unlock);
        bin_id = (int) getIntent().getSerializableExtra("bin_id");


        final TextView open_green_btn = (TextView)findViewById(R.id.green_open);
        open_green_btn.setOnClickListener(new EditText.OnClickListener(){
            public void onClick(View v){
                open_green_btn.setVisibility(View.GONE);
                //Toast saying:
                Toast.makeText(UnlockBinActivity.this, "Bin is unlocked. Open, enter trash and close lid.!",
                        Toast.LENGTH_LONG).show();
                unlock();
            }
        });
//        Intent openTransaction = new Intent(UnlockBinActivity.this, ViewTransaction.class);
//        openTransaction.putExtra("chosen", completedTransaction);
//        startActivity(filter);
    } // end onCreate()

    public void unlock() {
        Call<Transaction> getCode = httpManager.getCode(1, bin_id);
//
        getCode.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                final Transaction transaction = response.body();

                Call<Float> getWeight = httpManager.getWeight();
                getWeight.enqueue(new Callback<Float>() {
                    @Override
                    public void onResponse(Call<Float> call, Response<Float> response) {
                        Float result = response.body();
                        Log.e("debug",result.toString());

//
                        Call<Transaction> completeTransaction = httpManager.completeTransaction(transaction.getTransactionId(),result );

                        completeTransaction.enqueue(new Callback<Transaction>() {
                            @Override
                            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                Transaction result = response.body();
                                Log.e("here", "now im here");
                                Intent filter = new Intent(UnlockBinActivity.this, ViewTransaction.class);
                                filter.putExtra("chosen", result);
                                startActivity(filter);
                            }
                            @Override
                            public void onFailure(Call<Transaction> call, Throwable t) {
                            }
                        });
//
                    }

                    @Override
                    public void onFailure(Call<Float> call, Throwable t) {
                    }
                });
//
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
            }
        });
    }
}

