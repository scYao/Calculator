package com.shijiu.calculator.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by yao on 2017/8/9.
 */

public class Util {
    public static void forwardActivity(Context context, Class clas){
        Intent intent = new Intent();
        intent.setClass(context,clas);
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
}
