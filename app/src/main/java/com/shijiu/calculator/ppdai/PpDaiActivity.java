package com.shijiu.calculator.ppdai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppdai.loan.PPDLoanAgent;
import com.ppdai.open.core.AuthInfo;
import com.ppdai.open.core.OpenApiClient;
import com.ppdai.open.core.PropertyObject;
import com.ppdai.open.core.Result;
import com.ppdai.open.core.ValueTypeEnum;
import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.PpDaiAdapter;
import com.shijiu.calculator.bean.LoanBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PpDaiActivity extends AppCompatActivity {
    private ImageView back;
    private TextView title;
    private RecyclerView recyclerView;
    private List<LoanBean> beanList = new ArrayList<>();
    private PpDaiAdapter adapter;
    private static final String TAG = "PpDaiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_dai);

        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("贷款");

        initData();
    }

    private void onEnterSDK(){
        //启动SDK
        // 注意：在调用该方法之前，请先正确设置 initConfig 方法。
//        PPDLoanAgent.getInstance().initLaunch(this);
        String mobile = ""; // user mobile
        PPDLoanAgent.getInstance().initLaunch(this, mobile);
        Log.e(TAG, "onEnterSDK: " );

//        try {
//            //授权信息
//            AuthInfo authInfo = null;
//            //请求url 用户注册接口
//            String url = "http://gw.open.ppdai.com/auth/registerservice/register";
//            Result result = OpenApiClient.send(url
//                    , new PropertyObject("Mobile", "15056014715", ValueTypeEnum.String)
//                    , new PropertyObject("Email", "2247523672@qq.com", ValueTypeEnum.String)
//                    , new PropertyObject("Role", 12, ValueTypeEnum.Int32));
//            System.out.println(String.format("返回结果:%s", result.isSucess() ? result.getContext() : result.getErrorMessage()));
//
//            //需要授权token
//            //step 1 跳转到AC的oauth2.0联合登录 https://ac.ppdai.com/oauth2/login?AppID=XXXXXXXXXXXXX&ReturnUrl=http://mysite.com/auth/gettoken
//            //setp 2 登录成功后 oauth2.0 跳转到http://mysite.com/auth/gettoken?code=XXXXXXXXXXXXXXXXXXXXXXXXXX
//            String code = "your_code";//通过用户授权获取的code
//            authInfo = OpenApiClient.authorize(code);
//            String accessToken = authInfo.getAccessToken();
//            result = OpenApiClient.send("http://gw.open.ppdai.com/auth/LoginService/AutoLogin", accessToken
//                    , new PropertyObject("Timestamp", new Date(), ValueTypeEnum.DateTime));
//            System.out.println(String.format("返回结果:%s", result.isSucess() ? result.getContext() : result.getErrorMessage()));
//
//            //刷新token
//            authInfo = OpenApiClient.refreshToken(authInfo.getOpenID(), authInfo.getRefreshToken());
//            System.out.println(String.format("返回结果:%s", authInfo.getAccessToken()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void initData() {
        LoanBean bean = new LoanBean();
        bean.setName("拍拍贷");
        bean.setImagePath(R.mipmap.ic_launcher);
        beanList.add(bean);

        adapter = new PpDaiAdapter(this,beanList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new PpDaiAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Log.e(TAG, "onItemClick: " );
                        onEnterSDK();
                        break;
                }
            }
        });

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerView_ppd);
    }
}
