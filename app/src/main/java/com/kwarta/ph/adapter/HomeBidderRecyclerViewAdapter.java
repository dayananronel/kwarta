package com.kwarta.ph.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kwarta.ph.BuildConfig;
import com.kwarta.ph.R;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.model.LoginResponse;
import com.kwarta.ph.util.SharedPref;
import com.kwarta.ph.utilities.Api;
import com.kwarta.ph.utilities.CommonFunctions;
import com.kwarta.ph.utilities.GenericResponse;
import com.kwarta.ph.utilities.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeBidderRecyclerViewAdapter extends RecyclerView.Adapter<HomeBidderRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BiddersItem> biddersItemArrayList
            ;
    private Context mContext;

    String userid;

    ProgressDialog progressDialog;

    public HomeBidderRecyclerViewAdapter(Context mContext, ArrayList<BiddersItem> mPlaceName) {
        this.biddersItemArrayList = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_bidder_home_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


        Glide.with(mContext)
                .load(biddersItemArrayList.get(i).getImage())
                .override(100,100)
                .fitCenter()
                .into(viewHolder.img);

        viewHolder.desc.setText(biddersItemArrayList.get(i).getName());
        viewHolder.amount.setText(biddersItemArrayList.get(i).getMin_bid());
        viewHolder.bids.setText(biddersItemArrayList.get(i).getNumber_bid());
        viewHolder.duration.setText(biddersItemArrayList.get(i).getDuration()+" days");

        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(biddersItemArrayList.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return biddersItemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount;
        TextView desc;
        TextView bids;
        TextView duration;
        ImageView img;
        LinearLayout subLayout;
        ImageView fav;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.bidder_img);
            desc = itemView.findViewById(R.id.bidder_amount_desc);
            amount = itemView.findViewById(R.id.bidder_amount_value);
            bids = itemView.findViewById(R.id.bidder_amount_bids);
            duration = itemView.findViewById(R.id.bidder_amount_duration);
            subLayout = itemView.findViewById(R.id.bidder_mainlayout);
            fav = itemView.findViewById(R.id.bidder_favorites);
        }
    }



    private void showDialog(final BiddersItem biddersItem){

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Storyboard Bidding");

        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_bidding,null);
        alert.setView(view);

        ImageView imageView = view.findViewById(R.id.tv_bid_img);
        TextView tv_bid_name = view.findViewById(R.id.tv_bid_name);
        TextView tv_bid_duration = view.findViewById(R.id.tv_bid_duration);
        TextView tv_bid_currentbid = view.findViewById(R.id.tv_bid_currentbid);
        TextView tv_bid_currentbidamount = view.findViewById(R.id.tv_bid_currentbidamount);
        TextView tv_bid_notes = view.findViewById(R.id.tv_bid_notes);
        Button btn_bid_placebid = view.findViewById(R.id.btn_bid_placebid);
        final EditText et_bid = view.findViewById(R.id.et_bid);

        Glide.with(mContext)
                .load(biddersItem.getImage())
                .centerCrop()
                .into(imageView);

        tv_bid_name.setText(biddersItem.getName());
        tv_bid_duration.setText("Time Left: "+biddersItem.getDuration()+" days");
        tv_bid_currentbidamount.setText("Current Bid: PHP "+biddersItem.getMin_bid());
        tv_bid_currentbid.setText("["+biddersItem.getNumber_bid()+"] bids");
        tv_bid_notes.setText("Enter PHP "+biddersItem.getMin_bid()+" or higher.");


        final AlertDialog alertDialog = alert.create();
        alertDialog.show();


        btn_bid_placebid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_bid.getText().length() > 0){

                    LoginResponse data = new Gson().fromJson(SharedPref.getProfile(mContext),LoginResponse.class);

                    userid = data.getId();

                    if (CommonFunctions.getConnectivityStatus(mContext) > 0){

                        if(Double.parseDouble(et_bid.getText().toString()) >= Double.parseDouble(biddersItem.getMin_bid())){

                            progressDialog = new ProgressDialog(mContext);
                            progressDialog.setCancelable(false);
                            progressDialog.setMessage("Placing your bid.. please wait.");
                            progressDialog.show();


                            Api api = RetrofitBuilder.getClient().create(Api.class);
                            Call<GenericResponse> responseCall = api.biditem(userid,biddersItem.getId(),et_bid.getText().toString());
                            responseCall.enqueue(new Callback<GenericResponse>() {
                                @Override
                                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                    progressDialog.dismiss();
                                    if(response.errorBody() == null){
                                        if(response.body().getStatus().equals("000")){

                                            Toast.makeText(mContext,"Successfully Bid.",Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();

                                        }else{
                                            Toast.makeText(mContext,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(mContext,"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<GenericResponse> call, Throwable t) {
                                    progressDialog.dismiss();
                                    t.printStackTrace();
                                    Toast.makeText(mContext,"Something went wrong. Please try again.",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            et_bid.setError("Please enter higher or equal to bid amount.");
                        }

                    }else {
                        Toast.makeText(mContext,"No internet connection.",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    et_bid.setError("Invalid input.");
                }
            }
        });

    }

}
