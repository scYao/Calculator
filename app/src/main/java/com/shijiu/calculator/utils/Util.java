package com.shijiu.calculator.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yao on 2017/8/9.
 */

public class Util {
    public static void forwardActivity(Context context, Class clas){
        Intent intent = new Intent();
        intent.setClass(context,clas);
        context.startActivity(intent);
    }
}
