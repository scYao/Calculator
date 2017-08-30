package com.shijiu.calculator;

import android.app.Application;
import android.util.Log;

import com.ppdai.loan.PPDLoanAgent;

/**
 * Created by yao on 2017/8/29.
 */

public class APP extends Application {
    private static final String LOAN_SDK_APPID="301b71dc5ec4433e9f925b31afbcfb3f";
    private static final String LOAN_SDK_SERVER_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCF9DSEbsxGb8FOkZCHpr+dyhlj1/v0FuI6WvaRis72rPTpiVtFx/vRuD3cmc4DoD+2FCqvhmAvg5GxdpJImya98Zdr9Ov1lH1QCUZgTboQCunvlNMZLQtyuJ+H68QgSWrQZ+PcfJlT+Lx83p3H4NnaIm624EtRWPXdGrYkwBbYyQIDAQAB";
    private static final String LOAN_SDK_CLIENT_PRIVATE_KEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALXrqNB0UJkzeSubeMe9O5Ptpo8kldZXxz6dxBUXfoVMbAb4+vYbgpnABES29rEcxcmir/yY/gWlEFKGNf89GWe3r5LGiU+aw+VBujq4Y+UZ3X1LMaOJ275LP/CSkIEACYXsd9wPMbJc9C/pM1X0GnH5VCPjz6S94nqd1zrfnF4/AgMBAAECgYA8+tGLjnG16NslEuhZi8T8wRyjvs+7GZFgG+Iub8GpTQSpEayN8orEnujPX8UxeUOTY0U6ls4PdlUSVIqlFAX/fW2hQFzM2lD+NRpZYFvSx9sbkMWHL6gVTnruZ9qwUlsXU2HuThmiT07LVDPEd17MVFULoaXA5PZOxvAH+HxiAQJBAOIQBD1eKCnK97F7mNvjX2gAexSkOTiAgfe4jnCv9MqrlpUhupMwZ9TsfDA8tGibUxD6InvphiObkD8LRoHS5W8CQQDOAyNZRdlrxtv3QOMuexQaHXtE5YPNK3IifSop+Z+iTxeh/wmeOEo9n1nRNnvAaUM6v2+1QsHPhMY7AviONswxAkAP/AXwuOmqGtnsyMqEMc2bMv2A9iO9pQiLlZcszKRIeF2LafkhUzjkS1x0pLY091amSqmJjXeYqPVIH+n6YAktAkEAjbaG6HAuIf+HbMxL+rtblqNVGo2vXsFWH/dtu7YlY6d4oE0qNpaOK1mklVAfTLqcLptlopCrda7mFc3zYRC84QJBALg5Sh/1w24ZTfnhVls779CfN8UqF7E+JrYpQ3pHsQAAlM9RaUaEtRo08TG6PBfOcANf5kn0TiwSTHV8bIWsl2c=";

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
