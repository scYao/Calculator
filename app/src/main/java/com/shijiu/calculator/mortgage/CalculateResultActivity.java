package com.shijiu.calculator.mortgage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.MortgageBean;

public class CalculateResultActivity extends Activity {
    private TextView mortgage_total;
    private TextView rate_total;
    private TextView repay_total;
    private TextView total_years;

    private TextView month_repay;
    private static final String TAG = "CalculateResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        MortgageBean bean = (MortgageBean) this.getIntent().getSerializableExtra("bean");

        Log.e(TAG, "onCreate: " + bean.toString());
        initView();

        initData(bean);
    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 12 / 100;
        double years = Double.parseDouble(bean.getTotal_years());
        mortgage_total.setText(total_mortgage / 10000 + "万");
        total_years.setText(years + "年" + "(" + years * 12 + "月）");

        double d1 = Math.pow((1 + rate), years * 12);
        double d2 = Math.pow((1 + rate), years * 12) - 1;
        double month_money = (total_mortgage * rate * d1) / d2;
        month_repay.setText(month_money + "元");
        repay_total.setText(month_money * years * 12 + "");
        double d3 = month_money * years * 12 - total_mortgage;
        rate_total.setText(d3 + "");
    }

    private void initView() {
        mortgage_total =  findViewById(R.id.id_mortgage_total);
        rate_total = findViewById(R.id.id_rate_total);
        repay_total =  findViewById(R.id.id_repay_total);
        total_years =  findViewById(R.id.id_total_years);

        month_repay =  findViewById(R.id.id_month_repay);
    }
}
