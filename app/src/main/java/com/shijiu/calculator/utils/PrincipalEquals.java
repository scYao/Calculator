package com.shijiu.calculator.utils;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

/**
 * 等额本金计算
 */
public class PrincipalEquals {

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 106679317;
        double yearRate = 0.049;
        double monthRate = yearRate/12;
        int year = 1;
        int month = year * 12;
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital = 0;
        double tmpCapital =0;
        double monthInterest = 0;
        for(int i=1;i<month+1;i++){
            monthCapital = (invest/month) + (invest-tmpCapital) * monthRate;
            monthInterest = (invest-tmpCapital) * monthRate;
            tmpCapital = tmpCapital + (invest/month);
            System.out.println("第" + i + "月本息： " + monthCapital + "，本金：" + (invest/month) + "，利息：" + monthInterest);
        }

    }
}
