package com.kwarta.ph.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse {

    @SerializedName("bidder_win_id")
    @Expose
    String bidder_win_id;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("min_bid")
    @Expose
    String min_bid;

    @SerializedName("item_id")
    @Expose
    String item_id;

    @SerializedName("itemname")
    @Expose
    String itemname;

    @SerializedName("amount_bid")
    @Expose
    String amount_bid;

    @SerializedName("fname")
    @Expose
    String fname;

    @SerializedName("lname")
    @Expose
    String lname;

    @SerializedName("email")
    @Expose
    String email;

    public String getBidder_win_id() {
        return bidder_win_id;
    }

    public String getImage() {
        return image;
    }

    public String getMin_bid() {
        return min_bid;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getItemname() {
        return itemname;
    }

    public String getAmount_bid() {
        return amount_bid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }
}
