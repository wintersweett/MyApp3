package com.Simba.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.Simba.ui.RadarGradientView;
import com.example.think.myapp.R;

public class AdvancedUiActivity extends AppCompatActivity {
    String to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        if ("跳转到流式".equals(to)) {

            setContentView(R.layout.waterflow_layout);
        } else if ("线性渲染".equals(to)) {//跳转到渲染
            setContentView(R.layout.activity_advanced_ui);
        } else if ("雷达".equals(to)) {
            new RadarGradientView(this);
        }
    }

    private void getData() {
        Intent intent=getIntent();
        to=intent.getStringExtra("to");
    }
}
