package com.shijiu.calculator.mortgage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.CombinationBean;
import com.shijiu.calculator.bean.MortgageBean;

public class CombinationResultActivity extends Activity {
    private TextView mortgage_total;
    private TextView rate_total;
    private TextView repay_total;
    private TextView total_years;

    private TextView month_repay;
    private static final String TAG = "CombinationResultActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_result);
        CombinationBean bean = (CombinationBean) this.getIntent().getSerializableExtra("bean");
        Log.e(TAG, "onCreate: " +bean.toString());
        initView();

        initData(bean);
    }

    private void initData(CombinationBean bean) {
        double mortgage_business  = Double.parseDouble(bean.getMortgage_business());
        double mortgage_fund = Double.parseDouble(bean.getMortgage_fund());

        double rate_business = Double.parseDouble(bean.getRate_business())/12/100;
        double rate_fund = Double.parseDouble(bean.getRate_fund())/12/100;
        double years = Double.parseDouble(bean.getTotal_years());
        mortgage_total.setText((mortgage_fund+mortgage_business)+"万");
        total_years.setText(years+"年"+"("+years*12+"月）");

        double d1 = Math.pow((1+rate_business),years*12);
        double d2 = Math.pow((1+rate_business),years*12) -1;
        double month_money1 = (mortgage_business*rate_business*d1)/d2;

        double d3 = Math.pow((1+rate_fund),years*12);
        double d4 = Math.pow((1+rate_fund),years*12) -1;
        double month_money2 = (mortgage_fund*rate_fund*d3)/d4;


        month_repay.setText((month_money1+month_money2)+"元");

        double total_mortgage = mortgage_fund+ mortgage_business;

        repay_total.setText((month_money1+month_money2)*years*12+"");
        double d5 = (month_money1+month_money2)*years*12 -total_mortgage;
        rate_total.setText(d5+"");
    }

    private void initView() {
        mortgage_total = (TextView) findViewById(R.id.id_mortgage_total);
        rate_total = (TextView) findViewById(R.id.id_rate_total);
        repay_total = (TextView) findViewById(R.id.id_repay_total);
        total_years = (TextView) findViewById(R.id.id_total_years);

        month_repay = (TextView) findViewById(R.id.id_month_repay);
    }
}
