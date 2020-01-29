package com.kwarta.ph.model;

public class BiddersDataList {

    String biddername;
    String amount;
    String biddertime;

    public void setBiddername(String biddername) {
        this.biddername = biddername;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBiddertime(String biddertime) {
        this.biddertime = biddertime;
    }

    public String getBiddername() {
        return biddername;
    }

    public String getAmount() {
        return amount;
    }

    public String getBiddertime() {
        return biddertime;
    }
}
