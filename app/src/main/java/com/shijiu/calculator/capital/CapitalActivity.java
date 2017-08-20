package com.shijiu.calculator.capital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.utils.Main;
import com.shijiu.calculator.utils.Tool;

import java.util.ArrayList;
import java.util.List;

public class CapitalActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    private TextView detail;
    private EditText number;
    private TextView capital;
    private static final String TAG = "CapitalActivity";

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
    private boolean flag =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);

        initView();
        detail = (TextView) findViewById(R.id.id_detail);
        title.setText("大写转换");
        detail.setText("罗马数字");
        btn_point.setClickable(true);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    detail.setText("大写数字");
                    btn_point.setClickable(false);
                    flag =false;
                }else {
                    detail.setText("罗马数字");
                    btn_point.setClickable(true);
                    flag =true;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        initListener();
    }

    private void initListener() {
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = number.getText().toString();


                if (number != null && !s.equals("")) {
                    if (flag){
                        double d = Double.parseDouble(s);
                        capital.setText(Tool.change(d));
                    }else {
                        capital.setText(Main.intToRoman(Integer.parseInt(s)));
                    }


                } else {
//                    number.setHint("0");
                    capital.setText("零元整");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);

        number = (EditText) findViewById(R.id.id_number);
        capital = (TextView) findViewById(R.id.id_capital);

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

    }

    @Override
    public void onClick(View view) {
        String in = number.getText().toString();

        Log.e(TAG, "onClick: sssssssssssssssssss" + in);


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

                if (in.contains(".")){
                    int point = in.indexOf(".");
                    String s = in.substring(point);
                    if (s.length() >2){
                        return;
                    }
                }

                if (in.length() ==1 && in.substring(0,1).equals("0")){
                    number.setText(((TextView) view).getText());

                }else {
                    number.setText(in + ((TextView) view).getText() + "");
                }

                break;
            case R.id.btn_point:

                if (in.contains(".")){
                    int point = in.indexOf(".");
                    String s = in.substring(point);
                    if (s.length() >2){
                        return;
                    }
                }

                if (in.length() >0){
//                    if (in.length() ==1 && in.substring(0,1).equals("0")){
//                        number.setText(((TextView) view).getText());
//
//                    }else {
                        number.setText(in + ((TextView) view).getText() + "");
//                    }
                }


                break;

            case R.id.btn_del:
                if (in != null && !in.equals("")) {
                    number.setText(in.substring(0, in.length() - 1));

                } else {
                    capital.setText("零元整");
                }
                break;
            case R.id.btn_equal:
                number.setText("");
                capital.setText("零元整");
                break;

        }
    }
}
