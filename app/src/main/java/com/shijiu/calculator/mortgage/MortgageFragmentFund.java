package com.shijiu.calculator.mortgage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.utils.Util;

import java.util.Calendar;

/**
 * Created by yao on 2017/8/11.
 */

public class MortgageFragmentFund extends Fragment {

    private EditText unit_price;
    private EditText area;
    private TextView total_price;

    private EditText down_payments;
    private TextView down_payments_value;

    private TextView need_loan;
    private static final String TAG = "MortgageFragmentBusines";

    //控制显示或隐藏布局
    private ImageView hide_list;
    private boolean isHidden = false;
    private LinearLayout drop_down_list;

    private TextView loan_edit;

    //进度条
    private SeekBar seekBar;
    private TextView mortgage_years;

    //当前日期
    private TextView current_date;

    //贷款利率
    private EditText interest_rate1;
    private EditText interest_rate2;
    private TextView interest_rate3;

    private TextView current_rate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mortgage_fund,container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month =calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        current_date.setText(year+"年"+month+ "月"+ day+"日");
    }

    private void initListener() {
        unit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double d1;
                double d2;
                d1 = Double.parseDouble(unit_price.getText().toString().trim());
                if (!area.getText().toString().trim().equals("")){
                    d2 = Double.parseDouble(area.getText().toString().trim());

                    if (d1> 0 && d2 >0){
                        double result = d1*d2;
                        total_price.setText(result+"元");
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double d1;
                double d2;
                d2 = Double.parseDouble(unit_price.getText().toString().trim());
                if (!area.getText().toString().trim().equals("")){
                    d1 = Double.parseDouble(area.getText().toString().trim());

                    if (d1> 0 && d2 >0){
                        double result = d1*d2;
                        total_price.setText(result+"");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        down_payments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Util.isNull(total_price)){
                    double price = Double.parseDouble(total_price.getText().toString().trim());
                    Log.e(TAG, "onTextChanged: "+price );
                    double result = Double.parseDouble(charSequence.toString())*price/100;
                    down_payments_value.setText(result+"");
                    double rs =price -result;
                    need_loan.setText(rs+"元");
                    loan_edit.setText(rs/10000+"");
                }else {
                    Log.e(TAG, "onTextChanged: sssssssssssssssss" );
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        hide_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHidden){
                    drop_down_list.setVisibility(View.GONE);
                    isHidden = false;
                }else {
                    drop_down_list.setVisibility(View.VISIBLE);
                    isHidden = true;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String years = i+ "年";
                String months =i*12+ "个月";
                mortgage_years.setText(years+"("+months+")");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        interest_rate1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Util.isNull(interest_rate1) && Util.isNull(interest_rate2)){
                    double d1 = Double.parseDouble(Util.getValue(interest_rate1));
                    double d2 = Double.parseDouble(Util.getValue(interest_rate2));
                    double result = d1*d2;
                    interest_rate3.setText(result+"%");
                    current_rate.setText("当前年限基准利率：公积金"+result+"%");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        interest_rate2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Util.isNull(interest_rate1) && Util.isNull(interest_rate2)){
                    double d1 = Double.parseDouble(Util.getValue(interest_rate1));
                    double d2 = Double.parseDouble(Util.getValue(interest_rate2));
                    double result = d1*d2;
                    interest_rate3.setText(result+"%");
                    current_rate.setText("当前年限基准利率：公积金"+result+"%");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initView(View view) {
        unit_price= view.findViewById(R.id.id_unit_price);
        area = view.findViewById(R.id.id_area);
        total_price = view.findViewById(R.id.id_total_price);

        down_payments = view.findViewById(R.id.id_down_payments);
        down_payments_value= view.findViewById(R.id.id_down_payments_value);

        need_loan = view.findViewById(R.id._need_loan);
        hide_list= view.findViewById(R.id.id_hide_list);
        drop_down_list = view.findViewById(R.id.id_drop_down_list);
        loan_edit = view.findViewById(R.id.id_loan_edit);

        seekBar = view.findViewById(R.id.id_seekBar);
        mortgage_years = view.findViewById(R.id.id_mortgage_years);
        current_date = view.findViewById(R.id.id_current_date);

        interest_rate1 = view.findViewById(R.id.id_interest_rate1);
        interest_rate2 = view.findViewById(R.id.id_interest_rate2);
        interest_rate3 = view.findViewById(R.id.id_interest_rate3);

        current_rate = view.findViewById(R.id.id_current_rate);

    }
}
