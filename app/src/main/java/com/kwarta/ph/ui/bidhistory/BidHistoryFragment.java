package com.kwarta.ph.ui.bidhistory;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryBidderRecyclerViewAdapter;
import com.kwarta.ph.adapter.HistoryRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BidHistoryFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;

    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    ArrayList<BiddersItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_bid_history, container, false);

        recyclerView = root.findViewById(R.id.history_bidderrecyclerview);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        BiddersItem item = new BiddersItem();
        item.setBidder_amount_desc("one hundred");
        item.setBidder_history_status("won");
        item.setBidder_amount_value("100.00");

        BiddersItem item1 = new BiddersItem();
        item1.setBidder_amount_desc("two hundred");
        item1.setBidder_history_status("loss");
        item1.setBidder_amount_value("200.00");

        arrayList.add(item);
        arrayList.add(item1);
        initRecyclerView();

    }
    private void initRecyclerView(){
        HistoryBidderRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HistoryBidderRecyclerViewAdapter(getActivity(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

}
