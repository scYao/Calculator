package com.shijiu.calculator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shijiu.calculator.R;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewCalculatorActivity extends AppCompatActivity {

    BigDecimal result_jisuan;
    StringBuffer stringBuffer = new StringBuffer();
    private EditText result;
    private String pre_yunsuanfu = "";
    StringBuffer currentNumber = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calculator);
        result = (EditText) this.findViewById(R.id.result);
        //result.setText(result_jisuan.);
        // result.setInputType(InputType.TYPE_NULL);
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            result.setInputType(InputType.TYPE_NULL);
        } else {

            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(result, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isRuler(String c) {
        boolean ruler = true;
        if (currentNumber != null && currentNumber.length() > 0) {
            if (c.matches("[\\+\\-\\*/]")) {
                if (("" + stringBuffer.charAt(stringBuffer.length() - 1))
                        .matches("[\\+\\-\\*/]")) {
                    ruler = false;
                }
            } else if (c.equals(".")) {
                if (currentNumber.indexOf(".") >= 0) {
                    ruler = false;
                }
            }

        } else {
            if (c.matches("[\\+\\-\\*/]")) {
                ruler = false;
            }
        }
        return ruler;
    }

    public void onClick(View view) {
        // TODO Auto-generated method stub
        if (view instanceof TextView) {
            String content = ((TextView) view).getText().toString();
            if (!isRuler(content)) {

            } else if (stringBuffer.length() > 22 | content.equals("clear")) {
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
                } else if (content.matches("[\\+\\-\\*/]")) {
                    currentNumber.delete(0, currentNumber.length());
                    stringBuffer.append(content);
                }

                result.setText(stringBuffer);
                result.setSelection(stringBuffer.length());
                if (content.equals("=")) {
                    jisuan();
                    result.setText(stringBuffer);
                    currentNumber = new StringBuffer(stringBuffer.toString());
                }
            }
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
            BigDecimal bigDecimal = getResult(arrayList);
            stringBuffer.replace(arrayList.get(0), arrayList.get(3),
                    bigDecimal.toString());
            matcher = pattern.matcher(stringBuffer);
        }

    }

    private void jisuan() {
        // TODO Auto-generated method stub
        BigDecimal number = null;
        calculateProcess("[\\*/]");
        calculateProcess("[\\+\\-]");

    }

    private BigDecimal getResult(ArrayList<Integer> array) {
        BigDecimal decimal = null;
        String first = stringBuffer.substring(array.get(0), array.get(1));
        String second = stringBuffer.substring(array.get(2), array.get(3));
        String fuhao = stringBuffer.substring(array.get(1), array.get(2));
        BigDecimal number1 = new BigDecimal(first);
        BigDecimal number2 = new BigDecimal(second);
        if ("+".equals(fuhao)) {
            decimal = number1.add(number2);
        } else if ("-".equals(fuhao)) {
            decimal = number1.subtract(number2);
        } else if ("*".equals(fuhao)) {
            decimal = number1.multiply(number2);
            // decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else if ("/".equals(fuhao)) {
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
        String pre_content = stringBuffer.toString().substring(0, index - 1);
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(pre_content);
        while (matcher.find()) {
            String fuhao = matcher.group();
            end = matcher.end();
            start = matcher.start();
        }
        postion.add(start);
        postion.add(end);
        String after_content = stringBuffer.toString().substring(index,
                stringBuffer.length());
        matcher = pattern.matcher(after_content);
        if (matcher.find()) {
            String fuhao = matcher.group();
            end = matcher.end();
            start = matcher.start();
            postion.add(start + index);
            postion.add(end + index);
        }
        return postion;

    }

    private void clear() {
        // TODO Auto-generated method stub
        currentNumber.delete(0, currentNumber.length());
        stringBuffer.delete(0, stringBuffer.length());
        result.setText("");
    }

    public void onClick1(View v) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "zhangsan", Toast.LENGTH_LONG).show();
    }

    private BigDecimal yunsuan(String yunsuanfu) {

        BigDecimal pre = new BigDecimal(result.getText().toString());
        if (pre_yunsuanfu.equals("")) {
            result_jisuan = pre;
        } else {
            if ("+".equals(pre_yunsuanfu)) {
                result_jisuan = result_jisuan.add(pre);
            } else if ("-".equals(pre_yunsuanfu)) {
                result_jisuan = result_jisuan.subtract(pre);
            } else if ("*".equals(pre_yunsuanfu)) {
                result_jisuan = result_jisuan.multiply(pre);
            } else if ("/".equals(pre_yunsuanfu)) {
                try {
                    result_jisuan = result_jisuan.divide(pre);

                } catch (Exception e) {
                    // TODO: handle exception
                    result_jisuan = result_jisuan.divide(pre, 8,
                            BigDecimal.ROUND_HALF_DOWN);
                }

            } else if ("=".equals(yunsuanfu)) {
                result_jisuan = yunsuan(pre_yunsuanfu);
            }
        }
        return result_jisuan;
    }


}
