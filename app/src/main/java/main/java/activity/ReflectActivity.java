package main.java.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.think.myapp.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Utils.StringUtils;
import Utils.UtilsLog;
import activity.TestBean;

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

        name = TestBean.class.getName();
        UtilsLog.log("zhm",name);
        simpleName=TestBean.class.getSimpleName();
        UtilsLog.log("zhm",simpleName);
        try {
            StringUtils.writeToSdcard("woshishuiwozainali","/MyApp/","test.txt",100);
            Class aClass=Class.forName("activity.TestBean");
            Method method =aClass.getDeclaredMethod("setBean", String.class);
            method.invoke(aClass,"baby");
            aClass.getName();
        } catch (Exception e) {
            UtilsLog.log("zhm",e.getMessage());
            e.printStackTrace();
        }

    }
}
