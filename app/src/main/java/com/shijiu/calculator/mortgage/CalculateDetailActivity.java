package com.shijiu.calculator.mortgage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.CalculatorAdapter;
import com.shijiu.calculator.bean.CalculateBean;
import com.shijiu.calculator.bean.MortgageBean;
import com.shijiu.calculator.utils.AverageCapitalPlusInterestUtils;
import com.shijiu.calculator.utils.AverageCapitalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateDetailActivity extends AppCompatActivity {
    private ImageView back;
    private TextView title;
    private List<CalculateBean> beanList = new ArrayList<>();
    private CalculatorAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "CalculateDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_detail);

        initView();

        MortgageBean bean = (MortgageBean) this.getIntent().getSerializableExtra("bean");
        if (bean.getFlag().equals("0")){
            initData1(bean);
        }else {
            initData(bean);
        }




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("还款明细");
    }

    private void initData1(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 1000;
        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);

        //每月偿还本金和利息
        double total = AverageCapitalPlusInterestUtils.getPerMonthPrincipalInterest(total_mortgage, rate, months);

        //每月偿还利息
        Map<Integer, BigDecimal> maps = AverageCapitalPlusInterestUtils.getPerMonthInterest(total_mortgage, rate, months);

        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        for (Map.Entry<Integer, BigDecimal> entry : maps.entrySet()) {
            CalculateBean bean1 = new CalculateBean();
            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);

            bean1.setOrder_number(year + "." + month);

            bean1.setTotal(total+"");//总额

            //每月偿还利息
            calendar.add(Calendar.MONTH, 1);
            BigDecimal value = entry.getValue();
            bean1.setRate(value + "");

            double monthRate = rate / 12;

            BigDecimal monthIncome = new BigDecimal(total_mortgage)
                    .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, months)))
                    .divide(new BigDecimal(Math.pow(1 + monthRate, months) - 1), 2, BigDecimal.ROUND_DOWN);
            bean1.setInvest(monthIncome.subtract(entry.getValue())+"");//偿还本金

            beanList.add(bean1);
        }
    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 1000;
        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);

        Map<Integer, Double> maps = AverageCapitalUtils.getPerMonthPrincipalInterest(total_mortgage, rate, months);

        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        for (Map.Entry<Integer, Double> entry : maps.entrySet()) {
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);

            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);
            double value = entry.getValue();
            bean1.setTotal(value+"");
            double invest = AverageCapitalUtils.getPerMonthPrincipal(total_mortgage, months);
            bean1.setInvest(invest+"");

            BigDecimal principalBigDecimal = new BigDecimal(invest);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);

            bean1.setRate(interestBigDecimal.doubleValue()+"");

            beanList.add(bean1);
        }
        Collections.reverse(beanList);
        Log.e(TAG, "initData: "+beanList );

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);

        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        adapter = new CalculatorAdapter(beanList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
