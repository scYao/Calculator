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
    private String tag;//0商业贷款,1公积金贷款,3二者组合



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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
