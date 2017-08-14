package com.shijiu.calculator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.shijiu.calculator.R;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity {

    private EditText print;

    private static String fistNumber = "0";// 第一次输入的值
    private static String secondNumber = "0";// 第二次输入的值
    private static String num = "0";// 显示的结果
    private static int flg = 0;// 结果累加一次
    public Counts take = null;

    private int[] btidTake = { R.id.txtdivide, R.id.txtx, R.id.txtmin,
            R.id.txttakesum };

    private Button[] buttonTake = new Button[btidTake.length];

    private int[] btidNum = { R.id.txt0, R.id.txt1, R.id.txt2, R.id.txt3,
            R.id.txt4, R.id.txt5, R.id.txt6, R.id.txt7, R.id.txt8, R.id.txt9,
            R.id.txtspl };
    private Button[] buttons = new Button[btidNum.length];

    private int[] btcl = { R.id.chars, R.id.charx, R.id.txtb, R.id.txtv };
    private Button[] btcls = new Button[btcl.length];
    private GridLayout gly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        gly=(GridLayout)findViewById(R.id.gly);
        print = (EditText) findViewById(R.id.print);
        print.setText("0");
        print.setEnabled(false);
        GetNumber get = new GetNumber();
        for (int i = 0; i < btidNum.length; i++) {
            buttons[i] = (Button) findViewById(btidNum[i]);
            buttons[i].setOnClickListener(get);
        }
        Compute cm = new Compute();
        for (int i = 0; i < btidTake.length; i++) {
            buttonTake[i] = (Button) findViewById(btidTake[i]);
            buttonTake[i].setOnClickListener(cm);
        }

        Button eq = (Button) findViewById(R.id.txteq);

        eq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flg == 0) {
                    secondNumber = print.getText().toString();
                    if (take == Counts.DIVIDE && secondNumber.equals("0")) {
                        print.setText("0不能为被除数");
                    } else {
                        num = take.Values(fistNumber, secondNumber);
                        fistNumber = num;
                        secondNumber = "0";
                        print.setText(num);
                        flg = 1;
                        gly.setBackgroundResource(R.drawable.jz);
                    }
                }
            }
        });
        Button cleargo = (Button) findViewById(R.id.cleargo);
        cleargo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (num.length() > 1) {
                    num = num.substring(0, num.length() - 1);
                } else {
                    num = "0";
                }
                print.setText(num);
            }
        });
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                num = "0";
                fistNumber = secondNumber = num;
                print.setText(num);
                flg = 0;
            }
        });
        for (int i = 0; i < btcl.length; i++) {
            btcls[i] = (Button) findViewById(btcl[i]);
            btcls[i].setOnClickListener(new OnTake());
        }
    }

    // 给 EditText赋值
    class GetNumber implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (flg == 1)
                num = "0";
            if (num.equals("0")) {
                print.setText("");
                num = v.getId() == R.id.txtspl ? "0" : "";
            }
            String txt = ((Button) v).getText().toString();
            boolean s = Pattern.matches("-*(\\d+).?(\\d)*", num + txt);
            num = s ? (num + txt) : num;
            gly.setBackgroundResource(R.drawable.js);
            print.setText(num);
        }
    }

    // 根据条件计算
    class Compute implements OnClickListener {

        @Override
        public void onClick(View arg0) {

            fistNumber = print.getText().toString();
            // TODO Auto-generated method stub
            switch (arg0.getId()) {
                case R.id.txttakesum:
                    take = Counts.ADD;
                    break;
                case R.id.txtmin:
                    take = Counts.MINUS;
                    break;
                case R.id.txtx:
                    take = Counts.MULTIPLY;
                    break;
                case R.id.txtdivide:
                    take = Counts.DIVIDE;
                    break;
            }
            num = "0";
            flg = 0;
            gly.setBackgroundResource(R.drawable.js);
        }

    }

    class OnTake implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.chars:
                    num = "-" + num;
                    break;
                case R.id.charx:
                    if(!num.equals("0"))
                        num = BigDecimal.valueOf(1).divide(new BigDecimal(num),12,BigDecimal.ROUND_UP).stripTrailingZeros()
                                .toString();
                    break;
                case R.id.txtb:
                    num = new BigDecimal(num).divide(BigDecimal.valueOf(100),12,BigDecimal.ROUND_UP).stripTrailingZeros()
                            .toString();
                    break;
                case R.id.txtv:
                    Double numss = Math.sqrt(new BigDecimal(num).doubleValue());
                    int stratindex=numss.toString().contains(".")?numss.toString().indexOf("."):0;
                    num = numss.toString().length()>13?numss.toString().substring(0, 12+stratindex):numss.toString();
            }
            print.setText(num);
            flg=0;
            num = "0";

        }

    }



}