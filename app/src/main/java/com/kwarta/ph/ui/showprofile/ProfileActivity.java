package com.kwarta.ph.ui.showprofile;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryBidderRecyclerViewAdapter;
import com.kwarta.ph.adapter.ProfileFeedbackRecyclerViewAdapter;
import com.kwarta.ph.model.BiddersDataList;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.model.FeedbackModel;
import com.kwarta.ph.util.Validator;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    private TextView profilename;
    private EditText writeFeedback;
    BiddersDataList biddersDataList;
    private RelativeLayout emptyLayout;
    private RelativeLayout nointernetlayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<FeedbackModel> arrayList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilename = findViewById(R.id.profile_name);
        writeFeedback = findViewById(R.id.et_profile_writefeedback);
        swipeRefreshLayout = findViewById(R.id.swiperefreshfeedback);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        emptyLayout = findViewById(R.id.emptyLayout);
        nointernetlayout = findViewById(R.id.nointernetlayout);
        recyclerView = findViewById(R.id.profilefeedbackrecyclerview);


        arrayList = new ArrayList<>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        biddersDataList = new Gson().fromJson(getIntent().getStringExtra("profile"),BiddersDataList.class);

        writeFeedback.setHint("Write feedback to "+biddersDataList.getFname()+" "+biddersDataList.getLname());
        profilename.setText(biddersDataList.getFname()+" "+biddersDataList.getLname());

        initImageBitmaps();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        hideAllLayout();
        swipeRefreshLayout.setRefreshing(true);
        arrayList.clear();
        initImageBitmaps();

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

    private void initImageBitmaps() {


        swipeRefreshLayout.setRefreshing(false);
        showHasData();

        FeedbackModel item = new FeedbackModel();
        item.setStatus("1");
        item.setName("Dua Lipa");
        item.setRemarks("Greate Personality");

        FeedbackModel item1 = new FeedbackModel();
        item1.setStatus("0");
        item1.setName("Cardo Dalisay");
        item1.setRemarks("he arrived late!");

        arrayList.add(item);
        arrayList.add(item1);

        if(Validator.getConnectivityStatus(getApplicationContext()) > 0){
            showHasData();
            initRecyclerView();
        }else{
            showNoInternet();
        }


    }
    private void initRecyclerView(){
        ProfileFeedbackRecyclerViewAdapter packageListRecyclerViewAdapter =
                new ProfileFeedbackRecyclerViewAdapter(getApplicationContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

}
