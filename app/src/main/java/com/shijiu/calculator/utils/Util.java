package com.shijiu.calculator.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.shijiu.calculator.bean.MortgageBean;

import java.io.Serializable;

/**
 * Created by yao on 2017/8/9.
 */

public class Util {
    public static void forwardActivity(Context context, Class clas){
        Intent intent = new Intent();
        intent.setClass(context,clas);
        context.startActivity(intent);
    }

    public static void forwardActivity(Context context, Class clas,MortgageBean o){
        Intent intent = new Intent();
        intent.setClass(context,clas);
        intent.putExtra("bean", o);
        context.startActivity(intent);
    }

    public static boolean isNull(TextView textView){
        String s = textView.getText().toString().trim();
        if (s.equals("")){
            return false;
        }else {
            return true;
        }
    }

    public static boolean isNull(EditText textView){
        String s = textView.getText().toString().trim();
        if (s.equals("")){
            return false;
        }else {
            return true;
        }
    }

    public static String getValue(TextView textView){
        String s = textView.getText().toString().trim();
        return s;
    }

    public static String getValue(EditText textView){
        String s = textView.getText().toString().trim();
        return s;
    }
}
