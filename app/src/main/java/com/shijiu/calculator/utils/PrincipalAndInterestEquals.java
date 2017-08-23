package com.shijiu.calculator.utils;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

/**
 * 等额本息计算
 */
public class PrincipalAndInterestEquals {

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 1000000;
        double yearRate = 0.049;
        double monthRate = yearRate/12;
        int year = 1;
        int month = year * 12;
        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome = (invest* monthRate * Math.pow(1+monthRate,month))/(Math.pow(1+monthRate,month)-1);
        System.out.println("每月本息金额 : " + monthIncome);
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital = 0;
        for(int i=1;i<month+1;i++){
            monthCapital = (invest* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月本金： " + monthCapital);
        }
        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = invest;
        double tmpCapital = 0;
        for(int i=1;i<month+1;i++){
            capital = capital - tmpCapital;
            monthInterest = capital * monthRate;
            tmpCapital = (invest* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月利息： " + monthInterest);
        }

    }

}