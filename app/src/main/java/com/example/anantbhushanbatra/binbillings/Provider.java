package com.example.anantbhushanbatra.binbillings;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provider {

    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("green")
    @Expose
    private Float green;
    @SerializedName("brown")
    @Expose
    private Float brown;
    @SerializedName("red")
    @Expose
    private Float red;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Float getGreen() {
        return green;
    }

    public void setGreen(Float green) {
        this.green = green;
    }

    public Float getBrown() {
        return brown;
    }

    public void setBrown(Float brown) {
        this.brown = brown;
    }

    public Float getRed() {
        return red;
    }

    public void setRed(Float red) {
        this.red = red;
    }

}