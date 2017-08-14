package com.shijiu.calculator.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.adapter.PopAdapter;
import com.shijiu.calculator.bean.UnitBean;
import com.shijiu.calculator.length.LengthActivity;

import java.util.ArrayList;
import java.util.List;

public class TakePhotoPopWin extends PopupWindow {


    private View view;
    private List<UnitBean> beanList = new ArrayList<>();
    private PopAdapter adapter;
    private RecyclerView recylerView;
    private TextView btn_cancel;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;



    public TakePhotoPopWin(Context mContext) {
        this.mContext =mContext;
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);
        initData();


        btn_cancel = view.findViewById(R.id.id_cancel);
        recylerView = view.findViewById(R.id.id_recycleListView);
        layoutManager = new LinearLayoutManager(mContext);
        adapter = new PopAdapter(beanList,mContext);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                beanList.get(position).setImgae(R.mipmap.tick);
                adapter.notifyDataSetChanged();
                dismiss();
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
       beanList.add(new UnitBean("千米 km"));
       beanList.add(new UnitBean("米 m"));
       beanList.add(new UnitBean("分米 dm"));
       beanList.add(new UnitBean("厘米 cm"));
       beanList.add(new UnitBean("毫米米 mm"));
       beanList.add(new UnitBean("微米 um"));
       beanList.add(new UnitBean("纳米 nm"));
       beanList.add(new UnitBean("皮米 pm"));
       beanList.add(new UnitBean("海里 nmi"));
    }
}