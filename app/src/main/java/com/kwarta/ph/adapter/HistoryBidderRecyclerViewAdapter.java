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

import com.bumptech.glide.Glide;
import com.kwarta.ph.R;
import com.kwarta.ph.model.HistoryResponse;
import com.kwarta.ph.util.SharedPref;

import java.util.ArrayList;

public class HistoryBidderRecyclerViewAdapter extends RecyclerView.Adapter<HistoryBidderRecyclerViewAdapter.ViewHolder> {

    private ArrayList<HistoryResponse> historyResponses
            ;
    private Context mContext;

    public HistoryBidderRecyclerViewAdapter(Context mContext, ArrayList<HistoryResponse> mPlaceName) {
        this.historyResponses = mPlaceName;
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

        viewHolder.desc.setText(historyResponses.get(i).getItemname());
        viewHolder.amount.setText(historyResponses.get(i).getAmount_bid());

        Glide.with(mContext)
                .load(historyResponses.get(i).getImage())
                .override(100,100)
                .into(viewHolder.img);

        if(historyResponses.get(i).getBidder_win_id().equals(SharedPref.getID(mContext))){
            viewHolder.stat.setImageDrawable(mContext.getDrawable(R.drawable.win));
        }else{
            viewHolder.stat.setImageDrawable(mContext.getDrawable(R.drawable.loss));
        }

        viewHolder.stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, historyResponses.get(i).getBidder_amount_desc(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyResponses.size();
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
