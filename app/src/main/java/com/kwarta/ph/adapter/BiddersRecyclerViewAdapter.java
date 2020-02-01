package com.kwarta.ph.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kwarta.ph.R;
import com.kwarta.ph.model.BiddersDataList;
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.ui.showprofile.ProfileActivity;

import java.util.ArrayList;

public class BiddersRecyclerViewAdapter extends RecyclerView.Adapter<BiddersRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BiddersDataList> biddersDataLists
            ;
    private Context mContext;

    public BiddersRecyclerViewAdapter(Context mContext, ArrayList<BiddersDataList> mPlaceName) {
        this.biddersDataLists = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bidders_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(biddersDataLists.get(i).getFname()+" "+ biddersDataLists.get(i).getLname());
        viewHolder.amount.setText(biddersDataLists.get(i).getAmount_bid());
        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ProfileActivity.class);
                intent.putExtra("profile",new Gson().toJson(biddersDataLists.get(i)));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return biddersDataLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView amount;
        TextView name;
        TextView biddertime;
        RelativeLayout subLayout;

        ViewHolder(View itemView){
            super(itemView);
            amount = itemView.findViewById(R.id.bidder_amount);
            subLayout = itemView.findViewById(R.id.bidders_mainLayout);
            name = itemView.findViewById(R.id.bidder_name);
            biddertime = itemView.findViewById(R.id.bidder_time);
        }
    }

}
