package com.kwarta.ph.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kwarta.ph.R;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.BiddersItem;

import java.util.ArrayList;

public class HistoryBidderRecyclerViewAdapter extends RecyclerView.Adapter<HistoryBidderRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BiddersItem> auctionersItemArrayList
            ;
    private Context mContext;

    public HistoryBidderRecyclerViewAdapter(Context mContext, ArrayList<BiddersItem> mPlaceName) {
        this.auctionersItemArrayList = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_fragment_bidderhistory,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.desc.setText(auctionersItemArrayList.get(i).getBidder_amount_desc());
        viewHolder.amount.setText(auctionersItemArrayList.get(i).getBidder_amount_value());

        if(auctionersItemArrayList.get(i).getBidder_history_status().equals("won")){
            viewHolder.stat.setImageDrawable(mContext.getDrawable(R.drawable.win));
        }else{
            viewHolder.stat.setImageDrawable(mContext.getDrawable(R.drawable.loss));
        }

        viewHolder.stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, auctionersItemArrayList.get(i).getBidder_amount_desc(), Toast.LENGTH_SHORT).show();
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
        ImageView status;
        ImageView img;
        LinearLayout subLayout;
        ImageView stat;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.bidder_history_img);
            desc = itemView.findViewById(R.id.bidder_history_amountdesc);
            amount = itemView.findViewById(R.id.bidder_history_amountvalue);
            subLayout = itemView.findViewById(R.id.bidderhistory_mainLayout);
            stat = itemView.findViewById(R.id.bidder_status);
        }
    }

}
