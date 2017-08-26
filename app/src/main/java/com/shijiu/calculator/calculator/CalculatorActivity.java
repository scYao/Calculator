package com.shijiu.calculator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
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

    private EditText id_input_edit;
    private EditText id_result_text;

    boolean needclear = false;

    private ImageView back;
    private TextView title;

    private static final String TAG = "CalculatorActivity";
    private int flag = 0;//0表示正数,1表示负数
    private int delete = 0;//0未使用删除,1使用删除


    private StringBuffer stringBuffer = new StringBuffer();
    private StringBuffer currentNumber = new StringBuffer();

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

        id_input_edit = (EditText) findViewById(R.id.id_input_edit);
        id_result_text = (EditText) findViewById(R.id.id_result_text);


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


    public boolean isRuler(String c) {
        boolean ruler = true;
        if (currentNumber != null && currentNumber.length() > 0) {
            if (c.matches("[\\+\\-\\×÷]")) {
                if (("" + stringBuffer.charAt(stringBuffer.length() - 1))
                        .matches("[\\+\\-\\×÷]")) {
                    ruler = false;
                }
            } else if (c.equals(".")) {
                if (currentNumber.indexOf(".") >= 0) {
                    ruler = false;
                }
            }

        } else {
            if (c.matches("[\\+\\-\\×÷]")) {
                ruler = false;
            }
        }
        return ruler;
    }


    @Override
    public void onClick(View view) {

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
            case R.id.btn_clear:
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
            case R.id.btn_equal:
                if (needclear){
                    clear();
                    needclear = false;
                }
                String content = ((TextView) view).getText().toString();
                Log.e(TAG, "onClick: content" + content);

                if (!isRuler(content)) {

                } else if (stringBuffer.length() > 22 | content.equals("C")) {
                    clear();
                } else {
                    if (content.matches("\\d")) {
                        currentNumber.append(content);
                        stringBuffer.append(content);

                    } else if (content.equals(".")) {
                        if (currentNumber.length() > 1
                                && currentNumber.indexOf(".") < 0) {
                            currentNumber.append(content);
                            stringBuffer.append(content);
                        }
                    } else if (content.matches("[\\+\\-\\×÷]")) {
                        if (delete == 0) {
                            currentNumber.delete(0, currentNumber.length());
                        } else {
                            currentNumber.append(content);
                            delete = 0;
                        }

                        stringBuffer.append(content);
                        Log.e(TAG, "onClick: ssssssssssssssssssssssss");
                    }

                    id_input_edit.setText(stringBuffer);
                    id_input_edit.setSelection(stringBuffer.length());
                    if (content.equals("=")) {
                        jisuan();
                        id_result_text.setText(stringBuffer);
                        currentNumber = new StringBuffer(stringBuffer.toString());

                    }
                }
                break;

            case R.id.btn_del:
                String c = id_input_edit.getText().toString();
                if (c != null && !c.equals("") && c.length() > 0) {

                    c = c.substring(0, c.length() - 1);
                    id_input_edit.setText(c);
                    stringBuffer = new StringBuffer(c);
                    delete = 1;
                    Log.e(TAG, "onClick: c" + c + "stringBuffer" + stringBuffer.toString());
                } else {
                    clear();
                }

                break;

            case R.id.btn_complementation:
                Log.e(TAG, "onClick: " + needclear);
                String sc = id_input_edit.getText().toString();
                if (!sc.equals("")){
                    Pattern pattern = Pattern.compile("^[0-9]+([.][0-9]+){0,1}$");
                    Matcher matcher = pattern.matcher(sc);
                    if (matcher.matches()){
                        id_input_edit.setText(sc+" " + ((TextView) view).getText());
                        String exp = id_input_edit.getText().toString();
                        int space = exp.indexOf(' ');//用于搜索空格位置
                        String s1 = exp.substring(0, space);//s1用于保存第一个运算数
                        double arg1 = Double.parseDouble(s1);//将运算数从string转换为Single;
                        double r = arg1 / 100;

                        if (r == (long) r) {
                            id_result_text.setText((long) r + "");
                        } else {
                            id_result_text.setText(r + "");
                        }
                        needclear =true;
                    }
                }

                break;
            case R.id.btn_pluse_minus:
                Log.e(TAG, "onClick: " + stringBuffer);
                if (stringBuffer.toString().equals("")) {
                    stringBuffer.append("-");
                    currentNumber.append("-");
                    id_input_edit.setText(stringBuffer);
                }
                //正数添加负号
                String regex = "^[0-9]+([.][0-9]+){0,1}$";
//                String regex = "^\\d+(\\.\\d+)?";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(stringBuffer.toString());
                if (matcher.matches()){
                    stringBuffer.insert(0,"-");
                    currentNumber.insert(0,"-");
                    id_input_edit.setText(stringBuffer);
                }

                //负数移除负号变正数
                String regexRemove = "^[-][0-9]+([.][0-9]+){0,1}$";
                Pattern pattern1 = Pattern.compile(regexRemove);
                Matcher matcher1 = pattern1.matcher(stringBuffer.toString());
                if (matcher1.matches()){
                    stringBuffer.deleteCharAt(0);
                    id_input_edit.setText(stringBuffer);
                }

                break;
        }

    }


    public void calculateProcess(String reg) {
        String content = stringBuffer.toString();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(stringBuffer);
        int i = 0;
        while (matcher.find()) {
            String fuhao = matcher.group();
            int index = matcher.end();
            ArrayList<Integer> arrayList = getyunfuNumber(index);
            if (arrayList.size() < 3) {
                return;
            }
            BigDecimal bigDecimal = getResult(arrayList);
            Log.e(TAG, "calculateProcess: index" + index + "arrayList" + arrayList + "bigDecimal" + bigDecimal + "stringBuffer" + stringBuffer);

            stringBuffer.replace(arrayList.get(0), arrayList.get(3),
                    bigDecimal.toString());

            matcher = pattern.matcher(stringBuffer);
        }

    }

    private void jisuan() {
        // TODO Auto-generated method stub
        Log.e(TAG, "jisuan: " + stringBuffer);
        BigDecimal number = null;
        calculateProcess("[\\×÷]");
        calculateProcess("[\\+\\-]");

    }


    private BigDecimal getResult(ArrayList<Integer> array) {
        BigDecimal decimal = null;

        Log.e(TAG, "getResult:" + stringBuffer);
        String first = stringBuffer.substring(array.get(0), array.get(1));
        Log.e(TAG, "getResult: " + first);

        String second = stringBuffer.substring(array.get(2), array.get(3));
        String fuhao = stringBuffer.substring(array.get(1), array.get(2));
        BigDecimal number1 = new BigDecimal(first);
        BigDecimal number2 = new BigDecimal(second);
        if ("+".equals(fuhao)) {
            decimal = number1.add(number2);
        } else if ("-".equals(fuhao)) {
            decimal = number1.subtract(number2);
        } else if ("×".equals(fuhao)) {
            decimal = number1.multiply(number2);
            // decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else if ("÷".equals(fuhao)) {
            try {
                decimal = number1.divide(number2);
            } catch (ArithmeticException e) {
                // TODO: handle exception
                decimal = number1.divide(number2, 2, BigDecimal.ROUND_HALF_UP);
            }

        }


        return decimal;
    }


    private ArrayList<Integer> getyunfuNumber(int index) {
        ArrayList<Integer> postion = new ArrayList<Integer>();
        int start = 0;
        int end = 0;

        String pre_content = "";
        StringBuffer sb;
        int newIndex=0;
        if (index == 1) {
            Pattern pattern1 = Pattern.compile("[\\+\\-\\×÷]");

            sb = new StringBuffer(stringBuffer.toString().substring(1));
            Matcher matcher1 = pattern1.matcher(sb);

            while (matcher1.find()){
                newIndex = matcher1.end();
            }

            pre_content = stringBuffer.toString().substring(0, newIndex);
            if (newIndex ==0){
                id_result_text.setText(stringBuffer.toString());
                return postion;
            }
        } else {
            pre_content = stringBuffer.toString().substring(0, index - 1);
        }

        Pattern pattern = Pattern.compile("\\-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(pre_content);
        while (matcher.find()) {
            String fuhao = matcher.group();
            end = matcher.end();
            start = matcher.start();
        }
        postion.add(start);
        postion.add(end);
        String after_content = "";
        Log.e(TAG, "getyunfuNumber: " + stringBuffer);
        if (index == 1) {
                after_content = stringBuffer.toString().substring(newIndex+1, stringBuffer.length());
        } else {
            after_content = stringBuffer.toString().substring(index, stringBuffer.length());
        }
        Log.e(TAG, "getyunfuNumber: index " + index + "pre_content: " + pre_content + "after_content:" + after_content);
        Pattern pattern2 = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher2 = pattern2.matcher(after_content);
        while (matcher2.find()) {
            end = matcher2.end();
            start = matcher2.start();
            if (index == 1) {
                postion.add(start + newIndex + 1);
                postion.add(end + newIndex + 1);
            } else {
                postion.add(start + index);
                postion.add(end + index);
            }

        }
        Log.e(TAG, "getyunfuNumber: " + postion);
        return postion;

    }

    private void clear() {
        // TODO Auto-generated method stub
        currentNumber.delete(0, currentNumber.length());
        stringBuffer.delete(0, stringBuffer.length());
        id_result_text.setText("");
        id_input_edit.setText("");
    }


}