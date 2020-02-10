package com.kwarta.ph.ui.bidhistory;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HistoryBidderRecyclerViewAdapter;
import com.kwarta.ph.adapter.HistoryRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.model.HistoryResponse;
import com.kwarta.ph.ui.home.HomeViewModel;
import com.kwarta.ph.util.Validator;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BidHistoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private RelativeLayout emptyLayout;
    private RelativeLayout nointernetlayout;
    private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;
    private SwipeRefreshLayout swiperfreshhistory;

    ArrayList<HistoryResponse> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_bid_history, container, false);

        recyclerView = root.findViewById(R.id.history_bidderrecyclerview);
        emptyLayout = root.findViewById(R.id.emptyLayout);
        nointernetlayout = root.findViewById(R.id.nointernetlayout);
        swiperfreshhistory = root.findViewById(R.id.swiperefreshbidhistory);
        swiperfreshhistory.setOnRefreshListener(this);
        swiperfreshhistory.setRefreshing(true);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initImageBitmaps();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initImageBitmaps() {

        swiperfreshhistory.setRefreshing(true);
        hideAllLayout();

        if(Validator.getConnectivityStatus(getContext()) > 0){
            getItems();
        }else{
            swiperfreshhistory.setRefreshing(false);
            showNoInternet();
        }



    }
    private void initRecyclerView(){
        HistoryBidderRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HistoryBidderRecyclerViewAdapter(getActivity(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

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

    @Override
    public void onRefresh() {
        swiperfreshhistory.setRefreshing(true);
        arrayList.clear();
        hideAllLayout();
        initImageBitmaps();

    }

    private void getItems(){

        if(Validator.getConnectivityStatus(getContext()) > 0){
            Api api = RetrofitBuilder.getClient().create(Api.class);
            Call<GenericResponse> call = api.showauctionhistory("0");
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                    swiperfreshhistory.setRefreshing(false);
                    if(response.errorBody() == null){
                        if(response.body().getStatus().equals("000")){

                            arrayList = new Gson().fromJson(response.body().getData(),
                                    new TypeToken<List<HistoryResponse>>(){}.getType());

                            if(arrayList.size() > 0){
                                showHasData();
                                initRecyclerView();
                            }else{
                                showNoData();
                            }

                        }else{
                            Toast.makeText(getContext(),"Something went wrong:"+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            showNoData();
                        }
                    }else{
                        Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                        showNoData();
                    }
                }

                @Override
                public void onFailure(Call<GenericResponse> call, Throwable t) {
                    swiperfreshhistory.setRefreshing(false);
                    showNoInternet();
                    t.printStackTrace();
                    Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            swiperfreshhistory.setRefreshing(false);
            showNoInternet();
        }
    }

}
