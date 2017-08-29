package com.shijiu.calculator;

import android.app.Application;

import com.ppdai.loan.PPDLoanAgent;

/**
 * Created by yao on 2017/8/29.
 */

public class APP extends Application {
    private static final String LOAN_SDK_APPID="";
    private static final String LOAN_SDK_SERVER_PUBLIC_KEY="";
    private static final String LOAN_SDK_CLIENT_PRIVATE_KEY="";
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
    }
}
