package com.shijiu.calculator.mortgage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

    private ImageView back;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_result);
        MortgageBean bean = (MortgageBean) this.getIntent().getSerializableExtra("bean");

        back = findViewById(R.id.id_back);
        title = findViewById(R.id.id_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText("计算结果");

        Log.e(TAG, "onCreate: " + bean.toString());
        initView();

        initData(bean);
    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 12 / 100;
        double years = Double.parseDouble(bean.getTotal_years());
        mortgage_total.setText(total_mortgage / 10000 + "万");
        total_years.setText((int) years+"年("+(int) years*12+"月)");

        double d1 = Math.pow((1 + rate), years * 12);
        double d2 = Math.pow((1 + rate), years * 12) - 1;
        double month_money = (total_mortgage * rate * d1) / d2;
        double round_month_money = Double.parseDouble(String.format("%.2f", month_money));
        month_repay.setText(round_month_money + "元");
        repay_total.setText(round_month_money * years * 12 + "");
        double d3 = round_month_money * years * 12 - total_mortgage;
        rate_total.setText(String.format("%.2f", d3) + "");
    }

    private void initView() {
        mortgage_total =  findViewById(R.id.id_mortgage_total);
        rate_total = findViewById(R.id.id_rate_total);
        repay_total =  findViewById(R.id.id_repay_total);
        total_years =  findViewById(R.id.id_total_years);

        month_repay =  findViewById(R.id.id_month_repay);
    }
}
