package com.Simba.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.Simba.Utils.UtilsLog;
import com.Simba.adapter.AdvancedUIAdapter;
import com.Simba.entity.AdvanceduiEntity;
import com.example.think.myapp.R;
import com.user.baselibrary.service.ServiceFactory;
import com.user.module2.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends Activity implements AdvancedUIAdapter.ItemClick{
    Button toA,toB,show;
    FrameLayout container;
    ListView lv;
    List<AdvanceduiEntity> list;
    AdvancedUIAdapter uiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
        reisterListener();

    }

    private void initData() {
        String [] str={"跳转到流式","线性渲染","雷达","水波纹","填满心","滤镜","滤镜2"};
        //要跳转到的自定义view
        list=new ArrayList<>();
        for (int i=0;i<str.length;i++) {
            AdvanceduiEntity entity=new AdvanceduiEntity();
            entity.to=str[i];
            list.add(entity);
        }

        UtilsLog.log("zhm","listsize=="+list.size());
        uiAdapter=new AdvancedUIAdapter(this,list);
        lv.setAdapter(uiAdapter);



    }

    private void initView() {
        lv=(ListView)findViewById(R.id.lv);
        toA= (Button) findViewById(R.id.toA);
        toB= (Button) findViewById(R.id.toB);
        show= (Button) findViewById(R.id.show);

        container=(FrameLayout)findViewById(R.id.container);

    }

    private void reisterListener() {
        toB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceFactory.getInstance().getiServiceModuleB().lauch(Main3Activity.this,1);


            }
        });
        toA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsLog.i("zhm","toA===");
                ServiceFactory.getInstance().getiServiceModuleA().lauch(Main3Activity.this, "");
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            Bundle bundle=new Bundle();
            @Override
            public void onClick(View v) {
                UtilsLog.i("zhm","container onclick===");
                ServiceFactory.getInstance().getiServiceModuleA().UserInfoFragment(getFragmentManager(),R.id.container,bundle);
            }
        });
    }

    @Override
    public synchronized ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
        return null;
    }
    Intent intent=new Intent();


    @Override
    public void itemClick(AdvanceduiEntity entity) {
        intent.setClass(this,AdvancedUiActivity.class);
        intent.putExtra("to",entity.to);
        startActivity(intent);
    }
}
