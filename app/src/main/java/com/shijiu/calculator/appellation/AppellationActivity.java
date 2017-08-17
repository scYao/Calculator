package com.shijiu.calculator.appellation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;

public class AppellationActivity extends Activity {
    private WebView webView;
    private ImageView back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appellation);

        back = findViewById(R.id.id_back);
        title = findViewById(R.id.id_title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText("称谓计算器");
        webView = findViewById(R.id.id_webView);
        webView.loadUrl("file:///android_asset/Appellation/index.html");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
