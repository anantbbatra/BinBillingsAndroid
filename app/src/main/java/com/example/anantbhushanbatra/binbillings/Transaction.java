package com.example.anantbhushanbatra.binbillings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;
    @SerializedName("bin_id")
    @Expose
    private Integer binId;
    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("weight")
    @Expose
    private Float weight;
    @SerializedName("rate")
    @Expose
    private Float rate;
    @SerializedName("total_cost")
    @Expose
    private Float totalCost;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("time_of_transaction")
    @Expose
    private String timeOfTransaction;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getBinId() {
        return binId;
    }

    public void setBinId(Integer binId) {
        this.binId = binId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(String timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Transaction(Integer transactionId, Integer binId, Integer custId, Float weight, Float rate, Float totalCost, String color, Integer providerId, String timeOfTransaction, String status)
    {
        this.transactionId = transactionId;
        this.binId = binId;
        this.custId = custId;
        this.weight = weight;
        this.rate = rate;
        this.totalCost = totalCost;
        this.color = color;
        this.providerId = providerId;
        this.timeOfTransaction = timeOfTransaction;
        this.status = status;
    }
}