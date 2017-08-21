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
import com.shijiu.calculator.utils.AverageCapitalUtils;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

        Map<Integer, Double> maps = AverageCapitalUtils.getPerMonthPrincipalInterest(total_mortgage, rate, months);

        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth(), bean.getDay());
        for (Map.Entry<Integer, Double> entry : maps.entrySet()) {
            CalculateBean bean1 = new CalculateBean();


            double value = entry.getValue();
            bean1.setTotal(value + "");
            beanList.add(bean1);

        }
        month_repay.setText(beanList.get(0).getTotal() + "元");//首月还款
        mortgage_total.setText(Util.doubleTrans(total_mortgage / 10000) + "万");//贷款总额

        double d3 = AverageCapitalUtils.getInterestCount(total_mortgage, rate, months);//利息总额
        rate_total.setText(String.format("%.2f", d3) + "元");

        BigDecimal b = new BigDecimal(total_mortgage + d3);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        repay_total.setText(f1 + "元");

//        repay_total.setText((total_mortgage+d3) + "元");
        total_years.setText((int) years + "年(" + (int) years * 12 + "个月)");

    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 12 / 100;
        double years = Double.parseDouble(bean.getTotal_years());

        mortgage_total.setText(Util.doubleTrans(total_mortgage / 10000) + "万");
        total_years.setText((int) years + "年(" + (int) years * 12 + "个月)");

        double d1 = Math.pow((1 + rate), years * 12);
        double d2 = Math.pow((1 + rate), years * 12) - 1;
        double month_money = (total_mortgage * rate * d1) / d2;
        double round_month_money = Double.parseDouble(String.format("%.2f", month_money));

        month_repay.setText(round_month_money + "元");

        BigDecimal b = new BigDecimal(round_month_money * years * 12);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        repay_total.setText(f1 + "元");

        double d3 = round_month_money * years * 12 - total_mortgage;
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
