package com.example.anantbhushanbatra.binbillings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewTransaction extends AppCompatActivity {
    TextView transaction_id, bin_id, time_of_transaction, weight, rate, total_cost, color, status, date_of_transaction;
    Button dispute;
    Transaction chosen;
    String formattedTime, formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chosen = (Transaction) getIntent().getSerializableExtra("chosen");

        dispute = findViewById(R.id.disputeTransaction);

        transaction_id = findViewById(R.id.transaction_Id);
        bin_id = findViewById(R.id.bin_id);
        time_of_transaction = findViewById(R.id.time_of_transaction);
        weight = findViewById(R.id.weight);
        rate = findViewById(R.id.rate);
        total_cost = findViewById(R.id.total_cost);
        color = findViewById(R.id.color);
        status = findViewById(R.id.status);
        date_of_transaction = findViewById(R.id.date_of_transaction);
        formattedDate = chosen.getTimeOfTransaction().substring(0, 9);
        formattedTime = chosen.getTimeOfTransaction().substring(11,19);

        date_of_transaction.setText(formattedDate);
        time_of_transaction.setText(formattedTime);
        transaction_id.setText(chosen.getTransactionId().toString());
        bin_id.setText(chosen.getBinId().toString());
        weight.setText(chosen.getWeight().toString());
        rate.setText(chosen.getRate().toString());
        total_cost.setText(chosen.getTotalCost().toString());
        color.setText(chosen.getColor());


        if (chosen.getStatus().equals("conflict")){
            dispute.setVisibility(View.GONE);
            status.setText("Our team is looking into this issue.");
        }else{
            status.setText(chosen.getStatus().toUpperCase());
        }
    }
}
