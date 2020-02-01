package com.kwarta.ph.ui.bidders;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwarta.ph.R;
import com.kwarta.ph.adapter.BiddersRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersDataList;
import com.kwarta.ph.util.Validator;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.util.ArrayList;
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

        getItems();
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
                new BiddersRecyclerViewAdapter(getApplicationContext(),arrayList);
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
}
