package model;

import java.time.LocalDateTime;

public class Order {

    private int ordreID;
    private String timeNow;
    private int brugerID;
    private int totalSum;

    public Order(){

    }

    public Order(int ordreID, String timeNow, int brugerID, int totalSum) {
        this.ordreID = ordreID;
        this.timeNow = timeNow;
        this.brugerID = brugerID;
        this.totalSum = totalSum;
    }

    public Order(String timeNow, int brugerID, int totalSum) {
        this.timeNow = timeNow;
        this.brugerID = brugerID;
        this.totalSum = totalSum;
    }

    public int getOrdreID() {
        return ordreID;
    }

    public void setOrdreID(int ordreID) {
        this.ordreID = ordreID;
    }

    public String getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(String timeNow) {
        this.timeNow = timeNow;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
