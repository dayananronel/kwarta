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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kwarta.ph.R;
import com.kwarta.ph.model.AuctionersItem;
import com.kwarta.ph.model.HistoryResponse;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<HistoryResponse> historyResponseArrayList
            ;
    private Context mContext;

    public HistoryRecyclerViewAdapter(Context mContext, ArrayList<HistoryResponse> historyResponses) {
        this.historyResponseArrayList = historyResponses;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_fragment_history,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.desc.setText(historyResponseArrayList.get(i).getItemname());
        viewHolder.status.setText("Winner : "+historyResponseArrayList.get(i).getFname() +" "+historyResponseArrayList.get(i).getLname());

        Glide.with(mContext)
                .load(historyResponseArrayList.get(i).getImage())
                .override(100,100)
                .into(viewHolder.img);

        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, historyResponseArrayList.get(i).getAuctioneer_amount_desc(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyResponseArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount;
        TextView desc;
        TextView status;
        ImageView img;
        LinearLayout subLayout;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.auctioneerhistory_img);
            desc = itemView.findViewById(R.id.auc_history_amountdesc);
            status = itemView.findViewById(R.id.auc_history_winner);
            subLayout = itemView.findViewById(R.id.auctioneerhistory_mainlayout);
        }
    }

}
