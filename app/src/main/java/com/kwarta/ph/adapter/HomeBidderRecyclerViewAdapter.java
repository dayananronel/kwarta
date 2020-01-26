package com.kwarta.ph.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.kwarta.ph.R;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersItem;

import java.util.ArrayList;

public class HomeBidderRecyclerViewAdapter extends RecyclerView.Adapter<HomeBidderRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BiddersItem> auctionersItemArrayList
            ;
    private Context mContext;

    public HomeBidderRecyclerViewAdapter(Context mContext, ArrayList<BiddersItem> mPlaceName) {
        this.auctionersItemArrayList = mPlaceName;
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

        viewHolder.desc.setText(auctionersItemArrayList.get(i).getBidder_amount_desc());
        viewHolder.amount.setText(auctionersItemArrayList.get(i).getBidder_amount_value());
        viewHolder.bids.setText(auctionersItemArrayList.get(i).getBidder_amount_bids());
        viewHolder.duration.setText(auctionersItemArrayList.get(i).getBidder_amount_duration());

        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, auctionersItemArrayList.get(i).getBidder_amount_desc(), Toast.LENGTH_SHORT).show();
                showDialog(auctionersItemArrayList.get(i));
            }
        });

        viewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "FAVORITES : "+auctionersItemArrayList.get(i).getBidder_amount_desc(), Toast.LENGTH_SHORT).show();
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



    private void showDialog(BiddersItem biddersItem){

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Storyboard Bidding");
        alert.setView(R.layout.activity_bidding);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

}
