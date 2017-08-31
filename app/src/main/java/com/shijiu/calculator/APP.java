package com.shijiu.calculator;

import android.app.Application;
import android.util.Log;

import com.ppdai.loan.PPDLoanAgent;

/**
 * Created by yao on 2017/8/29.
 */

public class APP extends Application {
    private static final String LOAN_SDK_APPID="301b71dc5ec4433e9f925b31afbcfb3f";
    private static final String LOAN_SDK_SERVER_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdgG5LuxiPL8z6SpVFJ17lASgexvPhMxQw61CfQzVyT5O/91eHBeESPkTMbUfluqQtYoF6ldJibYogbd4IaXEjhb7MuIeIHqjD1YZav0bRuJJaoue+pfyyGcA8x5CXT1QqI/cI+5dshafSR3UB5yCMyuwdlj7X0c1f4G//eOOZfwIDAQAB";
    private static final String LOAN_SDK_CLIENT_PRIVATE_KEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKubXKWKbJeRHmg85TfZhmNl/+1LFJBlGszyoTT0xBL5WUJ1wwLDPu9yrzRLy6E6Q/6qINoQcl7iHL80cA08jdSZE02dWghSBlapWHEF6DV1PnHJ4P7vKYfkVSp8w1o8GlGFEpW3YqHa+Jb8MZRjFqmjfALAmQDzIvmg0WRjifgvAgMBAAECgYBADu62nMWMjo9bOeAWyQxxblHqsmwqupMWjju/GN9YddvsAymYjmmf2J+uVdzdgnJ/TeKVhC4tXRp+BYUOa9xFMRpdBZQjxr05aiEAvIX6S9B3+BScLxNTtrB2L7I6RW0E9mPyPfMYmP7o2Hkbc92/zmDnnyJ2GsMlGtcHDJd4AQJBAPridfZYdSBUcWU7HuQBSCsvmkhmO1AckxteD4lAMbSDewlAXyelR89d11l8mjdsXSqgsFIZTi2Tgvj7dDz1+G8CQQCvGxidnjD3az0W94kTQ/JLHgnQCjTuwJQ9nrwvMlpX9n+4K4e+u0Ypfu+cUv1iJqkZ6qmpfvlJ5odAknG0ElxBAkEA8nZ0PJ0mrRUO9h+Z7g7TGXGK8JdkkTrcbOGNsD6LCwLaoIlGrQEOEnmFyyUhwZiHvc/IN7/fDyr1s/0ITHYHHwJAKg4b718a65F57pYs+c+L1ba1LJ4G8ICYGeSR1dQGFrJBIn5x78ESrMyrx6O272fRnUPa07aHMNSJD10cqd7vAQJBALsDhv70Ngdo7sV0jqii56cuOBEwkP1xQpGd2xaQEgqcmZryuxC5+rWoAw0g2R5I2K6h5aM/DIebiOeqFP6F4zM=";

    private static final String TAG = "APP";
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化ppd sdk
        PPDLoanAgent.getInstance().initApplication(this);

        // appId 在管理后台申请的 appId
        // serverPublicKey 在管理后台申请的 服务端公钥
        // clientPrivateKey 在管理后台申请的 客户端私钥（字符串长度比 serverPublicKey 长）
        // NOTE: 请慎重修改 管理后台的 公私钥，改变了公私钥将导致接口调用失败
        // 最佳方案是通过你们的后台保存公私钥，通过你们自己的接口获取。
        PPDLoanAgent.getInstance().initConfig(this, LOAN_SDK_APPID, LOAN_SDK_SERVER_PUBLIC_KEY, LOAN_SDK_CLIENT_PRIVATE_KEY);
        Log.e(TAG, "onCreate: "+"clickssssssssssssssssssssss");
    }
}
