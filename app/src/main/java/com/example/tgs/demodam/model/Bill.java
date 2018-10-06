package com.example.tgs.demodam.model;

import java.util.Date;

public class Bill {

    public String id;
    public long date;

    public Bill(){

    }


    public Bill(String id, long date) {
        this.id = id;
        this.date = date;
    }

    public Bill(String string, Date parse) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String dateConvert = new Date(date).toString();
        return id + "\n" + dateConvert ;
    }
}
