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
import com.shijiu.calculator.utils.SpaceItemDecoration;
import com.shijiu.calculator.utils.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    private int unit1 = 1;
    private int unit2 = 1;

    private static final String TAG = "AreaActivity";
    private List<UnitBean> beanList1 = new ArrayList<>();
    private List<UnitBean> beanList2 = new ArrayList<>();

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
                    showPopFormBottom(1, beanList1);
                    break;

                case R.id.id_spinner2:
                    showPopFormBottom(2, beanList2);
                    break;
            }
        }
    };

    public void showPopFormBottom(int i, List<UnitBean> beanList) {
        AreaActivity.TakePhotoPopWin takePhotoPopWin = new AreaActivity.TakePhotoPopWin(this, i, beanList);
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
                getResult(unit1, unit2);
                break;
            case R.id.btn_equal:
                input.setText("");
                result.setText("");
                break;

        }
    }

    private void getResult(int i1, int i2) {
        if (input.getText().toString().equals("")) {
            return;
        }
        BigDecimal re = BigDecimal.valueOf(Double.parseDouble(input.getText().toString()));
        switch (i1) {
            case 0:
                BigDecimal re0 = Cac1(re, "1000000");
                toMeter(i2, re0);
                break;
            case 1:
                BigDecimal re1 = re;
                toMeter(i2, re1);
                break;
            case 2:
//                double re2 = re / 100;
                BigDecimal re2 = Cac2(re, "100");
                toMeter(i2, re2);
                break;
            case 3:
//                double re3 = re / 10000;
                BigDecimal re3 = Cac2(re, "10000");
                toMeter(i2, re3);
                break;
            case 4:
                BigDecimal re4 = Cac2(re, "1000000");
//                double re4 = re / 1000000;
                toMeter(i2, re4);
                break;
            case 5:
                BigDecimal re5 = Cac2(re, "1000000000000");
                toMeter(i2, re5);
                break;
            case 6:
                BigDecimal re6 = Cac1(re, "10000");
                toMeter(i2, re6);
                break;
            case 7:
                BigDecimal re7 = Cac1(re, "100");
                toMeter(i2, re7);
                break;
            case 8:
                BigDecimal re8 = Cac1(re, "4046.856");
                toMeter(i2, re8);
                break;
            //平方英里 平方码 平方英尺
            case 9:
                BigDecimal re9 = Cac1(re, "2589988.110336");
                toMeter(i2, re9);
                break;
            case 10:
                BigDecimal re10 = Cac1(re, "0.8361274");
                toMeter(i2, re10);
                break;
            case 11:
                BigDecimal re11 = Cac1(re, "0.092903");
                toMeter(i2, re11);
                break;
            //平方英寸 平方竿
            case 12:
                BigDecimal re12 = Cac1(re, "0.0006452");
                toMeter(i2, re12);
                break;
            case 13:
                BigDecimal re13 = Cac1(re, "25.2928526");
                toMeter(i2, re13);
                break;
            //顷 亩 平方尺
            case 14:
//                BigDecimal re14 = Cac1(re, "66666.6666666666");
//                double fen1 = 20000/3;
                BigDecimal re14 = Cac2(Cac1(re,"200000"), "3");
                if (unit2 ==15){
                    re14 = Cac1(re,"100");
                }
                toMeter(i2, re14);
                break;
            case 15:
//                BigDecimal re15 = Cac1(re, "666.6666666666");
//                double fen2 = 2000 /3;
                BigDecimal re15 = Cac2(Cac1(re,"2000"), "3");
                if (unit2==14){
                    re15 = re;
                }
                toMeter(i2, re15);
                break;
            case 16:
                BigDecimal re16 = Cac1(re, "0.1111111");
                toMeter(i2, re16);
                break;
            //平方寸 平方公里
            case 17:
                BigDecimal re17 = Cac2(re, "900");
                toMeter(i2, re17);
                break;
            case 18:
                BigDecimal re18 = Cac1(re, "1000000");
                toMeter(i2, re18);
                break;

        }

    }

    private void toMeter(int i, BigDecimal d) {

        switch (i) {
            case 0:
                BigDecimal d0 = Cac2(d, "1000000");
                showText(d0);
                break;
            case 1:
                BigDecimal d1 = d;
                showText(d1);
                break;
            case 2:
                BigDecimal d2 = Cac1(d, "100");
                showText(d2);
                break;
            case 3:
                BigDecimal d3 = Cac1(d, "10000");
                showText(d3);
                break;
            case 4:
                BigDecimal d4 = Cac1(d, "1000000");
                showText(d4);
                break;
            case 5:
                BigDecimal re5 = Cac1(d, "1000000000000");
                showText(re5);
                break;
            case 6:
                BigDecimal re6 = Cac2(d, "10000");
                showText(re6);
                break;
            case 7:
                BigDecimal re7 = Cac2(d, "100");
                showText(re7);
                break;
            case 8:
                BigDecimal re8 = Cac2(d, "4046.856");
                showText(re8);
                break;
            //平方英里 平方码 平方英尺
            case 9:
                BigDecimal re9 = Cac2(d, "2589988.110336");
                showText(re9);
                break;
            case 10:
                BigDecimal re10 = Cac2(d, "0.8361274");
                showText(re10);
                break;
            case 11:
                BigDecimal re11 = Cac2(d, "0.092903");
                showText(re11);
                break;
            //平方英寸 平方竿
            case 12:
                BigDecimal re12 = Cac2(d, "0.0006452");
                showText(re12);
                break;
            case 13:
                BigDecimal re13 = Cac2(d, "25.2928526");
                showText(re13);
                break;
            //顷 亩 平方尺
            case 14:
//                double fen1 = 20000/3;
                BigDecimal re14 = Cac2(Cac1(d,"3"), "200000");
                if (unit1 == 15){
                    re14 = Cac2(d,"100");
                }
                showText(re14);
                break;
            case 15:
//                double fen2 = 2000/3;
                BigDecimal re15 = Cac2(Cac1(d,"3"), "2000");
                if (unit1 == 14){
                    re15 = d;
                }
                showText(re15);
                break;
            case 16:
                BigDecimal re16 = Cac2(d, "0.1111111");
                showText(re16);
                break;
            //平方寸 平方公里
            case 17:
                BigDecimal re17 = Cac1(d, "900");
                showText(re17);
                break;
            case 18:
                BigDecimal re18 = Cac2(d, "1000000");
                showText(re18);
                break;
        }
    }

    private void showText(BigDecimal bigDecimal) {
        if (bigDecimal.stripTrailingZeros().toPlainString().length() < 10) {
            result.setText(bigDecimal.stripTrailingZeros().toPlainString());
        } else {
            result.setText(bigDecimal.stripTrailingZeros().toString().replace("E","e"));
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

    private BigDecimal Cac1(BigDecimal d, String s) {
        BigDecimal bigDecimal1 = d;
        BigDecimal bigDecimal2 = new BigDecimal(s);

        return bigDecimal1.multiply(bigDecimal2);
    }

    private BigDecimal Cac2(BigDecimal d, String s) {
        BigDecimal bigDecimal1 = d;
        BigDecimal bigDecimal2 = new BigDecimal(s);

        return bigDecimal1.divide(bigDecimal2, 12, BigDecimal.ROUND_HALF_UP);
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
            int in = 0;
            for (int j = 0; j < beanList.size(); j++) {
                UnitBean bean = beanList.get(j);
                if (bean.getImgae() == null) {
                    in = 0;
                } else {
                    in = 1;
                    break;
                }
            }
            if (in == 0) {
                beanList.get(1).setImgae(R.mipmap.tick);
            }

            btn_cancel = view.findViewById(R.id.id_cancel);
            recylerView = view.findViewById(R.id.id_recycleListView);
            layoutManager = new LinearLayoutManager(mContext);
            adapter = new PopAdapter(beanList, mContext);
            recylerView.setLayoutManager(layoutManager);
            recylerView.addItemDecoration(new SpaceItemDecoration(10));
            recylerView.setAdapter(adapter);

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

                    getResult(unit1, unit2);

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
            this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
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
            beanList1.add(new UnitBean("平方千米 km²"));
            beanList1.add(new UnitBean("平方米 m²"));
            beanList1.add(new UnitBean("平方分米 dm²"));
            beanList1.add(new UnitBean("平方厘米 cm²"));
            beanList1.add(new UnitBean("平方毫米 mm²"));
            beanList1.add(new UnitBean("平方微米 μm²"));
            beanList1.add(new UnitBean("公顷 ha"));
            beanList1.add(new UnitBean("公亩 are"));
            beanList1.add(new UnitBean("英亩 acre"));
            beanList1.add(new UnitBean("平方英里 mile²"));
            beanList1.add(new UnitBean("平方码 yd²"));
            beanList1.add(new UnitBean("平方英尺 ft²"));
            beanList1.add(new UnitBean("平方英寸 in²"));
            beanList1.add(new UnitBean("平方竿 rd²"));
            beanList1.add(new UnitBean("顷 qing"));
            beanList1.add(new UnitBean("亩 mu"));
            beanList1.add(new UnitBean("平方尺 chi²"));
            beanList1.add(new UnitBean("平方寸 cun²"));
            beanList1.add(new UnitBean("平方公里 gongli²"));

            beanList2.add(new UnitBean("平方千米 km²"));
            beanList2.add(new UnitBean("平方米 m²"));
            beanList2.add(new UnitBean("平方分米 dm²"));
            beanList2.add(new UnitBean("平方厘米 cm²"));
            beanList2.add(new UnitBean("平方毫米 mm²"));
            beanList2.add(new UnitBean("平方微米 μm²"));
            beanList2.add(new UnitBean("公顷 ha"));
            beanList2.add(new UnitBean("公亩 are"));
            beanList2.add(new UnitBean("英亩 acre"));
            beanList2.add(new UnitBean("平方英里 mile²"));
            beanList2.add(new UnitBean("平方码 yd²"));
            beanList2.add(new UnitBean("平方英尺 ft²"));
            beanList2.add(new UnitBean("平方英寸 in²"));
            beanList2.add(new UnitBean("平方竿 rd²"));
            beanList2.add(new UnitBean("顷 qing"));
            beanList2.add(new UnitBean("亩 mu"));
            beanList2.add(new UnitBean("平方尺 chi²"));
            beanList2.add(new UnitBean("平方寸 cun²"));
            beanList2.add(new UnitBean("平方公里 gongli²"));
        }
    }
}
