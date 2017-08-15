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
import com.shijiu.calculator.bean.CombinationBean;
import com.shijiu.calculator.bean.MortgageBean;
import com.shijiu.calculator.utils.AverageCapitalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CombinationDetailActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private List<CalculateBean> beanList = new ArrayList<>();
    private List<CalculateBean> beanList1 = new ArrayList<>();
    private List<CalculateBean> beanList2 = new ArrayList<>();
    private CalculatorAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "CalculateDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_detail);

        initView();

        CombinationBean bean = (CombinationBean) this.getIntent().getSerializableExtra("bean");
        Log.e(TAG, "onCreate: "+bean.toString());

        initData(bean);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("还款明细");
    }

    private void initData(CombinationBean bean) {
        double mortgage_business = Double.parseDouble(bean.getMortgage_business())*10000;
        double mortgage_fund = Double.parseDouble(bean.getMortgage_fund())*10000;

        double rate_business = Double.parseDouble(bean.getRate_business()) / 100;
        double rate_fund = Double.parseDouble(bean.getRate_fund()) / 100;

        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);

        Map<Integer, Double> maps = AverageCapitalUtils.getPerMonthPrincipalInterest(mortgage_business, rate_business, months);

        Calendar calendar = Calendar.getInstance();
        for (Map.Entry<Integer, Double> entry : maps.entrySet()) {
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, -1);
            double value = entry.getValue();
            bean1.setTotal(value+"");
            double invest = AverageCapitalUtils.getPerMonthPrincipal(mortgage_business, months);
            bean1.setInvest(invest+"");

            BigDecimal principalBigDecimal = new BigDecimal(invest);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);

            bean1.setRate(interestBigDecimal.doubleValue()+"");

            beanList1.add(bean1);
        }

        Map<Integer, Double> maps1 = AverageCapitalUtils.getPerMonthPrincipalInterest(mortgage_fund, rate_fund, months);

        Calendar calendar1 = Calendar.getInstance();
        for (Map.Entry<Integer, Double> entry : maps1.entrySet()) {
            CalculateBean bean2 = new CalculateBean();

            int month = calendar1.get(Calendar.MONTH);
            int year = calendar1.get(Calendar.YEAR);
            bean2.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, -1);
            double value = entry.getValue();
            bean2.setTotal(value+"");
            double invest = AverageCapitalUtils.getPerMonthPrincipal(mortgage_fund, months);
            bean2.setInvest(invest+"");

            BigDecimal principalBigDecimal = new BigDecimal(invest);
            BigDecimal principalInterestBigDecimal = new BigDecimal(entry.getValue());
            BigDecimal interestBigDecimal = principalInterestBigDecimal.subtract(principalBigDecimal);
            interestBigDecimal = interestBigDecimal.setScale(2, BigDecimal.ROUND_DOWN);

            bean2.setRate(interestBigDecimal.doubleValue()+"");

            beanList2.add(bean2);
        }

        for (int i = 0; i < beanList1.size(); i++) {
            CalculateBean bean3 = new CalculateBean();
            bean3.setOrder_number(beanList1.get(i).getOrder_number());

            double total = Double.parseDouble(beanList1.get(i).getTotal())+ Double.parseDouble(beanList2.get(i).getTotal());
            bean3.setTotal(total+"");

            double invest =Double.parseDouble(beanList1.get(i).getInvest())+ Double.parseDouble(beanList2.get(i).getInvest());
            bean3.setInvest(invest+"");

            double rate =Double.parseDouble(beanList1.get(i).getRate())+ Double.parseDouble(beanList2.get(i).getRate());

            bean3.setRate(rate+"");
            beanList.add(bean3);
        }

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
