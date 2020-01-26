package com.kwarta.ph;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kwarta.ph.model.LoginResponse;
import com.kwarta.ph.util.SharedPref;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.CommonFunctions;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_Register;
    private Button signInBtn;

    private EditText username,password;


    ProgressDialog progressDialog= null;

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

              if(CommonFunctions.getConnectivityStatus(getApplicationContext()) > 0){
                  if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

                      progressDialog = new ProgressDialog(SignInActivity.this);
                      progressDialog.setMessage("Logging you in.. Please wait.");
                      progressDialog.setCancelable(false);
                      progressDialog.show();

                      Api api = RetrofitBuilder.getClient().create(Api.class);
                      Call<GenericResponse> responseCall = api.login(username.getText().toString(),password.getText().toString());
                      responseCall.enqueue(new Callback<GenericResponse>() {
                          @Override
                          public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                              progressDialog.dismiss();
                              if(response.errorBody() == null){

                                  if(response.body().getStatus().equals("000")){

                                      LoginResponse response1 = new Gson().fromJson(response.body().getData(),LoginResponse.class);
                                      SharedPref.saveProfile(getApplicationContext(),response.body().getData());
                                      if(response1.getUser_type().equals("1")){
                                          startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                          SharedPref.saveUserType(getApplicationContext(), "auctioneer");
                                      }else if(response1.getUser_type().equals("2")){
                                          startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                          SharedPref.saveUserType(getApplicationContext(), "bidder");
                                      }

                                  }else{
                                      Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                  }

                              }else{
                                  Toast.makeText(getApplicationContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                              }
                          }

                          @Override
                          public void onFailure(Call<GenericResponse> call, Throwable t) {
                              progressDialog.dismiss();
                              Toast.makeText(getApplicationContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                          }
                      });

                  }else{
                      Toast.makeText(getApplicationContext(),"Please fill all required fields.",Toast.LENGTH_SHORT).show();
                  }
              }else{
                  Toast.makeText(getApplicationContext(),"No internet connection.",Toast.LENGTH_SHORT).show();
              }

              break;
      }
    }

}
