package com.shijiu.calculator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity implements OnClickListener {

    private TextView btn_0;
    private TextView btn_1;
    private TextView btn_2;
    private TextView btn_3;
    private TextView btn_4;
    private TextView btn_5;
    private TextView btn_6;
    private TextView btn_7;
    private TextView btn_8;
    private TextView btn_9;

    private TextView btn_equal;
    private TextView btn_del;
    private TextView btn_point;
    private TextView btn_clear;
    private TextView btn_pluse_minus;
    private TextView btn_divide;
    private TextView btn_multiply;
    private TextView btn_minus;
    private TextView btn_pluse;
    private TextView btn_complementation;

    private TextView id_input_edit;
    private TextView id_result_text;

    boolean needclear;

    private ImageView back;
    private TextView title;

    private static final String TAG = "CalculatorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText("计算器");

        initView();

    }

    private void initView() {

        btn_0 = (TextView) findViewById(R.id.btn_0);
        btn_1 = (TextView) findViewById(R.id.btn_1);
        btn_2 = (TextView) findViewById(R.id.btn_2);
        btn_3 = (TextView) findViewById(R.id.btn_3);
        btn_4 = (TextView) findViewById(R.id.btn_4);
        btn_5 = (TextView) findViewById(R.id.btn_5);
        btn_6 = (TextView) findViewById(R.id.btn_6);
        btn_7 = (TextView) findViewById(R.id.btn_7);
        btn_8 = (TextView) findViewById(R.id.btn_8);
        btn_9 = (TextView) findViewById(R.id.btn_9);

        btn_equal = (TextView) findViewById(R.id.btn_equal);
        btn_del = (TextView) findViewById(R.id.btn_del);
        btn_point = (TextView) findViewById(R.id.btn_point);
        btn_clear = (TextView) findViewById(R.id.btn_clear);
        btn_pluse_minus = (TextView) findViewById(R.id.btn_pluse_minus);
        btn_divide = (TextView) findViewById(R.id.btn_divide);
        btn_multiply = (TextView) findViewById(R.id.btn_multiply);
        btn_minus = (TextView) findViewById(R.id.btn_minus);
        btn_pluse = (TextView) findViewById(R.id.btn_pluse);
        btn_complementation = (TextView) findViewById(R.id.btn_complementation);

        id_input_edit = (TextView) findViewById(R.id.id_input_edit);
        id_result_text = (TextView) findViewById(R.id.id_result_text);


        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_equal.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_pluse_minus.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_pluse.setOnClickListener(this);
        btn_complementation.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String str = id_input_edit.getText().toString();

        switch (view.getId()) {

            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (needclear) {
                    str = "";
                    id_input_edit.setText("");
                    id_result_text.setText("");
                }
                id_input_edit.setText(str + ((TextView) view).getText());
                break;

            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    id_input_edit.setText(str.substring(0, str.length() - 1));

                }
                break;
            case R.id.btn_clear:
                id_input_edit.setText("");
                id_result_text.setText("0");
                needclear = false;
                break;
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
            case R.id.btn_complementation:
                if (needclear) {
                    id_input_edit.setText("");
                    id_result_text.setText("");

                }
                id_input_edit.setText(str + " " + ((TextView) view).getText() + " ");
                break;


            case R.id.btn_equal:
                getResult();
                break;

        }
    }

    /**
     * 获取计算结果
     */
    private void getResult() {
        Log.e(TAG, "getResult: wodddddddddddddddddddddddddd");
        needclear = true;
        String exp = id_input_edit.getText().toString();
        double r = 0;
        int space = exp.indexOf(' ');//用于搜索空格位置

        String s2 = null;
        double arg2;
        if (space == -1){
            String s1 = exp.substring(0);
            id_result_text.setText(s1);
        }else {
            String s1 = exp.substring(0, space);//s1用于保存第一个运算数
            String op = exp.substring(space + 1, space + 2);//op用于保存运算符
            double  arg1 = Double.parseDouble(s1);//将运算数从string转换为Single;
             s2 = exp.substring(space + 3);//s2用于保存第二个运算数


            if (s2.equals("")) {
                arg2 = 0;
            } else {
                arg2 = Double.parseDouble(s2);
            }

            if (op.equals("+")) {
                r = arg1 + arg2;
            } else if (op.equals("-")) {
                r = arg1 - arg2;
            } else if (op.equals("×")) {
                r = arg1 * arg2;
            } else if (op.equals("÷")) {
                if (arg2 == 0) {
                    r = 0;
                } else {
                    r = arg1 / arg2;
                }
            } else if (op.equals("%")) {
                if (arg2 == 0) {
                    r = 0;
                } else {
                    r = arg1 % arg2;
                }
            }

            if (!s1.contains(".") && !s2.contains(".")) {
                int result = (int) r;
                id_result_text.setText(result + "");

            } else {
                id_result_text.setText(r + "");
            }
        }


    }
}