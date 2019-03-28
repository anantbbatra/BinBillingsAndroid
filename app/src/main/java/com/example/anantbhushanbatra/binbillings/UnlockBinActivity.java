package com.example.anantbhushanbatra.binbillings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UnlockBinActivity extends AppCompatActivity {
    String bin_choice;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin_unlock);

//        final TextView open_green_btn = (TextView)findViewById(R.id.green_open);
//        open_green_btn.setOnClickListener(new EditText.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(UnlockBinActivity.this, BinInstructionsActivity.class);
//                bin_choice = "green";
//                intent.putExtra("bin_choice", bin_choice);
//                startActivity(intent);
//            }
//        });
//
//        final TextView open_red_btn = (TextView)findViewById(R.id.red_open);
//        open_red_btn.setOnClickListener(new EditText.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(UnlockBinActivity.this, BinInstructionsActivity.class);
//                bin_choice = "red";
//                intent.putExtra("bin_choice", bin_choice);
//                startActivity(intent);
//            }
//        });
//
//        final TextView open_brown_btn = (TextView)findViewById(R.id.brown_open);
//        open_brown_btn.setOnClickListener(new EditText.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(UnlockBinActivity.this, BinInstructionsActivity.class);
//                bin_choice = "brown";
//                intent.putExtra("bin_choice", bin_choice);
//                startActivity(intent);
//            }
//        });

    } // end onCreate()
}

