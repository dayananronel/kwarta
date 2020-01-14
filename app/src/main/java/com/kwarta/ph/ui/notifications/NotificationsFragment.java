package com.kwarta.ph.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryRecyclerViewAdapter;
import com.kwarta.ph.adapter.NotificationRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.NotificationItem;
import com.kwarta.ph.ui.home.HomeViewModel;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;

    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;

    ArrayList<NotificationItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = root.findViewById(R.id.notif_recyclerview);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        NotificationItem item = new NotificationItem();
        item.setContent("The Auction is already done!");
        item.setTime("9:01 pm");


        NotificationItem item1 = new NotificationItem();
        item1.setTitle("Donal Trump");
        item1.setContent("HI! Im donald trump.");
        item1.setTime("10:19 pm");



        arrayList.add(item);
        arrayList.add(item1);
        initRecyclerView();

    }
    private void initRecyclerView(){
        NotificationRecyclerViewAdapter packageListRecyclerViewAdapter =
                new NotificationRecyclerViewAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }
}