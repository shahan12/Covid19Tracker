package com.shahan.springproject.covid19tracker.DAO;

public class Locationstats {
    private String State;
    private String Country;
    private int total;
    private int diff;

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Locationstats{" +
                "State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", total=" + total +
                '}';
    }
}
