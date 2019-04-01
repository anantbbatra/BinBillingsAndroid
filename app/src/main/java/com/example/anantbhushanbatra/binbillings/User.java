package com.example.anantbhushanbatra.binbillings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("cust_name")
    @Expose
    private String custName;
    @SerializedName("community_id")
    @Expose
    private Integer communityId;
    @SerializedName("balance")
    @Expose
    private Float balance;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("apartment_name")
    @Expose
    private String communityName;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("cust_email")
    @Expose
    private String cust_email;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCustEmail() {
        return cust_email;
    }

    public void setCustEmail(String cust_email) {
        this.cust_email = cust_email;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }



}