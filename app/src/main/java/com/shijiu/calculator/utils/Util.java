package com.shijiu.calculator.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shijiu.calculator.bean.MortgageBean;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

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

    public static boolean isEmpty(EditText editText){
        String s = editText.getText().toString().trim();

        if (s.equals("")){
            return false;
        }else {
            return true;
        }
    }


    public static void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3500);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }


    public static String doubleTrans(double d){
        if(Math.round(d)-d==0){
            return String.valueOf((long)d);
        }
        return String.valueOf(d);
    }
}
