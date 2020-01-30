package com.kwarta.ph.ui.bidders;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kwarta.ph.R;
import com.kwarta.ph.adapter.BiddersRecyclerViewAdapter;
import com.kwarta.ph.model.BiddersDataList;

import java.util.ArrayList;
import java.util.Objects;

public class BiddersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<BiddersDataList> arrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidders);

        recyclerView = findViewById(R.id.bidderrecyclerview);
        swipeRefreshLayout = findViewById(R.id.biddersswiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bidders");

        initImageBitmaps();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initImageBitmaps() {

        BiddersDataList item = new BiddersDataList();
        item.setBiddername("Dan Kirby");
        item.setAmount("PHP 180.00");
        item.setBiddertime("03-02-20 at 12:16 AM");

        BiddersDataList item1 = new BiddersDataList();
        item1.setBiddername("Cardo Dalisay");
        item1.setAmount("PHP 100.00");
        item1.setBiddertime("06-05-20 at 10:16 AM");

        BiddersDataList item2 = new BiddersDataList();
        item2.setBiddername("Dan Kirby");
        item2.setAmount("PHP 120.00");
        item2.setBiddertime("06-12-20 at 12:00 PM");

        arrayList.add(item);
        arrayList.add(item1);
        arrayList.add(item2);
        initRecyclerView();

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
        swipeRefreshLayout.setRefreshing(false);
    }
}
