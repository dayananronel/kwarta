package com.kwarta.ph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kwarta.ph.model.LoginResponse;
import com.kwarta.ph.util.SharedPref;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText et_fname,et_lname,et_email,et_username,et_password,et_confirmpassword;
    private String user_type;

    String fname,lname,email,password,username,confirmpassword;
    private ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user_type = getIntent().getStringExtra("user_type");
        assert user_type != null;

        et_fname = findViewById(R.id.et_firstname);
        et_lname = findViewById(R.id.et_lastname);
        et_email = findViewById(R.id.et_email);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_confirmpassword = findViewById(R.id.et_confirmpassword);


    }


    public void createAccount(View view) {
        switch (view.getId()){
            case R.id.btn_createAccount:

                    fname = et_fname.getText().toString();
                    lname = et_lname.getText().toString();
                    email = et_email.getText().toString();
                    username = et_username.getText().toString();
                    password = et_password.getText().toString();
                    confirmpassword = et_confirmpassword.getText().toString();

                    if(fname.isEmpty()||lname.isEmpty()||email.isEmpty()||username.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()){
                        Toast.makeText(getApplicationContext(),"All fields are mandatory.",Toast.LENGTH_SHORT).show();
                    }else{
                        if(password.equals(confirmpassword)){

                            progressDialog = new ProgressDialog(CreateAccountActivity.this);
                            progressDialog.setMessage("Adding your account.. Please wait.");
                            progressDialog.setCancelable(false);
                            progressDialog.show();

                            Log.d("okhttp","FNAME : "+fname);
                            Log.d("okhttp","LNAME : "+lname);
                            Log.d("okhttp","EMAIL : "+email);
                            Log.d("okhttp","USERNAME : "+username);
                            Log.d("okhttp","PASSWORD : "+password);
                            Log.d("okhttp","TYPE : "+user_type);

                            Api api = RetrofitBuilder.getClient().create(Api.class);
                            Call<GenericResponse> responseCall = api.register(fname,lname,email,username,password,user_type);
                            responseCall.enqueue(new Callback<GenericResponse>() {
                                @Override
                                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                    progressDialog.dismiss();
                                    Log.d("okhttp","RESPONSE : "+ new Gson().toJson(response));
                                    if(response.errorBody() == null){
                                        if(response.body().getStatus().equals("000")){
                                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                           startActivity(intent);

                                        }else{
                                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<GenericResponse> call, Throwable t) {
                                    t.printStackTrace();
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();

                                }
                            });


                        }else{
                            Toast.makeText(getApplicationContext(),"Password didn't match",Toast.LENGTH_SHORT).show();
                        }
                    }

                break;
        }
    }
}
