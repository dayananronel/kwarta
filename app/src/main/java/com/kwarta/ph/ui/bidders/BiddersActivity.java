package com.kwarta.ph.ui.bidders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwarta.ph.R;
import com.kwarta.ph.adapter.BiddersRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersDataList;
import com.kwarta.ph.model.HistoryResponse;
import com.kwarta.ph.util.Validator;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.DateValidator;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout emptyLayout;
    private RelativeLayout nointernetlayout;
    private Validator validator;

    ArrayList<BiddersDataList> arrayList = new ArrayList<>();

    AuctionersItem item;
    private int mYear;
    private int mMonth;
    private int mDay;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidders);

        recyclerView = findViewById(R.id.bidderrecyclerview);
        swipeRefreshLayout = findViewById(R.id.biddersswiperefresh);
        emptyLayout =findViewById(R.id.emptyLayout);
        nointernetlayout = findViewById(R.id.nointernetlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        item = new Gson().fromJson(getIntent().getStringExtra("auctioneersitem"),AuctionersItem.class);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bidders for "+item.getName());


        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date dateobj = new Date();

        Log.d("okhttp","DATE : "+df.format(dateobj));


        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        if(!new DateValidator().compareDates(item.getDuration(),(mMonth + 1) +"-"+mDay+"-"+mYear)){
            getWinners();
        }else{
            getItems();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void getItems(){

       if(validator.getConnectivityStatus(getApplicationContext())> 0){
           Api api = RetrofitBuilder.getClient().create(Api.class);
           Call<GenericResponse> call = api.showbidder(item.getId());
           call.enqueue(new Callback<GenericResponse>() {
               @Override
               public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                   swipeRefreshLayout.setRefreshing(false);
                   if(response.errorBody() == null){
                       if(response.body().getStatus().equals("000")){

                           Log.d("okhttp","DATA : :: "+response.body().getData());
                           arrayList = new Gson().fromJson(response.body().getData(),
                                   new TypeToken<List<BiddersDataList>>(){}.getType());
                           if(arrayList.size() > 0){
                               showHasData();
                               initRecyclerView();
                           }else{
                               showNoData();
                               Toast.makeText(getApplicationContext(),"No bidders at this time.",Toast.LENGTH_SHORT).show();
                           }

                       }else{
                           showNoData();
                           Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       showNoData();
                       Toast.makeText(getApplicationContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<GenericResponse> call, Throwable t) {
                   swipeRefreshLayout.setRefreshing(false);
                   showNoInternet();
                   t.printStackTrace();
                   Toast.makeText(getApplicationContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
               }
           });
       }else{
           swipeRefreshLayout.setRefreshing(false);
           showNoInternet();

       }
    }

    private void initRecyclerView(){
        BiddersRecyclerViewAdapter packageListRecyclerViewAdapter =
                new BiddersRecyclerViewAdapter(BiddersActivity.this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        hideAllLayout();
        getItems();
    }

    public void hideAllLayout(){
        recyclerView.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        nointernetlayout.setVisibility(View.GONE);
    }

    public void showHasData(){
        recyclerView.setVisibility(View.VISIBLE);
        emptyLayout.setVisibility(View.GONE);
        nointernetlayout.setVisibility(View.GONE);
    }
    public void showNoInternet(){
        recyclerView.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        nointernetlayout.setVisibility(View.VISIBLE);
    }
    public void showNoData(){
        recyclerView.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);
        nointernetlayout.setVisibility(View.GONE);
    }

    private void getWinners(){

        if(validator.getConnectivityStatus(getApplicationContext())> 0){
            Api api = RetrofitBuilder.getClient().create(Api.class);
            Call<GenericResponse> call = api.showauctionhistory("0");
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if(response.errorBody() == null){
                        if(response.body().getStatus().equals("000")){

                            Log.d("okhttp","DATA : :: "+response.body().getData());
                            ArrayList<HistoryResponse> historyResponses = new ArrayList<>();
                            historyResponses = new Gson().fromJson(response.body().getData(),
                                    new TypeToken<List<HistoryResponse>>(){}.getType());

                            if(historyResponses.size() > 0){


                                new MaterialStyledDialog.Builder(BiddersActivity.this)
                                        .setTitle("Congratulations to the Winner of "+historyResponses.get(0).getItemname())
                                        .setDescription(""+historyResponses.get(0).getFname()+" "+ historyResponses.get(0).getLname())
                                        .setStyle(Style.HEADER_WITH_TITLE)
                                        .setPositiveText("Close")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                finish();
                                            }
                                        })
                                        .show();



                            }else{
                                showNoData();
                                Toast.makeText(getApplicationContext(),"No winners at this time.",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            showNoData();
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showNoData();
                        Toast.makeText(getApplicationContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GenericResponse> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    showNoInternet();
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            swipeRefreshLayout.setRefreshing(false);
            showNoInternet();
        }
    }

}
