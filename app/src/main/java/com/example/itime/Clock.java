package com.example.itime;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Clock implements Serializable {


    private String name;
    private String content;
    private String countdown;
    private String daoshu;
    private int ClockId;
    private Calendar calendar;
    public Clock(String name, String content, int clockeId, String countdown,String daoshu) {
        this.name = name;
        this.content = content;
        ClockId = clockeId;
        this.countdown=countdown;
        this.daoshu=daoshu;
    }
    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }
    public String getDaoshu() {
        return daoshu;
    }

    public void setDaoshu(String daoshu) {
        this.daoshu = daoshu;
    }
    public int getClockId() {
        return ClockId;
    }

    public void setClockId(int clockId) {
        ClockId = clockId;
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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
