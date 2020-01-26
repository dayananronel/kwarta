package com.kwarta.ph.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BiddersItem {


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

    private String bidder_image;
    private String bidder_amount_desc;
    private String bidder_amount_value;
    private String bidder_amount_bids;
    private String bidder_amount_duration;
    private String bidder_history_status;
    private String bidder_favorites;

    public String getBidder_favorites() {
        return bidder_favorites;
    }

    public void setBidder_favorites(String bidder_favorites) {
        this.bidder_favorites = bidder_favorites;
    }

    public String getBidder_image() {
        return bidder_image;
    }

    public void setBidder_image(String bidder_image) {
        this.bidder_image = bidder_image;
    }

    public String getBidder_amount_desc() {
        return bidder_amount_desc;
    }

    public void setBidder_amount_desc(String bidder_amount_desc) {
        this.bidder_amount_desc = bidder_amount_desc;
    }

    public String getBidder_amount_value() {
        return bidder_amount_value;
    }

    public void setBidder_amount_value(String bidder_amount_value) {
        this.bidder_amount_value = bidder_amount_value;
    }

    public String getBidder_amount_bids() {
        return bidder_amount_bids;
    }

    public void setBidder_amount_bids(String bidder_amount_bids) {
        this.bidder_amount_bids = bidder_amount_bids;
    }

    public String getBidder_amount_duration() {
        return bidder_amount_duration;
    }

    public void setBidder_amount_duration(String bidder_amount_duration) {
        this.bidder_amount_duration = bidder_amount_duration;
    }

    public String getBidder_history_status() {
        return bidder_history_status;
    }

    public void setBidder_history_status(String bidder_history_status) {
        this.bidder_history_status = bidder_history_status;
    }
}
