package com.example.itime;

import java.io.Serializable;
import java.util.Calendar;

public class Clock implements Serializable {


    private String name;
    private String content;
    private int ClockId;



    private Calendar calendar;
    public Clock(String name, String content, int clockeId) {
        this.name = name;
        this.content = content;
        ClockId = clockeId;

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
        return ClockId;
    }

    public void setClockeId(int clockeId) {
        ClockId = clockeId;
    }
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
