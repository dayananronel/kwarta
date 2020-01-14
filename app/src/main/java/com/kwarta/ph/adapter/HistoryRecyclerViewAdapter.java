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

import com.kwarta.ph.R;
import com.kwarta.ph.model.AuctionersItem;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<AuctionersItem> auctionersItemArrayList
            ;
    private Context mContext;

    public HistoryRecyclerViewAdapter(Context mContext, ArrayList<AuctionersItem> mPlaceName) {
        this.auctionersItemArrayList = mPlaceName;
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

        viewHolder.desc.setText(auctionersItemArrayList.get(i).getAuctioneer_amount_desc());
        viewHolder.status.setText(auctionersItemArrayList.get(i).getAuctioneer_history_status());

        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, auctionersItemArrayList.get(i).getAuctioneer_amount_desc(), Toast.LENGTH_SHORT).show();
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
        TextView status;
        ImageView img;
        LinearLayout subLayout;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.auctioneerhistory_img);
            desc = itemView.findViewById(R.id.auc_history_amountdesc);
            status = itemView.findViewById(R.id.auc_history_status);
            subLayout = itemView.findViewById(R.id.auctioneerhistory_mainlayout);
        }
    }

}
