package com.example.itime;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Clock implements Serializable {
    private boolean dayFinal=false;

    private String name;
    private String content;



    private String countdown;

    public String getDaoshu() {
        return daoshu;
    }

    public void setDaoshu(String daoshu) {
        this.daoshu = daoshu;
    }

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
    public int getClockId() {
        return ClockId;
    }

    public void setClockId(int clockId) {
        ClockId = clockId;
    }
    public Calendar getSub(){
        Calendar calendar2= Calendar.getInstance();

        calendar2.setTime(new Date());
        long now=calendar2.getTimeInMillis();

        long time=calendar.getTimeInMillis();

        if(time-now<0)dayFinal=true;
        else dayFinal=false;

        long sub=Math.abs(time-now);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));//此处为格林威治时区，方便后面与1970-1-1 0：0：0相减

        calendar.setTime(new Date(sub));
        return calendar;
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
