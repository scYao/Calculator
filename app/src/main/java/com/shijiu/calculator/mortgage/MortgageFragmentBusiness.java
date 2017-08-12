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
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.utils.Util;

/**
 * Created by yao on 2017/8/11.
 */

public class MortgageFragmentBusiness extends Fragment {
    private EditText unit_price;
    private EditText area;
    private TextView total_price;

    private EditText down_payments;
    private TextView down_payments_value;

    private TextView need_loan;
    private static final String TAG = "MortgageFragmentBusines";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mortgage_business, container, false);
        initView(view);
        initListener();
        return view;
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
                }else {
                    Log.e(TAG, "onTextChanged: sssssssssssssssss" );
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

    }


}
