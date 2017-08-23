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
import com.shijiu.calculator.utils.Util;

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
        double years = Double.parseDouble(bean.getTotal_years());
        double monthRate = rate/12;
        int month =(int) years * 12;

        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome = (total_mortgage* monthRate * Math.pow(1+monthRate,month))/(Math.pow(1+monthRate,month)-1);
        Log.e(TAG, "每月本息金额 : " + monthIncome);;
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital = 0;
        for(int i=1;i<month+1;i++){
            monthCapital = (total_mortgage* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月本金： " + monthCapital);
            CalculateBean bean1 = new CalculateBean();
            bean1.setTotal(monthIncome+"");//总额
            bean1.setInvest(monthCapital+"");//偿还本金
            int montht = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);

            bean1.setOrder_number(year + "." + montht);
            calendar.add(Calendar.MONTH, 1);
            beanList.add(bean1);
        }
        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = total_mortgage;
        double tmpCapital = 0;
        for(int i=0;i<beanList.size();i++){
            capital = capital - tmpCapital;
            monthInterest = capital * monthRate;
            tmpCapital = (total_mortgage* monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
            System.out.println("第" + i + "月利息： " + monthInterest);

            beanList.get(i).setRate(monthInterest+"");
        }


    }

    private void initData(MortgageBean bean) {
        double total_mortgage = Double.parseDouble(bean.getTotal_mortgage());
        double rate = Double.parseDouble(bean.getRate()) / 1000;
        int months = (int) (Double.parseDouble(bean.getTotal_years()) * 12);


        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital = 0;
        double tmpCapital =0;
        double monthInterest = 0;
        double monthRate = rate/12;
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
