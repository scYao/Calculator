package com.shijiu.calculator.area;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.PopAdapter;
import com.shijiu.calculator.bean.UnitBean;
import com.shijiu.calculator.length.LengthActivity;

import java.util.ArrayList;
import java.util.List;

public class AreaActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView title;

    public TextView spinner1;
    private TextView spinner2;

    private WindowManager.LayoutParams params;

    private EditText input;
    private EditText result;

    private TextView btn_0;
    private TextView btn_1;
    private TextView btn_2;
    private TextView btn_3;
    private TextView btn_4;
    private TextView btn_5;
    private TextView btn_6;
    private TextView btn_7;
    private TextView btn_8;
    private TextView btn_9;

    private TextView btn_equal;
    private TextView btn_del;
    private TextView btn_point;

    private static int unit1=1;
    private static int unit2=1;

    private static final String TAG = "AreaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        initView();
        title.setText("面积转换");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    private void initView() {
        title = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);

        spinner1 = (TextView) findViewById(R.id.id_spinner1);
        spinner2 = (TextView) findViewById(R.id.id_spinner2);
        spinner1.setOnClickListener(onClickListener);
        spinner2.setOnClickListener(onClickListener);

        input = (EditText) findViewById(R.id.id_input);
        result = (EditText) findViewById(R.id.id_result);

        btn_0 = (TextView) findViewById(R.id.btn_0);
        btn_1 = (TextView) findViewById(R.id.btn_1);
        btn_2 = (TextView) findViewById(R.id.btn_2);
        btn_3 = (TextView) findViewById(R.id.btn_3);
        btn_4 = (TextView) findViewById(R.id.btn_4);
        btn_5 = (TextView) findViewById(R.id.btn_5);
        btn_6 = (TextView) findViewById(R.id.btn_6);
        btn_7 = (TextView) findViewById(R.id.btn_7);
        btn_8 = (TextView) findViewById(R.id.btn_8);
        btn_9 = (TextView) findViewById(R.id.btn_9);

        btn_equal = (TextView) findViewById(R.id.btn_equal);
        btn_del = (TextView) findViewById(R.id.btn_del);
        btn_point = (TextView) findViewById(R.id.btn_point);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_equal.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_point.setOnClickListener(this);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_spinner1:
                    showPopFormBottom(1);
                    break;

                case R.id.id_spinner2:
                    showPopFormBottom(2);
                    break;
            }
        }
    };

    public void showPopFormBottom(int i) {
        AreaActivity.TakePhotoPopWin takePhotoPopWin = new AreaActivity.TakePhotoPopWin(this, i);
//        设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view1), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        params = getWindow().getAttributes();
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        takePhotoPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

    }


    @Override
    public void onClick(View view) {
        String in = input.getText().toString();

        Log.e(TAG, "onClick: sssssssssssssssssss" + in);
        switch (view.getId()) {

            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                input.setText(in + ((TextView) view).getText() + "");
                getResult(unit1, unit2);
                break;

            case R.id.btn_del:
                if (in != null && !in.equals("")) {
                    input.setText(in.substring(0, in.length() - 1));
                    result.setText(in.substring(0, in.length() - 1));
                }
                break;
            case R.id.btn_equal:
                input.setText("");
                result.setText("");
                break;

        }
    }

    private void getResult(int i1, int i2) {
        double re = Double.parseDouble(input.getText().toString());
        switch (i1) {
            case 0:
                double re0 = re / 1000000;
                toMeter(i2, re0);
                break;
            case 1:
                double re1 = re;
                toMeter(i2, re1);
                break;
            case 2:
                double re2 = re * 100;
                toMeter(i2, re2);
                break;
            case 3:
                double re3 = re * 10000;
                toMeter(i2, re3);
                break;
            case 4:
                double re4 = re * 1000000;
                toMeter(i2, re4);
                break;
//            case 5:
//                double re5 = re * 10000;
//                toMeter(i2, re5);
//                break;
//            case 6:
//                double re6 = re * 100000;
//                toMeter(i2, re6);
//                break;
//            case 7:
//                double re7 = re * 1000000;
//                toMeter(i2, re7);
//                break;
        }

    }

    private void toMeter(int i, double d) {

        switch (i) {
            case 0:
                double d0 = d / 1000000;
                result.setText(d0 + "");
                break;
            case 1:
                double d1 = d;
                result.setText(d1 + "");
                break;
            case 2:
                double d2 = d * 100;
                result.setText(d2 + "");
                break;
            case 3:
                double d3 = d * 10000;
                result.setText(d3 + "");
                break;
            case 4:
                double d4 = d * 1000000;
                result.setText(d4 + "");
                break;
//            case 5:
//                double d5 = d * 10000;
//                result.setText(d5 + "");
//                break;
//            case 6:
//                double d6 = d * 100000;
//                result.setText(d6 + "");
//                break;
//            case 7:
//                double d7 = d * 100000;
//                result.setText(d7 + "");
//                break;
        }
    }

    public class TakePhotoPopWin extends PopupWindow {


        private View view;
        private List<UnitBean> beanList = new ArrayList<>();
        private PopAdapter adapter;
        private RecyclerView recylerView;
        private TextView btn_cancel;
        private RecyclerView.LayoutManager layoutManager;


        public TakePhotoPopWin(Context mContext, final int i) {
            view = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);
            initData();


            btn_cancel = view.findViewById(R.id.id_cancel);
            recylerView = view.findViewById(R.id.id_recycleListView);
            layoutManager = new LinearLayoutManager(mContext);
            adapter = new PopAdapter(beanList, mContext);
            recylerView.setLayoutManager(layoutManager);
            recylerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new PopAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (i == 1) {
                        unit1 = position;
                        spinner1.setText(beanList.get(position).getUnit());
                        beanList.get(position).setImgae(R.mipmap.tick);
                        adapter.notifyDataSetChanged();
                        dismiss();
                    } else {
                        unit2 = position;
                        spinner2.setText(beanList.get(position).getUnit());
                        beanList.get(position).setImgae(R.mipmap.tick);
                        adapter.notifyDataSetChanged();
                        dismiss();
                    }

                }
            });


            // 取消按钮
            btn_cancel.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // 销毁弹出框
                    dismiss();
                }
            });


            // 设置外部可点击
            this.setOutsideTouchable(true);
            // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            this.view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {

                    int height = view.findViewById(R.id.pop_layout).getTop();

                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            dismiss();
                        }
                    }
                    return true;
                }
            });


    /* 设置弹出窗口特征 */
            // 设置视图
            this.setContentView(this.view);
            // 设置弹出窗体的宽和高
            this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
            this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

            // 设置弹出窗体可点击
            this.setFocusable(true);

            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            // 设置弹出窗体的背景
            this.setBackgroundDrawable(dw);

            // 设置弹出窗体显示时的动画，从底部向上弹出
            this.setAnimationStyle(R.style.take_photo_anim);

        }

        private void initData() {
//            beanList.add(new UnitBean("公顷 ha"));
//            beanList.add(new UnitBean("公亩 are"));
            beanList.add(new UnitBean("平方千米 km2"));
            beanList.add(new UnitBean("平方米 m2"));
            beanList.add(new UnitBean("平方分米 dm2"));
            beanList.add(new UnitBean("平方厘米 cm2"));
            beanList.add(new UnitBean("平方毫米 mm2"));
//            beanList.add(new UnitBean("平方微米 um2"));
//            beanList.add(new UnitBean("平方纳米 nm2"));
//            beanList.add(new UnitBean("平方皮米 pm2"));
//            beanList.add(new UnitBean("英亩 acre"));
//            beanList.add(new UnitBean("海里 nmi"));
        }
    }
}
