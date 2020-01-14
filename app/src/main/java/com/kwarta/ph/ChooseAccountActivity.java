package com.kwarta.ph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        buttonChoose = findViewById(R.id.btn_chooseAccount);
        buttonChoose.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, CreateAccountActivity.class));
    }
}
