package com.shijiu.calculator.mortgage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.MortgageBean;
import com.shijiu.calculator.utils.InputFilterMinMax;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
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
    private static final String TAG = "MortgageFragmentFund";

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

    //开始计算
    private TextView start_calculate;
    private MortgageBean bean;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private TextView check_text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mortgage_fund, container, false);
        bean = new MortgageBean();
        bean.setFlag("1");
        bean.setTotal_years("1");
        bean.setRate("3.25");
        initView(view);
        initListener();
        initData();
        down_payments.setFilters(new InputFilter[]{new InputFilterMinMax("0", "100")});
        return view;
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        current_date.setText(year + "年" + month + "月" + day + "日");
        bean.setRepay_date(year + "年" + month + "月" + day + "日");
        bean.setYear(year);
        bean.setMonth(month);
        bean.setDay(day);
    }

    private void initListener() {

        current_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        current_date.setText(i + "年" + (i1 + 1) + "月" + i2 + "日");
                        bean.setRepay_date(i + "年" + (i1 + 1) + "月" + i2 + "日");
                        bean.setYear(i);
                        bean.setMonth(i1 + 1);
                        bean.setDay(i2);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == radioButton1.getId()) {
                    check_text.setText("等额本息（每月等额还款）");
                    bean.setFlag("0");
                }

                if (i == radioButton2.getId()) {
                    check_text.setText("等额本金（每月递减还款）");
                    bean.setFlag("1");
                }
            }
        });
        unit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double d1;
                double d2;
                if (Util.isEmpty(unit_price) && Util.isEmpty(area)) {
                    d1 = Double.parseDouble(unit_price.getText().toString().trim());
                    if (!area.getText().toString().trim().equals("")) {
                        d2 = Double.parseDouble(area.getText().toString().trim());

                        if (d1 > 0 && d2 > 0) {
                            double result = d1 * d2;
                            BigDecimal bigDecimal = new BigDecimal(result);
                            String str = bigDecimal.toString();
                            total_price.setText(str);
                        }
                    }
                } else {
                    total_price.setText("");
                    need_loan.setText("");
                    loan_edit.setText("");
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

                if (Util.isEmpty(unit_price) && Util.isEmpty(area)) {
                    d2 = Double.parseDouble(unit_price.getText().toString().trim());


                    if (!area.getText().toString().trim().equals("")) {
                        d1 = Double.parseDouble(area.getText().toString().trim());

                        if (d1 > 0 && d2 > 0) {
                            double result = d1 * d2;
                            BigDecimal bigDecimal = new BigDecimal(result);
                            String str = bigDecimal.toString();
                            total_price.setText(str);
                        }
                    }
                } else {
                    total_price.setText("");
                    need_loan.setText("");
                    loan_edit.setText("");
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

                if (Util.isEmpty(unit_price) && Util.isEmpty(area)) {
                    d2 = Double.parseDouble(unit_price.getText().toString().trim());


                    if (!area.getText().toString().trim().equals("")) {
                        d1 = Double.parseDouble(area.getText().toString().trim());

                        if (d1 > 0 && d2 > 0) {
                            double result = d1 * d2;
                            BigDecimal bigDecimal = new BigDecimal(result);
                            String str = bigDecimal.toString();
                            total_price.setText(str);
                        }
                    }
                } else {
                    total_price.setText("");
                    need_loan.setText("");
                    loan_edit.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //贷款金额获取焦点时清空其他输入框
        loan_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    unit_price.setText("");
                    area.setText("");
                    total_price.setText("");
                    down_payments.setText("");
                    down_payments_value.setText("");
                    need_loan.setText("");
                }
            }
        });

        //判断是否获取焦点
        down_payments.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (Util.isNull(total_price)) {
                        down_payments.setFilters(new InputFilter[]{new InputFilterMinMax("0", "100")});
                        double price = Double.parseDouble(total_price.getText().toString().trim());
                        price = (long)price;
                        need_loan.setText(price + "元");
                        loan_edit.setText(price / 10000 + "");
                    }else {
                        down_payments.setFilters(new InputFilter[]{new InputFilter.LengthFilter(0)});
                    }
                }
            }
        });

        down_payments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Util.isNull(total_price)) {
                    double price = Double.parseDouble(total_price.getText().toString().trim());
                    Log.e(TAG, "onTextChanged: " + price);
                    if (!charSequence.toString().equals("")) {
                        double result = Double.parseDouble(charSequence.toString()) * price / 100;
                        down_payments_value.setText((long) result + "");
                        long rs = (long)(price - result);
                        need_loan.setText(rs + "元");
                        loan_edit.setText(rs / 10000 + "");
                        if (rs > 0) {
                            bean.setTotal_mortgage(rs + "");
                        }
                    } else {
                        down_payments_value.setText("");
                        need_loan.setText(0 + "元");
                        loan_edit.setText(price/10000+"");
//                        loan_edit.setText("");
                    }


                } else {
                    Log.e(TAG, "onTextChanged: sssssssssssssssss");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loan_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    double result = Double.parseDouble(charSequence.toString());
                    bean.setTotal_mortgage(result * 10000 + "");
                }
                if (bean.getTotal_mortgage() == null || bean.getRate() == null || bean.getTotal_years() == null || Double.parseDouble(bean.getTotal_mortgage())<=0) {

                    start_calculate.setBackgroundResource(R.drawable.text_shape_un);
                } else {
                    start_calculate.setBackgroundResource(R.drawable.text_shape);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        hide_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHidden) {
                    drop_down_list.setVisibility(View.GONE);
                    isHidden = false;
                } else {
                    drop_down_list.setVisibility(View.VISIBLE);
                    isHidden = true;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String years = i + "年";
                String months = i * 12 + "个月";
                mortgage_years.setText(years + "(" + months + ")");
                if (i > 0) {
                    bean.setTotal_years(i + "");
                }


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
                if (Util.isNull(interest_rate1) && Util.isNull(interest_rate2)) {
                    double d1 = Double.parseDouble(Util.getValue(interest_rate1));
                    double d2 = Double.parseDouble(Util.getValue(interest_rate2));
                    double result = d1 * d2;
                    interest_rate3.setText(result + "%");
                    current_rate.setText("当前年限基准利率：公积金" + result + "%");
                    if (result > 0) {

                        bean.setRate(result + "");
                        Log.e(TAG, "onTextChanged: " + bean.toString());
                    }

                } else {
                    interest_rate3.setText(0 + "%");
                    current_rate.setText("当前年限基准利率：商业" + 0 + "%");
                }
                if (bean.getTotal_mortgage() == null || bean.getRate() == null || bean.getTotal_years() == null || Double.parseDouble(bean.getTotal_mortgage())<=0) {

                    start_calculate.setBackgroundResource(R.drawable.text_shape_un);
                } else {
                    start_calculate.setBackgroundResource(R.drawable.text_shape);
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
                if (Util.isNull(interest_rate1) && Util.isNull(interest_rate2)) {
                    double d1 = Double.parseDouble(Util.getValue(interest_rate1));
                    double d2 = Double.parseDouble(Util.getValue(interest_rate2));
                    double result = d1 * d2;
                    interest_rate3.setText(result + "%");
                    current_rate.setText("当前年限基准利率：公积金" + result + "%");

                    if (result > 0) {

                        bean.setRate(result + "");
                        Log.e(TAG, "onTextChanged: " + bean.toString());
                    }
                } else {
                    interest_rate3.setText(0 + "%");
                    current_rate.setText("当前年限基准利率：商业" + 0 + "%");
                }

                if (bean.getTotal_mortgage() == null || bean.getRate() == null || bean.getTotal_years() == null || Double.parseDouble(bean.getTotal_mortgage())<=0) {

                    start_calculate.setBackgroundResource(R.drawable.text_shape_un);
                } else {
                    start_calculate.setBackgroundResource(R.drawable.text_shape);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        start_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + bean.toString());
                if (bean.getTotal_mortgage() == null) {
//                    Toast toast = Toast.makeText(getActivity(), "没有贷款总额", Toast.LENGTH_SHORT);
//                    Util.showMyToast(toast, 1000);
                    return;
                } else if (bean.getRate() == null) {
//                    Toast toast = Toast.makeText(getActivity(), "请填写利率", Toast.LENGTH_SHORT);
//                    Util.showMyToast(toast, 1000);
                    return;
                } else if (bean.getTotal_years() == null) {
//                    Toast toast = Toast.makeText(getActivity(), "请设置还款年限", Toast.LENGTH_SHORT);
//                    Util.showMyToast(toast, 1000);
                    return;
                } else {
                    if (bean.getFlag() != null) {

                        Log.e(TAG, "onClick: " + bean.toString());
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), CalculateResultActivity.class);
                        intent.putExtra("bean", bean);
                        startActivity(intent);
                    }

                }
            }
        });
    }


    private void initView(View view) {
        unit_price = view.findViewById(R.id.id_unit_price);
        area = view.findViewById(R.id.id_area);
        total_price = view.findViewById(R.id.id_total_price);

        down_payments = view.findViewById(R.id.id_down_payments);
        down_payments_value = view.findViewById(R.id.id_down_payments_value);

        need_loan = view.findViewById(R.id._need_loan);
        hide_list = view.findViewById(R.id.id_hide_list);
        drop_down_list = view.findViewById(R.id.id_drop_down_list);
        loan_edit = view.findViewById(R.id.id_loan_edit);

        seekBar = view.findViewById(R.id.id_seekBar);
        mortgage_years = view.findViewById(R.id.id_mortgage_years);
        current_date = view.findViewById(R.id.id_current_date);

        interest_rate1 = view.findViewById(R.id.id_interest_rate1);
        interest_rate2 = view.findViewById(R.id.id_interest_rate2);
        interest_rate3 = view.findViewById(R.id.id_interest_rate3);

        current_rate = view.findViewById(R.id.id_current_rate);

        start_calculate = view.findViewById(R.id.id_start_calculate);

        radioGroup = view.findViewById(R.id.id_radioGroup);

        radioButton1 = view.findViewById(R.id.id_radioButton1);
        radioButton2 = view.findViewById(R.id.id_radioButton2);
        check_text = view.findViewById(R.id.id_check_text);

    }

}
