package com.shijiu.calculator.length;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shijiu.calculator.R;

import java.util.ArrayList;
import java.util.List;

public class LengthActivity extends AppCompatActivity {
    private ImageView back;
    private TextView title;

    private Spinner spinner1;
    private Spinner spinner2;
    private List<String> strings1 = new ArrayList<>();
    private List<String> strings2 = new ArrayList<>();
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

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

    private void initData() {
        strings1.add("毫米");
        strings1.add("厘米");
        strings1.add("分米");
        strings1.add("米");
        strings1.add("千米");

        strings2.add("毫米");
        strings2.add("厘米");
        strings2.add("分米");
        strings2.add("米");
        strings2.add("千米");

        adapter1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,strings1);
        adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,strings2);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner1.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);
        spinner1 = (Spinner) findViewById(R.id.id_spinner1);
        spinner2 = (Spinner) findViewById(R.id.id_spinner2);
    }
}
