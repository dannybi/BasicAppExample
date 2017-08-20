package com.benishay.danny.taskmanagerdanny.Data;

/**
 * Created by Dan on 12/07/2017.
 */

public class MyTasks {
    private String tKey, text /* title */, address, gKey, uKey /*the group and the user KEY*/;
    private boolean isCompleted;
    private long createAt;
    private double loc_lat, loc_lng;

    public MyTasks() {
    }

    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getgKey() {
        return gKey;
    }

    public void setgKey(String gKey) {
        this.gKey = gKey;
    }

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public double getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public double getLoc_lng() {
        return loc_lng;
    }

    public void setLoc_lng(double loc_lng) {
        this.loc_lng = loc_lng;
    }

    @Override
    public String toString() {
        return "MyTasks{" +
                "tKey='" + tKey + '\'' +
                ", text='" + text + '\'' +
                ", address='" + address + '\'' +
                ", gKey='" + gKey + '\'' +
                ", uKey='" + uKey + '\'' +
                ", isCompleted=" + isCompleted +
                ", createAt=" + createAt +
                ", loc_lat=" + loc_lat +
                ", loclng=" + loc_lng +
                '}';
    }
}
