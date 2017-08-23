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
import com.shijiu.calculator.utils.AverageCapitalPlusInterestUtils;
import com.shijiu.calculator.utils.AverageCapitalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    private void initData1(CombinationBean bean) {
        double mortgage_business = Double.parseDouble(bean.getMortgage_business())*10000;
        double mortgage_fund = Double.parseDouble(bean.getMortgage_fund())*10000;


        double rate_business = Double.parseDouble(bean.getRate_business()) / 1000/12;
        double rate_fund = Double.parseDouble(bean.getRate_fund()) / 1000/12;


        int month = (int) (Double.parseDouble(bean.getTotal_years()) * 12);


        Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());

        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome = (mortgage_business* rate_business * Math.pow(1+rate_business,month))/(Math.pow(1+rate_business,month)-1);
        Log.e(TAG, "每月本息金额 : " + monthIncome);;
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital = 0;
        for(int i=1;i<month+1;i++){
            monthCapital = (mortgage_business* rate_business * (Math.pow((1+rate_business),i-1)))/(Math.pow(1+rate_business,month)-1);
            System.out.println("第" + i + "月本金： " + monthCapital);
            CalculateBean bean1 = new CalculateBean();
            bean1.setTotal(monthIncome+"");//总额
            bean1.setInvest(monthCapital+"");//偿还本金
            int montht = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);

            bean1.setOrder_number(year + "." + montht);
            calendar.add(Calendar.MONTH, 1);
            beanList1.add(bean1);
        }
        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = mortgage_business;
        double tmpCapital = 0;
        for(int i=0;i<beanList1.size();i++){
            capital = capital - tmpCapital;
            monthInterest = capital * rate_business;
            tmpCapital = (mortgage_business* rate_business * (Math.pow((1+rate_business),i-1)))/(Math.pow(1+rate_business,month)-1);
            System.out.println("第" + i + "月利息： " + monthInterest);

            beanList1.get(i).setRate(monthInterest+"");
        }


        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome1 = (mortgage_fund* rate_fund * Math.pow(1+rate_fund,month))/(Math.pow(1+rate_fund,month)-1);
        Log.e(TAG, "每月本息金额 : " + monthIncome1);;
        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital1 = 0;
        for(int i=1;i<month+1;i++){
            monthCapital1 = (mortgage_fund* rate_fund * (Math.pow((1+rate_fund),i-1)))/(Math.pow(1+rate_fund,month)-1);
            System.out.println("第" + i + "月本金： " + monthCapital1);
            CalculateBean bean1 = new CalculateBean();
            bean1.setTotal(monthIncome1+"");//总额
            bean1.setInvest(monthCapital1+"");//偿还本金
            int montht = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);

            bean1.setOrder_number(year + "." + montht);
            calendar.add(Calendar.MONTH, 1);
            beanList2.add(bean1);
        }
        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest1 = 0;
        double capital1 = mortgage_fund;
        double tmpCapital1 = 0;
        for(int i=0;i<beanList2.size();i++){
            capital1 = capital1 - tmpCapital1;
            monthInterest1 = capital1 * rate_fund;
            tmpCapital1 = (mortgage_fund* rate_fund * (Math.pow((1+rate_fund),i-1)))/(Math.pow(1+rate_fund,month)-1);
            System.out.println("第" + i + "月利息： " + monthInterest1);

            beanList2.get(i).setRate(monthInterest1+"");
        }


        for (int i = 0; i < beanList1.size(); i++) {
            CalculateBean bean3 = new CalculateBean();
            bean3.setOrder_number(beanList1.get(i).getOrder_number());

            double totals = Double.parseDouble(beanList1.get(i).getTotal())+ Double.parseDouble(beanList2.get(i).getTotal());
            bean3.setTotal(totals+"");

            double invest =Double.parseDouble(beanList1.get(i).getInvest())+ Double.parseDouble(beanList2.get(i).getInvest());
            bean3.setInvest(invest+"");

            double rate =Double.parseDouble(beanList1.get(i).getRate())+ Double.parseDouble(beanList2.get(i).getRate());

            bean3.setRate(rate+"");
            beanList.add(bean3);
        }

    }

    private void initData(CombinationBean bean) {
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
        double monthCapital = 0;
        double tmpCapital =0;
        double monthInterest = 0;
        double monthRate = rate_business/12;
        for(int i=1;i<months+1;i++){
            monthCapital = (mortgage_business/months) + (mortgage_business-tmpCapital) * monthRate;
            monthInterest = (mortgage_business-tmpCapital) * monthRate;
            tmpCapital = tmpCapital + (mortgage_business/months);
            CalculateBean bean1 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);
            bean1.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);

            bean1.setTotal(monthCapital+"");
            bean1.setInvest((mortgage_business/months)+"");
            bean1.setRate(monthInterest+"");

            beanList1.add(bean1);
        }


        Calendar calendar1 = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth()-1, bean.getDay());
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        double monthCapital1 = 0;
        double tmpCapital1 =0;
        double monthInterest1 = 0;
        double monthRate1 = rate_fund/12;
        for(int i=1;i<months+1;i++){
            monthCapital = (mortgage_fund/months) + (mortgage_fund-tmpCapital1) * monthRate1;
            monthInterest1 = (mortgage_fund-tmpCapital1) * monthRate1;
            tmpCapital1 = tmpCapital1 + (mortgage_fund/months);
            CalculateBean bean2 = new CalculateBean();

            int month = calendar.get(calendar.MONTH)+1;
            int year = calendar.get(calendar.YEAR);
            bean2.setOrder_number(year+"."+month);

            calendar.add(Calendar.MONTH, 1);

            bean2.setTotal(monthCapital+"");
            bean2.setInvest((mortgage_fund/months)+"");
            bean2.setRate(monthInterest1+"");

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
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i <beanList.size() ; i++) {
            stringList.add(beanList.get(i).getTotal());
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
