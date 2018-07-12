package com.shevelev.spring.core;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private Random random = new Random();
    private int id = random.nextInt();
    private String msg;
    private Date date;
    private DateFormat df;


    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName()).append(" ");
        stringBuilder.append("id = ").append(id).append(" ");
        stringBuilder.append("msg = ").append(msg).append(" ");
        stringBuilder.append("date = ").append(df.format(date));
        return String.valueOf(stringBuilder);
    }
}
