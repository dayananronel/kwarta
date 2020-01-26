package com.kwarta.ph.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuctionersItem {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("min_bid")
    @Expose
    String min_bid;

    @SerializedName("number_bid")
    @Expose
    String number_bid;

    @SerializedName("duration")
    @Expose
    String duration;

    @SerializedName("auctioner_id")
    @Expose
    String auctioner_id;
    @SerializedName("image")
    @Expose
    String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMin_bid() {
        return min_bid;
    }

    public String getNumber_bid() {
        return number_bid;
    }

    public String getDuration() {
        return duration;
    }

    public String getAuctioner_id() {
        return auctioner_id;
    }

    public String getImage() {
        return image;
    }















    private String auctioneer_image;
    private String auctioneer_amount_desc;
    private String auctioneer_amount_value;
    private String auctioneer_amount_bids;
    private String auctioneer_amount_duration;
    private String auctioneer_history_status;

    public String getAuctioneer_history_status() {
        return auctioneer_history_status;
    }

    public void setAuctioneer_history_status(String auctioneer_history_status) {
        this.auctioneer_history_status = auctioneer_history_status;
    }

    public String getAuctioneer_image() {
        return auctioneer_image;
    }

    public void setAuctioneer_image(String auctioneer_image) {
        this.auctioneer_image = auctioneer_image;
    }

    public String getAuctioneer_amount_desc() {
        return auctioneer_amount_desc;
    }

    public void setAuctioneer_amount_desc(String auctioneer_amount_desc) {
        this.auctioneer_amount_desc = auctioneer_amount_desc;
    }

    public String getAuctioneer_amount_value() {
        return auctioneer_amount_value;
    }

    public void setAuctioneer_amount_value(String auctioneer_amount_value) {
        this.auctioneer_amount_value = auctioneer_amount_value;
    }

    public String getAuctioneer_amount_bids() {
        return auctioneer_amount_bids;
    }

    public void setAuctioneer_amount_bids(String auctioneer_amount_bids) {
        this.auctioneer_amount_bids = auctioneer_amount_bids;
    }

    public String getAuctioneer_amount_duration() {
        return auctioneer_amount_duration;
    }

    public void setAuctioneer_amount_duration(String auctioneer_amount_duration) {
        this.auctioneer_amount_duration = auctioneer_amount_duration;
    }
}
