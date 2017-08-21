package com.shijiu.calculator.length;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.PopAdapter;
import com.shijiu.calculator.bean.UnitBean;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class LengthActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;

    public TextView spinner1;
    private TextView spinner2;
    private List<String> strings1 = new ArrayList<>();
    private List<String> strings2 = new ArrayList<>();
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
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

    private static final String TAG = "LengthActivity";
    private static int unit1 = 1;
    private static int unit2 = 1;


    private List<UnitBean> beanList1 = new ArrayList<>();
    private List<UnitBean> beanList2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        title.setText("长度转换");
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
                    showPopFormBottom(1, beanList1);
                    break;

                case R.id.id_spinner2:
                    showPopFormBottom(2, beanList2);
                    break;
            }
        }
    };


    public void showPopFormBottom(int i, List<UnitBean> beanList) {
        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this, i, beanList);
//        设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
                if (in.length() == 1 && in.equals("0")) {
                    input.setText(((TextView) view).getText() + "");
                } else {
                    input.setText(in + ((TextView) view).getText() + "");
                }

                getResult(unit1, unit2);
                break;
            case R.id.btn_point:
                if (!in.equals("")) {

                    input.setText(in + ((TextView) view).getText() + "");
                    getResult(unit1, unit2);
                }

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

    public void getResult(int i1, int i2) {


        BigDecimal re = BigDecimal.valueOf(Double.parseDouble(input.getText().toString()));
        Log.e(TAG, "getResult: " + re);

        switch (i1) {
            case 0:
//                double re0 = re * 1000;
                BigDecimal re0 = Cac1(re, "1000");
                toMeter(i2, re0);
                break;
            case 1:
                BigDecimal re1 = re;
                toMeter(i2, re1);
                break;
            case 2:
//                double re2 = re / 10;
                BigDecimal re2 = Cac2(re, "10");
                toMeter(i2, re2);
                break;
            case 3:
//                double re3 = re / 100;
                BigDecimal re3 = Cac2(re, "100");
                toMeter(i2, re3);
                break;
            case 4:
//                double re4 = re / 1000;
                BigDecimal re4 = Cac2(re, "1000");
                toMeter(i2, re4);
                break;
            case 5:
//                double re5 = re / 1000000;
                BigDecimal re5 = Cac2(re, "1000000");
                toMeter(i2, re5);
                break;
            case 6:
//                double re6 = re / 1000000000;
                BigDecimal re6 = Cac2(re, "1000000000");
                toMeter(i2, re6);
                break;
            case 7:
                BigDecimal re7 = Cac2(re, "1000000000000");
                toMeter(i2, re7);
                break;

            case 8:
                BigDecimal re8 = Cac1(re, "1852");
                toMeter(i2, re8);
                break;
            case 9:
                BigDecimal re9 = Cac1(re, "1609.344");
                toMeter(i2, re9);
                break;

            case 10:
                BigDecimal re10 = Cac1(re, "201.168");
                toMeter(i2, re10);
                break;
        }

    }

    private void toMeter(int i, BigDecimal d) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        switch (i) {
            case 0:
//                double d0 = d / 1000;
                BigDecimal d0 = Cac2(d, "1000");
//                result.setText(decimalFormat.format(d0));
                result.setText(d0.stripTrailingZeros().toPlainString());
                break;
            case 1:
                BigDecimal d1 = d;

                result.setText(d1.stripTrailingZeros().toPlainString());
                break;
            case 2:

//                double d2 = d * 10;
                BigDecimal d2 = Cac1(d, "10");
//                result.setText(decimalFormat.format(d2));
                result.setText(d2.stripTrailingZeros().toPlainString());
                break;
            case 3:
//                double d3 = d * 100;
                BigDecimal d3 = Cac1(d, "100");
//                result.setText(decimalFormat.format(d3));
                result.setText(d3.stripTrailingZeros().toPlainString());
                break;
            case 4:
//                double d4 = d * 1000;
                BigDecimal d4 = Cac1(d, "1000");
//                result.setText(decimalFormat.format(d4));
                result.setText(d4.stripTrailingZeros().toPlainString());
                break;
            case 5:
//                double d5 = d * 1000000;
                BigDecimal d5 = Cac1(d, "1000000");
                result.setText(d5.stripTrailingZeros().toPlainString());
                break;
            case 6:
//                double d6 = d * 1000000000;
                BigDecimal d6 = Cac1(d, "1000000000");
//                result.setText(decimalFormat.format(d6));
                result.setText(d6.stripTrailingZeros().toPlainString());
                break;
            case 7:
                BigDecimal d7 = Cac1(d, "1000000000000");
                result.setText(d7.stripTrailingZeros().toPlainString());
                break;

            case 8:
                BigDecimal d8 = Cac2(d, "1852");
                result.setText(d8.stripTrailingZeros().toPlainString());
                break;
            case 9:
                BigDecimal d9 = Cac2(d, "1609.344");
                result.setText(d9.stripTrailingZeros().toPlainString());
                break;

            case 10:
                BigDecimal d10 = Cac2(d, "201.168");
                result.setText(d10.stripTrailingZeros().toPlainString());
                break;
        }
    }

//    private String Cac1(double d, String s) {
//        BigDecimal bigDecimal1 = new BigDecimal(d);
//        BigDecimal bigDecimal2 = new BigDecimal(s);
//        Double d1 = bigDecimal1.multiply(bigDecimal2).doubleValue();
//        String str = new BigDecimal(d1.toString()).toString();
//        return str;
//    }
//
//    private String Cac2(double d, String s) {
//        BigDecimal bigDecimal1 = new BigDecimal(d);
//        BigDecimal bigDecimal2 = new BigDecimal(s);
//        Double d1 = bigDecimal1.divide(bigDecimal2).doubleValue();
//        String str = new BigDecimal(d1.toString()).toString();
//        return str;
//    }

//    private Double Cac1(double d, String s) {
//        BigDecimal bigDecimal1 = new BigDecimal(d);
//        BigDecimal bigDecimal2 = new BigDecimal(s);
//        Double d1 = bigDecimal1.multiply(bigDecimal2).doubleValue();
//
//        return d1;
//    }
//
//    private Double Cac2(double d, String s) {
//        BigDecimal bigDecimal1 = new BigDecimal(d);
//        BigDecimal bigDecimal2 = new BigDecimal(s);
//        Double d1 = bigDecimal1.divide(bigDecimal2).doubleValue();
//
//        return d1;
//    }

    private BigDecimal Cac1(BigDecimal d, String s) {
        BigDecimal bigDecimal1 = d;
        BigDecimal bigDecimal2 = new BigDecimal(s);

        return bigDecimal1.multiply(bigDecimal2);
    }

    private BigDecimal Cac2(BigDecimal d, String s) {
        BigDecimal bigDecimal1 = d;
        BigDecimal bigDecimal2 = new BigDecimal(s);
//        BigDecimal bigDecimal =bigDecimal1.divide(bigDecimal2);
//        bigDecimal.setScale(10,BigDecimal.ROUND_HALF_UP);

        return bigDecimal1.divide(bigDecimal2,6,BigDecimal.ROUND_HALF_UP);
//        return bigDecimal;
    }

    private static String big(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }


    public class TakePhotoPopWin extends PopupWindow {


        private View view;

        private PopAdapter adapter;
        private RecyclerView recylerView;
        private TextView btn_cancel;
        private RecyclerView.LayoutManager layoutManager;


        public TakePhotoPopWin(Context mContext, final int i, final List<UnitBean> beanList) {
            view = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);

            if (beanList.size() == 0) {
                initData();
            }


            btn_cancel = view.findViewById(R.id.id_cancel);
            recylerView = view.findViewById(R.id.id_recycleListView);
            layoutManager = new LinearLayoutManager(mContext);
            adapter = new PopAdapter(beanList, mContext);
            recylerView.setLayoutManager(layoutManager);
            recylerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            Log.e(TAG, "TakePhotoPopWin: " + beanList.toString());
            adapter.setOnItemClickListener(new PopAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (i == 1) {
                        unit1 = position;
                        spinner1.setText(beanList.get(position).getUnit());
                        for (int j = 0; j < beanList.size(); j++) {
                            if (j == position) {
                                beanList.get(j).setImgae(R.mipmap.tick);
                                adapter.notifyItemChanged(position);
                            } else {
                                beanList.get(j).setImgae(null);
                            }
                        }

                        Log.e(TAG, "onItemClick: " + beanList.toString());


                        dismiss();
                    } else {
                        unit2 = position;
                        spinner2.setText(beanList.get(position).getUnit());
                        for (int j = 0; j < beanList.size(); j++) {
                            if (j == position) {
                                beanList.get(j).setImgae(R.mipmap.tick);
                                adapter.notifyItemChanged(position);
                            } else {
                                beanList.get(j).setImgae(null);
                            }
                        }
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
            beanList1.add(new UnitBean("千米 km"));
            beanList1.add(new UnitBean("米 m"));
            beanList1.add(new UnitBean("分米 dm"));
            beanList1.add(new UnitBean("厘米 cm"));
            beanList1.add(new UnitBean("毫米 mm"));
            beanList1.add(new UnitBean("微米 um"));
            beanList1.add(new UnitBean("纳米 nm"));
            beanList1.add(new UnitBean("皮米 pm"));
            beanList1.add(new UnitBean("海里 nmi"));
            beanList1.add(new UnitBean("英里 mi"));
            beanList1.add(new UnitBean("弗隆 fur"));

            beanList2.add(new UnitBean("千米 km"));
            beanList2.add(new UnitBean("米 m"));
            beanList2.add(new UnitBean("分米 dm"));
            beanList2.add(new UnitBean("厘米 cm"));
            beanList2.add(new UnitBean("毫米 mm"));
            beanList2.add(new UnitBean("微米 um"));
            beanList2.add(new UnitBean("纳米 nm"));
            beanList2.add(new UnitBean("皮米 pm"));
            beanList2.add(new UnitBean("海里 nmi"));
            beanList2.add(new UnitBean("英里 mi"));
            beanList2.add(new UnitBean("弗隆 fur"));
        }
    }


}
