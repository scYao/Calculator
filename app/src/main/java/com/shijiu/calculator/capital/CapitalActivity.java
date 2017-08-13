package com.shijiu.calculator.capital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.utils.Tool;

import java.util.ArrayList;
import java.util.List;

public class CapitalActivity extends AppCompatActivity {
    private ImageView back;
    private TextView title;
    private EditText number;
    private TextView capital;
    private static final String TAG = "CapitalActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);

        initView();
        title.setText("大写转换");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initListener();
    }

    private void initListener() {
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                double d = Double.parseDouble(number.getText().toString().trim());
                Log.e(TAG, "onTextChanged: "+d );

                capital.setText(Tool.change(d));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.id_back);
        title = (TextView) findViewById(R.id.id_title);

        number = (EditText) findViewById(R.id.id_number);
        capital = (TextView) findViewById(R.id.id_capital);

    }
}
