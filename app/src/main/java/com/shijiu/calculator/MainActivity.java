package com.shijiu.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.shijiu.calculator.appellation.AppellationActivity;
import com.shijiu.calculator.area.AreaActivity;
import com.shijiu.calculator.calculator.CalculatorActivity;
import com.shijiu.calculator.capital.CapitalActivity;
import com.shijiu.calculator.length.LengthActivity;
import com.shijiu.calculator.mortgage.MortgageActivity;
import com.shijiu.calculator.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String, Object>> dataList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    //图片数组
    private int[] imageList = {
            R.mipmap.mortgage, R.mipmap.appellation,
            R.mipmap.length, R.mipmap.capital,
            R.mipmap.calculator, R.mipmap.area
    };

    private String[] nameList = {
            "算房贷", "称谓计算器", "长度转换",
            "大写转换", "计算器", "面积转换"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        String[] dataFrom = {"image","text"};
        int[] dataTo ={R.id.id_grid_image, R.id.id_grid_text};
        simpleAdapter = new SimpleAdapter(this,dataList,R.layout.gird_item,dataFrom, dataTo);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);

    }

    private void initData() {
        for (int i = 0; i < imageList.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageList[i]);
            map.put("text", nameList[i]);
            dataList.add(map);
        }
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.id_gridView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){

            case 0:
                Util.forwardActivity(MainActivity.this, MortgageActivity.class);
                break;
            case 1:
                Util.forwardActivity(MainActivity.this, AppellationActivity.class);
                break;
            case 2:
                Util.forwardActivity(MainActivity.this, LengthActivity.class);
                break;
            case 3:
                Util.forwardActivity(MainActivity.this, CapitalActivity.class);
                break;
            case 4:
                Util.forwardActivity(MainActivity.this, CalculatorActivity.class);
                break;
            case 5:
                Util.forwardActivity(MainActivity.this, AreaActivity.class);
                break;
            
        }
    }
}
