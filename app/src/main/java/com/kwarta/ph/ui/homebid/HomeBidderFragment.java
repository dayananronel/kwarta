package com.kwarta.ph.ui.homebid;


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
import com.kwarta.ph.adapter.HomeBidderRecyclerViewAdapter;
import com.kwarta.ph.adapter.HomeRecyclerViewAdapter;
import com.kwarta.ph.model.BiddersItem;
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
public class HomeBidderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiperefreshbidder;
    private RelativeLayout emptyLayout;
    private RelativeLayout nointernetlayout;
    private Validator validator;

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    ArrayList<BiddersItem> arrayList;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home_bidder, container, false);

        arrayList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.home_bidderrecyclerview);
        swiperefreshbidder = root.findViewById(R.id.swiperefreshbidder);
        nointernetlayout = root.findViewById(R.id.nointernetlayout);
        emptyLayout = root.findViewById(R.id.emptyLayout);
        swiperefreshbidder.setOnRefreshListener(this);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        getItems();

        super.onViewCreated(view, savedInstanceState);
    }

    private void getItems(){
        if (validator.getConnectivityStatus(getContext())> 0){
            Api api = RetrofitBuilder.getClient().create(Api.class);
            Call<GenericResponse> call = api.showauction("1");
            call.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                    swiperefreshbidder.setRefreshing(false);
                    if(response.errorBody() == null){
                        if(response.body().getStatus().equals("000")){

                            arrayList = new Gson().fromJson(response.body().getData(),
                                    new TypeToken<List<BiddersItem>>(){}.getType());

                            if(arrayList.size() > 0){
                                showHasData();
                                initRecyclerView();
                            }else{
                                showNoData();
                            }

                        }else{
                            Toast.makeText(getContext(),"Error on request: "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            showNoData();
                        }
                    }else{
                        showNoData();
                        Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GenericResponse> call, Throwable t) {
                    swiperefreshbidder.setRefreshing(false);
                    showNoInternet();
                    t.printStackTrace();
                    Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            swiperefreshbidder.setRefreshing(false);
            showNoInternet();
        }
    }

    private void initRecyclerView(){
        HomeBidderRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HomeBidderRecyclerViewAdapter(getContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }

    @Override
    public void onRefresh() {
        arrayList.clear();
        swiperefreshbidder.setRefreshing(true);
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
