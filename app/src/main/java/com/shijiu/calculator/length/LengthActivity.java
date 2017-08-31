package com.shijiu.calculator.length;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.PopAdapter;
import com.shijiu.calculator.bean.UnitBean;
import com.shijiu.calculator.utils.MyLayoutManager;
import com.shijiu.calculator.utils.SpaceItemDecoration;
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
    private int unit1 = 1;
    private int unit2 = 1;


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
        initData();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        unit1 = 1;
//
//    }

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
        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(LengthActivity.this, i, beanList);
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
                getResult(unit1, unit2);
                break;
            case R.id.btn_equal:
                input.setText("");
                result.setText("");
                break;

        }

    }

    public void getResult(int i1, int i2) {
        if (input.getText().toString().equals("")) {
            return;
        }

        Log.e(TAG, "getResult: " + input.getText().toString() + "positon1" + beanList1.get(i1).getUnit() + "position2" + beanList2.get(i2).getUnit());

        BigDecimal re = BigDecimal.valueOf(Double.parseDouble(input.getText().toString()));

        switch (i1) {
            //千米 米 分米
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
            //厘米 毫米 微米
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
            //纳米 皮米 海里
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
            //英里 费隆 英寻
            case 9:
                BigDecimal re9 = Cac1(re, "1609.344");
                toMeter(i2, re9);
                break;
            case 10:
                BigDecimal re10 = Cac1(re, "201.168");
                toMeter(i2, re10);
                break;
            case 11:
                BigDecimal re11 = Cac1(re, "1.8288");
                toMeter(i2, re11);
                break;
            //码 英尺 英寸
            case 12:
                BigDecimal re12 = Cac1(re, "0.9144");
                toMeter(i2, re12);
                break;
            case 13:
                BigDecimal re13 = Cac1(re, "0.3048");
                toMeter(i2, re13);
                break;
            case 14:
                BigDecimal re14 = Cac1(re, "0.0254");
                toMeter(i2, re14);
                break;
            //公里 里 丈
            case 15:
                BigDecimal re15 = Cac1(re, "1000");
                toMeter(i2, re15);
                break;
            case 16:
                BigDecimal re16 = Cac1(re, "500");
                toMeter(i2, re16);
                break;
            case 17:
                BigDecimal re17 = Cac1(re, "3.333");
                toMeter(i2, re17);
                break;
            //尺 寸 分
            case 18:
                BigDecimal re18 = Cac1(re, "0.3333");
                toMeter(i2, re18);
                break;
            case 19:
                BigDecimal re19 = Cac1(re, "0.03333");
                toMeter(i2, re19);
                break;
            case 20:
                BigDecimal re20 = Cac1(re, "0.003333");
                toMeter(i2, re20);
                break;
            //厘 毫 秒差距
            case 21:
                BigDecimal re21 = Cac1(re, "0.0003333");
                toMeter(i2, re21);
                break;
            case 22:
                BigDecimal re22 = Cac1(re, "0.0000333");
                toMeter(i2, re22);
                break;
            case 23:
                BigDecimal re23 = Cac1(re, "30856775814671915.808");
                toMeter(i2, re23);
                break;
            //月球距离 天文单位 光年
            case 24:
//                BigDecimal re24 = Cac1(re, "384000000");
                BigDecimal re24 = Cac1(re, "384401000");
                toMeter(i2, re24);
                break;
            case 25:
//                BigDecimal re25 = Cac1(re, "149597870700");
                BigDecimal re25 = Cac1(re, "149597871000");
                toMeter(i2, re25);
                break;
            case 26:
//                BigDecimal re26 = Cac1(re, "9460730472580800");
                BigDecimal re26 = Cac1(re, "9460730470000000");
                toMeter(i2, re26);
                break;
        }

    }

    private void toMeter(int i, BigDecimal d) {
        switch (i) {
            //千米 米 分米
            case 0:
                BigDecimal d0 = Cac2(d, "1000");
                showText(d0);
                break;
            case 1:
                BigDecimal d1 = d;
                showText(d1);
                break;
            case 2:
                BigDecimal d2 = Cac1(d, "10");
                showText(d2);
                break;
            //厘米 毫米 微米
            case 3:
                BigDecimal d3 = Cac1(d, "100");
                showText(d3);
                break;
            case 4:
                BigDecimal d4 = Cac1(d, "1000");
                showText(d4);
                break;
            case 5:
                BigDecimal d5 = Cac1(d, "1000000");
                showText(d5);
                break;
            //纳米 皮米 海里
            case 6:
                BigDecimal d6 = Cac1(d, "1000000000");
                showText(d6);
                break;
            case 7:
                BigDecimal d7 = Cac1(d, "1000000000000");
                showText(d7);
                break;
            case 8:
                BigDecimal d8 = Cac2(d, "1852");
                showText(d8);
                break;
            //英里 费隆 英寻
            case 9:
                BigDecimal d9 = Cac2(d, "1609.344");
                showText(d9);
                break;
            case 10:
                BigDecimal d10 = Cac2(d, "201.168");
                showText(d10);
                break;
            case 11:
                BigDecimal d11 = Cac2(d, "1.8288");
                showText(d11);
                break;
            //码 英尺 英寸
            case 12:
                BigDecimal d12 = Cac2(d, "0.9144");
                showText(d12);
                break;
            case 13:
                BigDecimal d13 = Cac2(d, "0.3048");
                showText(d13);
                break;
            case 14:
                BigDecimal d14 = Cac2(d, "0.0254");
                showText(d14);
                break;
            //公里 里 丈
            case 15:
                BigDecimal d15 = Cac2(d, "1000");
                showText(d15);
                break;
            case 16:
                BigDecimal d16 = Cac2(d, "500");
                showText(d16);
                break;
            case 17:
                BigDecimal d17 = Cac2(d, "3.333");
                showText(d17);
                break;
            //尺 寸 分
            case 18:
                BigDecimal d18 = Cac2(d, "0.3333");
                showText(d18);
                break;
            case 19:
                BigDecimal d19 = Cac2(d, "0.03333");
                showText(d19);
                break;
            case 20:
                BigDecimal d20 = Cac2(d, "0.003333");
                showText(d20);
                break;
            //厘 毫 秒差距
            case 21:
                BigDecimal d21 = Cac2(d, "0.0003333");
                showText(d21);
                break;
            case 22:
                BigDecimal d22 = Cac2(d, "0.0000333");
                showText(d22);
                break;
            case 23:
                BigDecimal d23 = Cac2(d, "30856775814671915.808");
                showText(d23);
                break;
            //月球距离 天文单位 光年
            case 24:
//                BigDecimal d24 = Cac2(d, "384000000");
                BigDecimal d24 = Cac2(d, "384401000");
                showText(d24);
                break;
            case 25:
//                BigDecimal d25 = Cac2(d, "149597870700");
                BigDecimal d25 = Cac2(d, "149597871000");
                showText(d25);
                break;
            case 26:
//                BigDecimal d26 = Cac2(d, "9460730472580800");
                BigDecimal d26 = Cac2(d, "9460730470000000");
                showText(d26);
                break;
        }
    }

    private void showText(BigDecimal bigDecimal) {
        if (bigDecimal.stripTrailingZeros().toPlainString().length() >= 10) {

            result.setText(bigDecimal.toEngineeringString().replace("E","e"));
        } else {
            result.setText(bigDecimal.stripTrailingZeros().toPlainString());
        }
    }


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

        return bigDecimal1.divide(bigDecimal2, 12, BigDecimal.ROUND_HALF_UP);
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
        private MyLayoutManager myLayoutManager;


        public TakePhotoPopWin(Context mContext, final int i, final List<UnitBean> beanList) {
//            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_view);
            view = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);
//            linearLayout.addView(view);

//            if (beanList.size() == 0) {
//                initData();
//            }
            int in = 0;
            Log.e(TAG, "TakePhotoPopWin: " + beanList.toString());
            for (int j = 0; j < beanList.size(); j++) {
                UnitBean bean = beanList.get(j);
                if (bean.getImgae() == null) {
                    in = 0;
//                    continue;
                } else {
                    in = 1;
                    break;
                }
            }
            if (in == 0) {
                beanList.get(1).setImgae(R.mipmap.tick);
            }
            Log.e(TAG, "TakePhotoPopWin: " + beanList.toString());
            btn_cancel = view.findViewById(R.id.id_cancel);
            recylerView = view.findViewById(R.id.id_recycleListView);
            layoutManager = new LinearLayoutManager(mContext);
            recylerView.setLayoutManager(layoutManager);
//            myLayoutManager = new MyLayoutManager(mContext);
//            myLayoutManager.setScrollEnabled(false);
//            recylerView.setLayoutManager(myLayoutManager);
            adapter = new PopAdapter(beanList, mContext);
            recylerView.setAdapter(adapter);
            recylerView.addItemDecoration(new SpaceItemDecoration(10));
            adapter.notifyDataSetChanged();

            Log.e(TAG, "TakePhotoPopWin: " + beanList.toString() + beanList.size());

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
                                adapter.notifyItemChanged(j);
                            }

                        }
//                        adapter.notifyDataSetChanged();
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
                                adapter.notifyItemChanged(j);
                            }
                        }
//                        adapter.notifyDataSetChanged();
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


    }

    private void initData() {
        beanList1.add(new UnitBean("千米 km"));
        beanList1.add(new UnitBean("米 m"));
        beanList1.add(new UnitBean("分米 dm"));
        beanList1.add(new UnitBean("厘米 cm"));
        beanList1.add(new UnitBean("毫米 mm"));
        beanList1.add(new UnitBean("微米 μm"));
        beanList1.add(new UnitBean("纳米 nm"));
        beanList1.add(new UnitBean("皮米 pm"));
        beanList1.add(new UnitBean("海里 nmi"));
        beanList1.add(new UnitBean("英里 mi"));
        beanList1.add(new UnitBean("弗隆 fur"));
        beanList1.add(new UnitBean("英寻 fm"));
        beanList1.add(new UnitBean("码 yd"));
        beanList1.add(new UnitBean("英尺 ft"));
        beanList1.add(new UnitBean("英寸 in"));
        beanList1.add(new UnitBean("公里 gongli"));
        beanList1.add(new UnitBean("里 li"));
        beanList1.add(new UnitBean("丈 zhang"));
        beanList1.add(new UnitBean("尺 chi"));
        beanList1.add(new UnitBean("寸 cun"));
        beanList1.add(new UnitBean("分 fen"));
        beanList1.add(new UnitBean("厘 li"));
        beanList1.add(new UnitBean("毫 hao"));
        beanList1.add(new UnitBean("秒差距 pc"));
        beanList1.add(new UnitBean("月球距离 ld"));
        beanList1.add(new UnitBean("天文单位 AU"));
        beanList1.add(new UnitBean("光年 ly"));

        beanList2.add(new UnitBean("千米 km"));
        beanList2.add(new UnitBean("米 m"));
        beanList2.add(new UnitBean("分米 dm"));
        beanList2.add(new UnitBean("厘米 cm"));
        beanList2.add(new UnitBean("毫米 mm"));
        beanList2.add(new UnitBean("微米 μm"));
        beanList2.add(new UnitBean("纳米 nm"));
        beanList2.add(new UnitBean("皮米 pm"));
        beanList2.add(new UnitBean("海里 nmi"));
        beanList2.add(new UnitBean("英里 mi"));
        beanList2.add(new UnitBean("弗隆 fur"));
        beanList2.add(new UnitBean("英寻 fm"));
        beanList2.add(new UnitBean("码 yd"));
        beanList2.add(new UnitBean("英尺 ft"));
        beanList2.add(new UnitBean("英寸 in"));
        beanList2.add(new UnitBean("公里 gongli"));
        beanList2.add(new UnitBean("里 li"));
        beanList2.add(new UnitBean("丈 zhang"));
        beanList2.add(new UnitBean("尺 chi"));
        beanList2.add(new UnitBean("寸 cun"));
        beanList2.add(new UnitBean("分 fen"));
        beanList2.add(new UnitBean("厘 li"));
        beanList2.add(new UnitBean("毫 hao"));
        beanList2.add(new UnitBean("秒差距 pc"));
        beanList2.add(new UnitBean("月球距离 ld"));
        beanList2.add(new UnitBean("天文单位 AU"));
        beanList2.add(new UnitBean("光年 ly"));
    }
}
