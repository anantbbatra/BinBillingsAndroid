package com.example.anantbhushanbatra.binbillings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bin {

    @SerializedName("bin_id")
    @Expose
    private Integer binId;
    @SerializedName("community_id")
    @Expose
    private Integer communityId;
    @SerializedName("x_coordinate")
    @Expose
    private Float xCoordinate;
    @SerializedName("y_coordinate")
    @Expose
    private Float yCoordinate;
    @SerializedName("color")
    @Expose
    private String color;

    public Integer getBinId() {
        return binId;
    }

    public void setBinId(Integer binId) {
        this.binId = binId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Float getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}