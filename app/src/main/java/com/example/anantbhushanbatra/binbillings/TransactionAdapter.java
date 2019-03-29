package com.example.anantbhushanbatra.binbillings;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction> {
    TextView weightView, costView, dateView;
    private static final String TAG = "TransactionHistoryActivity";

    ImageView conflictView;
    View colorView;
    private Context context;
    public TransactionAdapter(Context context, List<Transaction> transactions) {
        super(context, 0, transactions);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listTransactionView = convertView;
        if (listTransactionView == null) {
            listTransactionView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_transaction, parent, false);
        }
        Transaction transaction = getItem(position);

        weightView = (TextView) listTransactionView.findViewById(R.id.weightView);
        costView = (TextView) listTransactionView.findViewById(R.id.costView);
        dateView = (TextView) listTransactionView.findViewById(R.id.dateView);

        colorView = (View) listTransactionView.findViewById(R.id.colorIdentifier);
        conflictView = (ImageView) listTransactionView.findViewById(R.id.issueView);

        weightView.setText((transaction.getWeight().toString())+"kg");
        costView.setText("€"+(transaction.getTotalCost().toString()));
        dateView.setText(transaction.getTimeOfTransaction().split("T0")[0]);

        switch (transaction.getColor()){
            case "green":
                colorView.setBackgroundColor(context.getResources().getColor(R.color.green));
                break;
            case "red":
                colorView.setBackgroundColor(context.getResources().getColor(R.color.red));
                break;
            case "brown":
                colorView.setBackgroundColor(context.getResources().getColor(R.color.brown));
                break;
        }

        if (transaction.getStatus().equals("conflict")){
            conflictView.setVisibility(View.VISIBLE);
            Log.e(TAG,transaction.getStatus());
        }else{
            conflictView.setVisibility(View.INVISIBLE);

        }

        return listTransactionView;
    }
}
