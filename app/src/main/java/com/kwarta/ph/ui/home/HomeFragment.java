package com.kwarta.ph.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kwarta.ph.R;
import com.kwarta.ph.adapter.HomeRecyclerViewAdapter;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    ArrayList<AuctionersItem> arrayList = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        arrayList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.home_recyclerview);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getItems();
        super.onViewCreated(view, savedInstanceState);
    }

    private void getItems(){

        Api api = RetrofitBuilder.getClient().create(Api.class);
        Call<GenericResponse> call = api.showauction("1");
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if(response.errorBody() == null){
                    if(response.body().getStatus().equals("000")){

                        arrayList = new Gson().fromJson(response.body().getData(),
                                new TypeToken<List<AuctionersItem>>(){}.getType());

                        if(arrayList.size() > 0){
                            initRecyclerView();
                        }else{
                            Toast.makeText(getContext(),"No Items to post.",Toast.LENGTH_SHORT).show();
                        }

                    }else{

                    }
                }else{
                    Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(),"Something went wrong.Please try again.",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initRecyclerView(){
        HomeRecyclerViewAdapter packageListRecyclerViewAdapter =
                new HomeRecyclerViewAdapter(getContext(),arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(packageListRecyclerViewAdapter);

    }
}