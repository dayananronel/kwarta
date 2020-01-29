package com.kwarta.ph;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChooseAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoose;

    private RadioButton radio_bidder,radio_auctioneer;
    private String type;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        radio_auctioneer = findViewById(R.id.radio_auctioneer);
        radio_bidder= findViewById(R.id.radio_bidder);


        radio_bidder.setOnClickListener(this);
        radio_auctioneer.setOnClickListener(this);

        buttonChoose = findViewById(R.id.btn_chooseAccount);
        buttonChoose.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btn_chooseAccount:
               if(type == null){
                   Toast.makeText(getApplicationContext(),"Please select user type.",Toast.LENGTH_SHORT).show();
               }else{
                   startActivity(new Intent(this, CreateAccountActivity.class).putExtra("user_type",type));
               }
               break;

           case  R.id.radio_auctioneer:
               if (radio_bidder.isChecked()) {
                   radio_bidder.setChecked(false);
                   radio_auctioneer.setChecked(true);
                   type = "1";
               }else{
                   radio_auctioneer.setChecked(true);
                   type = "1";
               }
               break;
           case R.id.radio_bidder:
               if (radio_auctioneer.isChecked()) {
                   radio_auctioneer.setChecked(false);
                   radio_bidder.setChecked(true);
                   type = "2";
               }else{
                   radio_bidder.setChecked(true);
                   type = "2";
               }
               break;
       }
    }
}
