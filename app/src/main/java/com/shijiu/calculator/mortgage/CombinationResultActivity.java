package com.shijiu.calculator.mortgage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.CalculateBean;
import com.shijiu.calculator.bean.CombinationBean;
import com.shijiu.calculator.bean.MortgageBean;
import com.shijiu.calculator.utils.AverageCapitalUtils;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CombinationResultActivity extends Activity {
    private TextView mortgage_total;
    private TextView rate_total;
    private TextView repay_total;
    private TextView total_years;

    private TextView month_repay;
    private static final String TAG = "CombinationResultActivity";


    private ImageView back;
    private TextView title;
    private TextView detail;
    private CombinationBean bean;
    private TextView first_month;

    private List<CalculateBean> beanList = new ArrayList<>();
    private List<CalculateBean> beanList1 = new ArrayList<>();
    private List<CalculateBean> beanList2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_result);
         bean = (CombinationBean) this.getIntent().getSerializableExtra("bean");

        title = findViewById(R.id.id_title);
        back = findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("计算结果");

        detail = findViewById(R.id.id_detail);
        detail.setText("明细");
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CombinationResultActivity.this, CombinationDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        initView();
        first_month = findViewById(R.id.id_first_month);
        if (bean.getFlag().equals("0")){
            initData(bean);
            first_month.setText("每月还款金额");
        }else {
            initData1(bean);
            first_month.setText("首月还款金额");
        }




    }

    private void initData1(CombinationBean bean) {
        double mortgage_business = Double.parseDouble(bean.getMortgage_business())*10000;
        double mortgage_fund = Double.parseDouble(bean.getMortgage_fund())*10000;

        double rate_business = Double.parseDouble(bean.getRate_business()) / 1000;
        double rate_fund = Double.parseDouble(bean.getRate_fund()) / 1000;

        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);

        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital1 = 0;
        double tmpCapital1 =0;
        double monthInterest1 = 0;
        double monthRate1 = rate_business/12;
        double d3=0;//利息总额
        for(int i=1;i<months+1;i++){
            monthCapital1 = (mortgage_business/months) + (mortgage_business-tmpCapital1) * monthRate1;
            monthInterest1 = (mortgage_business-tmpCapital1) * monthRate1;
            tmpCapital1 = tmpCapital1 + (mortgage_business/months);
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);
            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);

            bean1.setTotal(monthCapital1+"");
            bean1.setInvest((mortgage_business/months)+"");
            bean1.setRate(monthInterest1+"");

            beanList1.add(bean1);

            d3 = d3+monthInterest1;
        }


        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital2 = 0;
        double tmpCapital2 =0;
        double monthInterest2 = 0;
        double monthRate2 = rate_fund/12;
        double d4=0;//利息总额
        for(int i=1;i<months+1;i++){
            monthCapital2 = (mortgage_fund/months) + (mortgage_fund-tmpCapital2) * monthRate2;
            monthInterest2 = (mortgage_fund-tmpCapital2) * monthRate2;
            tmpCapital2 = tmpCapital2 + (mortgage_fund/months);
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);
            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);

            bean1.setTotal(monthCapital2+"");
            bean1.setInvest((mortgage_fund/months)+"");
            bean1.setRate(monthInterest2+"");

            beanList2.add(bean1);

            d4 = d4+monthInterest2;
        }


        for (int i = 0; i < beanList1.size(); i++) {
            CalculateBean bean3 = new CalculateBean();

            double total = Double.parseDouble(beanList1.get(i).getTotal())+ Double.parseDouble(beanList2.get(i).getTotal());
            bean3.setTotal(total+"");

            beanList.add(bean3);
        }
        mortgage_total.setText(Util.doubleTrans((mortgage_fund+mortgage_business)/10000)+"万");//贷款总额
        month_repay.setText(String.format("%.2f",Double.parseDouble(beanList.get(0).getTotal()))+"元");//首月还款


        rate_total.setText(String.format("%.2f", d3+d4)+"元");//利息总和

        double d6= ((mortgage_fund+mortgage_business)+(d3+d4))/10000;
        repay_total.setText(String.format("%.2f", d6)+"万");//还款总额

        double years = Double.parseDouble(bean.getTotal_years());
        total_years.setText((int) years+"年("+(int) years*12+"个月)");
    }

    private void initData(CombinationBean bean) {
        double mortgage_business  = Double.parseDouble(bean.getMortgage_business())*10000;
        double mortgage_fund = Double.parseDouble(bean.getMortgage_fund())*10000;

        double rate_business = Double.parseDouble(bean.getRate_business())/12/1000;
        double rate_fund = Double.parseDouble(bean.getRate_fund())/12/1000;
        double years = Double.parseDouble(bean.getTotal_years());
        mortgage_total.setText(Util.doubleTrans((mortgage_fund+mortgage_business)/10000)+"万");
        total_years.setText((int) years+"年("+(int) years*12+"个月)");

        double d1 = Math.pow((1+rate_business),years*12);
        double d2 = Math.pow((1+rate_business),years*12) -1;
        double month_money1 = (mortgage_business*rate_business*d1)/d2;

        double d3 = Math.pow((1+rate_fund),years*12);
        double d4 = Math.pow((1+rate_fund),years*12) -1;
        double month_money2 = (mortgage_fund*rate_fund*d3)/d4;



        month_repay.setText(String.format("%.2f", month_money1+month_money2)+"元");

        double total_mortgage = mortgage_fund+ mortgage_business;

        double d6 =(month_money1+month_money2)*years*12;
        repay_total.setText(String.format("%.2f", d6)+"元");
        double d5 = (month_money1+month_money2)*years*12 -total_mortgage;
        rate_total.setText(String.format("%.2f", d5)+"元");
    }

    private void initView() {
        mortgage_total = (TextView) findViewById(R.id.id_mortgage_total);
        rate_total = (TextView) findViewById(R.id.id_rate_total);
        repay_total = (TextView) findViewById(R.id.id_repay_total);
        total_years = (TextView) findViewById(R.id.id_total_years);

        month_repay = (TextView) findViewById(R.id.id_month_repay);
    }
}
