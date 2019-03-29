package com.example.anantbhushanbatra.binbillings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeReceipt {

    @SerializedName("recharge_id")
    @Expose
    private Integer rechargeId;
    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("time_of_recharge")
    @Expose
    private String timeOfRecharge;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("newBalance")
    @Expose
    private Float newBalance;

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTimeOfRecharge() {
        return timeOfRecharge;
    }

    public void setTimeOfRecharge(String timeOfRecharge) {
        this.timeOfRecharge = timeOfRecharge;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Float newBalance) {
        this.newBalance = newBalance;
    }

}