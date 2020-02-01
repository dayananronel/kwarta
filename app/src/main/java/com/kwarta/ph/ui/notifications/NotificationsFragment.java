package com.kwarta.ph.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryRecyclerViewAdapter;
import com.kwarta.ph.adapter.NotificationRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.NotificationItem;
import com.kwarta.ph.ui.home.HomeViewModel;
import com.kwarta.ph.util.Validator;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RelativeLayout emptyLayout;
    private RelativeLayout nointernetlayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    ArrayList<NotificationItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = root.findViewById(R.id.notif_recyclerview);
        emptyLayout = root.findViewById(R.id.emptyLayout);
        nointernetlayout = root.findViewById(R.id.nointernetlayout);
        swipeRefreshLayout = root.findViewById(R.id.swiperefreshnotifications);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        swipeRefreshLayout.setRefreshing(false);

        NotificationItem item = new NotificationItem();
        item.setContent("The Auction is already done!");
        item.setTime("9:01 pm");


        NotificationItem item1 = new NotificationItem();
        item1.setTitle("Donal Trump");
        item1.setContent("HI! Im donald trump.");
        item1.setTime("10:19 pm");


        arrayList.add(item);
        arrayList.add(item1);

        if(Validator.getConnectivityStatus(getContext()) > 0){
            showHasData();
            initRecyclerView();
        }else{
            showNoInternet();
        }

        initRecyclerView();

    }
    private void initRecyclerView(){
        NotificationRecyclerViewAdapter packageListRecyclerViewAdapter =
                new NotificationRecyclerViewAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

    @Override
    public void onRefresh() {
        arrayList.clear();
        hideAllLayout();
        swipeRefreshLayout.setRefreshing(true);
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
}