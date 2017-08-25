package com.shijiu.calculator.mortgage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.CalculateBean;
import com.shijiu.calculator.bean.MortgageBean;
import com.shijiu.calculator.bean.UnitBean;
import com.shijiu.calculator.utils.AverageCapitalUtils;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class CalculateResultActivity extends Activity {
    private TextView mortgage_total;
    private TextView rate_total;
    private TextView repay_total;
    private TextView total_years;

    private TextView month_repay;
    private static final String TAG = "CalculateResultActivity";

    private ImageView back;
    private TextView title;
    private TextView detail;
    private MortgageBean bean;
    private TextView first_month;
    private List<CalculateBean> beanList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        bean = (MortgageBean) this.getIntent().getSerializableExtra("bean");

        back = findViewById(R.id.id_back);
        title = findViewById(R.id.id_title);
        detail = findViewById(R.id.id_detail);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText("计算结果");
        detail.setText("明细");

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculateResultActivity.this, CalculateDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        Log.e(TAG, "onCreate: " + bean.toString());
        initView();

        first_month = findViewById(R.id.id_first_month);
        if (bean.getFlag().equals("0")) {
            initData(bean);
            first_month.setText("每月还款金额");
        } else {
            first_month.setText("首月还款金额");
            initData1(bean);
        }


    }

    private void initData1(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 100;
        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);
        double years = Double.parseDouble(bean.getTotal_years());


        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital = 0;
        double tmpCapital =0;
        double monthInterest = 0;
        double monthRate = rate/12;
        double d3=0;//利息总额
        for(int i=1;i<months+1;i++){
            monthCapital = (total_mortgage/months) + (total_mortgage-tmpCapital) * monthRate;
            monthInterest = (total_mortgage-tmpCapital) * monthRate;
            tmpCapital = tmpCapital + (total_mortgage/months);
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);
            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);

            bean1.setTotal(monthCapital+"");
            bean1.setInvest((total_mortgage/months)+"");
            bean1.setRate(monthInterest+"");

            beanList.add(bean1);

            System.out.println("第" + i + "月本息： " + monthCapital + "，本金：" + (total_mortgage/months) + "，利息：" + monthInterest);
            d3 = d3+monthInterest;
        }
        double d0 = Double.parseDouble(beanList.get(0).getTotal());
        month_repay.setText(new  DecimalFormat("#.00").format(d0)+ "元");//首月还款
        mortgage_total.setText(Util.doubleTrans(total_mortgage / 10000) + "万");//贷款总额


//        double d6 = AverageCapitalUtils.getInterestCount(total_mortgage, rate, months);//利息总额


        rate_total.setText(new  DecimalFormat("#.00").format(d3) + "元");
//        rate_total.setText(new  DecimalFormat("#.00").format(d6) + "元");

        BigDecimal b = new BigDecimal((total_mortgage + d3)/10000);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        repay_total.setText(f1 + "万");

//        repay_total.setText((total_mortgage+d3) + "元");
        total_years.setText((int) years + "年(" + (int) years * 12 + "个月)");

    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate())  / 100;
        Log.e(TAG, "initData: "+bean.getRate() );
        double years = Double.parseDouble(bean.getTotal_years());
        double monthRate = rate/12;
        int month =(int) years * 12;


        total_years.setText((int) years + "年(" + (int) years * 12 + "个月)");
        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome = (total_mortgage* monthRate * Math.pow(1+monthRate,month))/(Math.pow(1+monthRate,month)-1);
        Log.e(TAG, "每月本息金额 : " + monthIncome);;
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital = 0;
        for(int i=1;i<month+1;i++){
            monthCapital = (total_mortgage* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月本金： " + monthCapital);
        }
        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = total_mortgage;
        double tmpCapital = 0;
        for(int i=1;i<month+1;i++){
            capital = capital - tmpCapital;
            monthInterest = capital * monthRate;
            tmpCapital = (total_mortgage* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月利息： " + monthInterest);
        }

        mortgage_total.setText(Util.doubleTrans(total_mortgage / 10000) + "万");

        month_repay.setText(new BigDecimal(monthIncome).setScale(2,BigDecimal.ROUND_HALF_UP) + "元");

        BigDecimal b = new BigDecimal(monthIncome * years * 12/10000);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        repay_total.setText(f1 + "万");

        double d3 = monthIncome * years * 12 - total_mortgage;
        rate_total.setText(String.format("%.2f", d3) + "元");
    }

    private void initView() {
        mortgage_total = findViewById(R.id.id_mortgage_total);
        rate_total = findViewById(R.id.id_rate_total);
        repay_total = findViewById(R.id.id_repay_total);
        total_years = findViewById(R.id.id_total_years);

        month_repay = findViewById(R.id.id_month_repay);
    }
}
