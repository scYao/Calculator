package com.shijiu.calculator.mortgage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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

    private ImageView back;
    private TextView title;
    private ImageView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_mortgage);

        initView();
    }

    private void initView() {
        mortgageFragmentBusiness = new MortgageFragmentBusiness();
        mortgageFragmentCombination = new MortgageFragmentCombination();
        mortgageFragmentFund = new MortgageFragmentFund();

        fragmentList.add(mortgageFragmentBusiness);
        fragmentList.add(mortgageFragmentFund);
        fragmentList.add(mortgageFragmentCombination);

        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                int currentItem = viewPager.getCurrentItem();
                resetBackground();
                switch (position){
                    case 0:
                        viewPager.setCurrentItem(0);
                        business.setTextColor(ContextCompat.getColor(MortgageActivity.this,R.color.colorSelectText));
                        business.setBackgroundResource(R.drawable.textview_select_shape);
                        break;

                    case 1:
                        viewPager.setCurrentItem(1);
                        fund.setTextColor(ContextCompat.getColor(MortgageActivity.this,R.color.colorSelectText));
                        fund.setBackgroundResource(R.drawable.textview_select_shape);
                        break;

                    case 2:
                        viewPager.setCurrentItem(2);
                        combination.setTextColor(ContextCompat.getColor(MortgageActivity.this,R.color.colorSelectText));
                        combination.setBackgroundResource(R.drawable.textview_select_shape);
                        break;


                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        business = (TextView) findViewById(R.id.id_business);
        fund = (TextView) findViewById(R.id.id_fund);
        combination = (TextView) findViewById(R.id.id_combination);
        combination.setOnClickListener(this);
        business.setOnClickListener(this);
        fund.setOnClickListener(this);

        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);
        help = (ImageView) findViewById(R.id.id_help);
        help.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("房贷计算");
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MortgageActivity.this,HelpActivity.class);
                startActivity(intent);
            }
        });

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
