package com.kwarta.ph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kwarta.ph.util.SharedPref;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_Register;
    private Button signInBtn;

    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
        listener();
    }

    private void init() {
        tv_Register = findViewById(R.id.tv_register);
        signInBtn= findViewById(R.id.signin_btn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

    }

    private void listener(){

        tv_Register.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
      switch (view.getId()){
          case R.id.tv_register:
              startActivity(new Intent(this, ChooseAccountActivity.class));
              break;

          case R.id.signin_btn:

              if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                  startActivity(new Intent(this, MenuActivity.class));
                  SharedPref.saveUserType(this,"auctioneer");
              }else{
                  startActivity(new Intent(this, MenuActivity.class));
                  SharedPref.saveUserType(this,"bidder");
              }

              break;
      }
    }

}
