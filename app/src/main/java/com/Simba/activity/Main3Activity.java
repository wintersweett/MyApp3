package com.Simba.activity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;
import com.user.baselibrary.service.ServiceFactory;
import com.user.module2.MainActivity;

public class Main3Activity extends Activity {
    Button toA,toB,show;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toA= (Button) findViewById(R.id.toA);
        toB= (Button) findViewById(R.id.toB);
        show= (Button) findViewById(R.id.show);

        container=(FrameLayout)findViewById(R.id.container);
        reisterListener();

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
}
