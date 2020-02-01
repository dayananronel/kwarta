package com.kwarta.ph.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BiddersDataList {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("duration")
    @Expose
    String duration;

    @SerializedName("auctioner_id")
    @Expose
    String auctioner_id;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("bidder_id")
    @Expose
    String bidder_id;

    @SerializedName("item_id")
    @Expose
    String item_id;

    @SerializedName("amount_bid")
    @Expose
    String amount_bid;

    @SerializedName("fname")
    @Expose
    String fname;

    @SerializedName("lname")
    @Expose
    String lname;


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAmount_bid() {
        return amount_bid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getAuctioner_id() {
        return auctioner_id;
    }

    public String getStatus() {
        return status;
    }

    public String getBidder_id() {
        return bidder_id;
    }

    public String getItem_id() {
        return item_id;
    }
}
