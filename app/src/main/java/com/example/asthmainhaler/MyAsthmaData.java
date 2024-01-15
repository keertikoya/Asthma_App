package com.example.asthmainhaler;

public class MyAsthmaData {
    private String location;
    private String date;
    private String trigger;

    public MyAsthmaData(String location, String date, String trigger) {
        this.location = location;
        this.date = date;
        this.trigger = trigger;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
