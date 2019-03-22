package com.Simba.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.think.myapp.R;

import java.lang.reflect.Method;

import com.Simba.Utils.StringUtils;
import com.Simba.Utils.UtilsLog;

public class ReflectActivity extends Activity {
    String name;
    String simpleName;
    public static String [] permisions={Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final int REQUESTCODE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        if (StringUtils.lackPermision(this,permisions)) {

            ActivityCompat.requestPermissions(this,permisions,REQUESTCODE);
        }

        try {
            StringUtils.writeToSdcard("woshishuiwozainali","/MyApp/","test.txt",100);
        } catch (Exception e) {
            UtilsLog.log("zhm",e.getMessage());
            e.printStackTrace();
        }

    }
}
