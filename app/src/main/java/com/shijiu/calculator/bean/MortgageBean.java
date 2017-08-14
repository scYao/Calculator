package com.shijiu.calculator.bean;

import java.io.Serializable;

/**
 * Created by yao on 2017/8/13.
 */

public class MortgageBean implements Serializable{
    private String total_mortgage;
    private String total_years;
    private String rate;

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

    @Override
    public String toString() {
        return "MortgageBean{" +
                "total_mortgage='" + total_mortgage + '\'' +
                ", total_years='" + total_years + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
