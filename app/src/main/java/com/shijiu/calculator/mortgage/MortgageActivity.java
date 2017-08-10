package com.shijiu.calculator.mortgage;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MortgageActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private MortgageFragmentBusiness mortgageFragmentBusiness;
    private MortgageFragmentCombination mortgageFragmentCombination;
    private MortgageFragmentFund mortgageFragmentFund;

    private TextView business, fund, combination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage);

        initView();
    }

    private void initView() {
        mortgageFragmentBusiness = new MortgageFragmentBusiness();
        mortgageFragmentCombination = new MortgageFragmentCombination();
        mortgageFragmentFund = new MortgageFragmentFund();

        fragmentList.add(mortgageFragmentBusiness);
        fragmentList.add(mortgageFragmentCombination);
        fragmentList.add(mortgageFragmentFund);

        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);

        business = (TextView) findViewById(R.id.id_business);
        fund = (TextView) findViewById(R.id.id_fund);
        combination = (TextView) findViewById(R.id.id_combination);
        combination.setOnClickListener(this);
        business.setOnClickListener(this);
        fund.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        resetBackground();
        switch (view.getId()){
            case R.id.id_business:
                viewPager.setCurrentItem(0);
                business.setTextColor(ContextCompat.getColor(this,R.color.colorSelectText));
                business.setBackgroundResource(R.drawable.textview_select_shape);
                break;

            case R.id.id_fund:
                viewPager.setCurrentItem(1);
                fund.setTextColor(ContextCompat.getColor(this,R.color.colorSelectText));
                fund.setBackgroundResource(R.drawable.textview_select_shape);
                break;

            case R.id.id_combination:
                viewPager.setCurrentItem(2);
                combination.setTextColor(ContextCompat.getColor(this,R.color.colorSelectText));
                combination.setBackgroundResource(R.drawable.textview_select_shape);
                break;
        }

    }
    //初始化textview背景和字体颜色
    private void resetBackground() {
        business.setTextColor(ContextCompat.getColor(this, R.color.colorDefaultText));
        fund.setTextColor(ContextCompat.getColor(this, R.color.colorDefaultText));
        combination.setTextColor(ContextCompat.getColor(this, R.color.colorDefaultText));

        business.setBackgroundResource(R.drawable.textview_unselect_shape);
        fund.setBackgroundResource(R.drawable.textview_unselect_shape);
        combination.setBackgroundResource(R.drawable.textview_unselect_shape);
    }

}
