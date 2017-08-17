package com.shijiu.calculator.bean;

import java.io.Serializable;

/**
 * Created by yao on 2017/8/13.
 */

public class MortgageBean implements Serializable{
    private String total_mortgage;
    private String total_years;
    private String rate;
    private String flag;//0等额本息,1等额本金,3二者都有
    private String repay_date;
    private int year;
    private int month;
    private int day;



    public String getTotal_mortgage() {
        return total_mortgage;
    }

    public void setTotal_mortgage(String total_mortgage) {
        this.total_mortgage = total_mortgage;
    }

    public String getTotal_years() {
        return total_years;
    }

    public void setTotal_years(String total_years) {
        this.total_years = total_years;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRepay_date() {
        return repay_date;
    }

    public void setRepay_date(String repay_date) {
        this.repay_date = repay_date;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MortgageBean{" +
                "total_mortgage='" + total_mortgage + '\'' +
                ", total_years='" + total_years + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
