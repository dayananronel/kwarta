package com.kwarta.ph.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kwarta.ph.R;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.ui.bidders.BiddersActivity;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private ArrayList<AuctionersItem> auctionersItemArrayList
            ;
    private Context mContext;

    public HomeRecyclerViewAdapter(Context mContext,  ArrayList<AuctionersItem> mPlaceName) {
        this.auctionersItemArrayList = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_auctioneers_home_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


        Glide.with(mContext)
                .load(auctionersItemArrayList.get(i).getImage())
                .override(100,100)
                .centerCrop()
                .into(viewHolder.img);

        viewHolder.desc.setText(auctionersItemArrayList.get(i).getName());
        viewHolder.amount.setText(auctionersItemArrayList.get(i).getMin_bid());
        viewHolder.bids.setText(auctionersItemArrayList.get(i).getNumber_bid());
        viewHolder.duration.setText(auctionersItemArrayList.get(i).getDuration()+" days");

        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, auctionersItemArrayList.get(i).getName(), Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, BiddersActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return auctionersItemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount;
        TextView desc;
        TextView bids;
        TextView duration;
        ImageView img;
        LinearLayout subLayout;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.auctioneer_img);
            desc = itemView.findViewById(R.id.auctioneer_amount_desc);
            amount = itemView.findViewById(R.id.auctioneer_amount_value);
            bids = itemView.findViewById(R.id.auctioneer_amount_bids);
            duration = itemView.findViewById(R.id.auctioneer_amount_duration);
            subLayout = itemView.findViewById(R.id.auctioneer_mainlayout);
        }
    }

}
