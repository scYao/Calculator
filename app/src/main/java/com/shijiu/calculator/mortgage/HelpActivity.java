package com.shijiu.calculator.mortgage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;

public class HelpActivity extends AppCompatActivity {
    private TextView title;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        title = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);

        title.setText("帮助");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
