package com.shijiu.calculator.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class CombinationBean implements Serializable{

    private String mortgage_business;
    private String rate_business;

    private String mortgage_fund;
    private String rate_fund;

    private String total_years;
    private String flag;//0等额本息,1等额本金

    public String getMortgage_business() {
        return mortgage_business;
    }

    public void setMortgage_business(String mortgage_business) {
        this.mortgage_business = mortgage_business;
    }

    public String getRate_business() {
        return rate_business;
    }

    public void setRate_business(String rate_business) {
        this.rate_business = rate_business;
    }

    public String getMortgage_fund() {
        return mortgage_fund;
    }

    public void setMortgage_fund(String mortgage_fund) {
        this.mortgage_fund = mortgage_fund;
    }

    public String getRate_fund() {
        return rate_fund;
    }

    public void setRate_fund(String rate_fund) {
        this.rate_fund = rate_fund;
    }

    public String getTotal_years() {
        return total_years;
    }

    public void setTotal_years(String total_years) {
        this.total_years = total_years;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


}
