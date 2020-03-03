package com.cogent.utils.models;

public class Date  {

    private int year;
    private int month;
    private int dayOfMonth;
    private int hours;
    private int minutes;
    private double second;

    public Date(){}


    public Date(int year, int month, int dayOfMonth, int hours, int minutes, double second) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hours = hours;
        this.minutes = minutes;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }
}
