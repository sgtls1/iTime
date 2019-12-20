package com.example.itime;

public class Clock {


    private String name;
    private String content;
    private int ClockeId;

    public Clock(String name, String content, int clockeId) {
        this.name = name;
        this.content = content;
        ClockeId = clockeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClockeId() {
        return ClockeId;
    }

    public void setClockeId(int clockeId) {
        ClockeId = clockeId;
    }
}
