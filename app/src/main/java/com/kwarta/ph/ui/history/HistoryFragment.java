package com.kwarta.ph.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryRecyclerViewAdapter;
import com.kwarta.ph.adapter.HomeRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.ui.home.HomeViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;

    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    ArrayList<AuctionersItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = root.findViewById(R.id.history_recyclerview);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        AuctionersItem item = new AuctionersItem();
        item.setAuctioneer_amount_desc("two hundred");
        item.setAuctioneer_history_status("SOLD");


        AuctionersItem item1 = new AuctionersItem();
        item1.setAuctioneer_amount_desc("two hundred");
        item1.setAuctioneer_history_status("SOLD");

        AuctionersItem item2 = new AuctionersItem();
        item2.setAuctioneer_amount_desc("one hundred");
        item2.setAuctioneer_history_status("SOLD");


        arrayList.add(item);
        arrayList.add(item1);
        arrayList.add(item2);
        initRecyclerView();

    }
    private void initRecyclerView(){
        HistoryRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HistoryRecyclerViewAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }
}