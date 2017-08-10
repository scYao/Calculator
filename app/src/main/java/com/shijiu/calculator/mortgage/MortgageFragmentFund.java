package com.shijiu.calculator.mortgage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shijiu.calculator.R;

/**
 * Created by yao on 2017/8/11.
 */

public class MortgageFragmentFund extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mortgage_fund,container, false);
        return view;
    }
}
