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
import com.kwarta.ph.model.NotificationItem;

import java.util.ArrayList;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder> {

    private ArrayList<NotificationItem> auctionersItemArrayList
            ;
    private Context mContext;

    public NotificationRecyclerViewAdapter(Context mContext, ArrayList<NotificationItem> mPlaceName) {
        this.auctionersItemArrayList = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_notifications_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(auctionersItemArrayList.get(i).getTitle());
        viewHolder.content.setText(auctionersItemArrayList.get(i).getContent());
        viewHolder.time.setText(auctionersItemArrayList.get(i).getTime());
        viewHolder.subLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, auctionersItemArrayList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return auctionersItemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView title;
        ImageView img;
        TextView time;
        LinearLayout subLayout;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.auc_notification_img);
            title = itemView.findViewById(R.id.auc_notif_title);
            content = itemView.findViewById(R.id.auc_notif_content);
            subLayout = itemView.findViewById(R.id.notif_mainlayout);
            time = itemView.findViewById(R.id.auc_notif_time);
        }
    }

}
