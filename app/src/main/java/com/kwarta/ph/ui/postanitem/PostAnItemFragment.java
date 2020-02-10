package com.kwarta.ph.ui.postanitem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.kwarta.ph.CreateAccountActivity;
import com.kwarta.ph.R;
import com.kwarta.ph.SignInActivity;
import com.kwarta.ph.model.LoginResponse;
import com.kwarta.ph.util.SharedPref;
import com.kwarta.ph.util.Validator;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAnItemFragment extends Fragment implements View.OnClickListener {

    private PostAnItemViewModel postAnItemViewModel;

    static final int PICTURE_RESULT = 1;
    String mCurrentPhotoPath;
    ContentValues values;
    private Uri file;
    Bitmap help1;

    private Button selectdate;
    private EditText et_desc,et_amount,et_name;
    private String dates ="";
    ThumbnailUtils thumbnail;

    ImageView postitem;
    private int mYear,mMonth,mDay;
    private Button submitbtn;
    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_postanitem, container, false);

        postitem = root.findViewById(R.id.img_postitem);
        selectdate = root.findViewById(R.id.selectdate);

        et_desc = root.findViewById(R.id.et_description);
        et_amount = root.findViewById(R.id.et_amount);
        et_name = root.findViewById(R.id.et_name);

        submitbtn = root.findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(this);

        selectdate.setOnClickListener(this);
        values = new ContentValues();
        postitem.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.img_postitem:

                break;

            case R.id.selectdate:

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dates = ""+dayOfMonth ;
                                selectdate.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                break;

            case R.id.submitbtn:

                if(Validator.getConnectivityStatus(Objects.requireNonNull(getContext())) <=0 )  {
                    Toast.makeText(getContext(),"No internet connection.",Toast.LENGTH_SHORT).show();
                }else {
                    if(dates.isEmpty() || et_name.getText().toString().isEmpty()|| et_desc.getText().toString().isEmpty() || et_amount.getText().toString().isEmpty() ){
                        Toast.makeText(getContext(),"All fields are mandatory.",Toast.LENGTH_SHORT).show();
                    }else{

                        progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Adding your auction.. Please wait.");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        LoginResponse loginResponse = new Gson().fromJson(SharedPref.getProfile(getContext()),LoginResponse.class);
                        Api api = RetrofitBuilder.getClient().create(Api.class);
                        Call<GenericResponse> call = api.addauction(et_name.getText().toString(),"https://carepharmaceuticals.com.au/wp-content/uploads/sites/19/2018/02/placeholder-600x400.png",
                                et_desc.getText().toString(),et_amount.getText().toString(),dates, loginResponse.getId());

                        call.enqueue(new Callback<GenericResponse>() {
                            @Override
                            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                progressDialog.dismiss();
                                Log.d("okhttp","RESPONSE : "+ new Gson().toJson(response));
                                if(response.errorBody() == null){
                                    if(response.body().getStatus().equals("000")){
                                        Toast.makeText(getContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                                        et_amount.setText("");
                                        et_desc.setText("");
                                        et_name.setText("");
                                        selectdate.setText("");

                                    }else{
                                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                }else{
                                    Toast.makeText(getContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<GenericResponse> call, Throwable t) {
                                t.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(getContext(),"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }

                break;

        }
    }

}