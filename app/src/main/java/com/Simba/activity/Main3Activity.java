package com.Simba.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.Simba.Utils.StringUtils;
import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;
import com.user.baselibrary.service.ServiceFactory;
import com.user.module2.MainActivity;
import com.user.pluginlib.PluginManager;
import com.user.pluginlib.ProxyActivity;

public class Main3Activity extends Activity {
    Button toA,toB,show,toApk,loadApk;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        PluginManager.getsInstance().init(this);
        toA= (Button) findViewById(R.id.toA);
        toB= (Button) findViewById(R.id.toB);
        show= (Button) findViewById(R.id.show);
        toApk= (Button) findViewById(R.id.toApk);
        loadApk= (Button) findViewById(R.id.download);

        container=(FrameLayout)findViewById(R.id.container);
        reisterListener();

    }
    public void load() {
        String path= StringUtils.copyAssetsAndWrite(this,"pluginapkA.apk");
        PluginManager.getsInstance().loadApk(path);
    }

    private void reisterListener() {
        toApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this, ProxyActivity.class);
                intent.putExtra("className","com.user.pluginapk.MainActivity");
                startActivity(intent);
            }
        });
        loadApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
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
}
