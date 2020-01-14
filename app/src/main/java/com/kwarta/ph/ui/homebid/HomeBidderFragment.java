package com.kwarta.ph.ui.homebid;


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
import com.kwarta.ph.adapter.HomeBidderRecyclerViewAdapter;
import com.kwarta.ph.adapter.HomeRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBidderFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    ArrayList<BiddersItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home_bidder, container, false);

        recyclerView = root.findViewById(R.id.home_bidderrecyclerview);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        BiddersItem item = new BiddersItem();
        item.setBidder_amount_desc("two hundred");
        item.setBidder_amount_value("200.00");
        item.setBidder_amount_bids("2");
        item.setBidder_amount_duration("4d 1n");

        BiddersItem item1 = new BiddersItem();
        item1.setBidder_amount_desc("two hundred");
        item1.setBidder_amount_value("200.00");
        item1.setBidder_amount_bids("2");
        item1.setBidder_amount_duration("4d 1n");


        BiddersItem item2 = new BiddersItem();
        item2.setBidder_amount_desc("two hundred");
        item2.setBidder_amount_value("200.00");
        item2.setBidder_amount_bids("2");
        item2.setBidder_amount_duration("4d 1n");


        arrayList.add(item);
        arrayList.add(item1);
        arrayList.add(item2);
        initRecyclerView();

    }
    private void initRecyclerView(){
        HomeBidderRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HomeBidderRecyclerViewAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

}
