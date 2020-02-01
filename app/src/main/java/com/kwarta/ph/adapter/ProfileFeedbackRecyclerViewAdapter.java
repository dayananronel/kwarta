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
import com.kwarta.ph.model.BiddersItem;
import com.kwarta.ph.model.FeedbackModel;

import java.util.ArrayList;

public class ProfileFeedbackRecyclerViewAdapter extends RecyclerView.Adapter<ProfileFeedbackRecyclerViewAdapter.ViewHolder> {

    private ArrayList<FeedbackModel> feedbackModelArrayList
            ;
    private Context mContext;

    public ProfileFeedbackRecyclerViewAdapter(Context mContext, ArrayList<FeedbackModel> mPlaceName) {
        this.feedbackModelArrayList = mPlaceName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feedbacks_layout_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(feedbackModelArrayList.get(i).getName());
        viewHolder.desc.setText(feedbackModelArrayList.get(i).getRemarks());
        if(feedbackModelArrayList.get(i).getStatus().equals("0")){
            viewHolder.img.setImageDrawable(mContext.getDrawable(R.drawable.thumbsdown));
        }else{
            viewHolder.img.setImageDrawable(mContext.getDrawable(R.drawable.ic_thumb_up_red_a100_24dp));
        }


    }

    @Override
    public int getItemCount() {
        return feedbackModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView desc;
        ImageView status;
        ImageView img;
        LinearLayout subLayout;
        ImageView stat;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.feedbackimg);
            desc = itemView.findViewById(R.id.feedbackremarks);
            name = itemView.findViewById(R.id.feedbackname);
            subLayout = itemView.findViewById(R.id.feedbackslayout);

        }
    }

}
