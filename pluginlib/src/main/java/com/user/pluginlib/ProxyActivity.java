package com.user.pluginlib;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.textclassifier.TextClassifier;

public class ProxyActivity extends AppCompatActivity {
    private String mClassName;//插件里的activity的类名
    private PluginApk mPluginApk;
    IPlugin mIplugin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName=getIntent().getStringExtra("className");
        Log.i("zhm","mClassName=="+mClassName);
        mPluginApk=PluginManager.getsInstance().getmPluginApk();
        lauchActivity();
    }

    private void lauchActivity() {
        if (mPluginApk==null) {
            throw new RuntimeException("请加载插件apk");
        }
        //实例化acivity
        try {
            Class clazz=mPluginApk.mDexClassLoader.loadClass(mClassName);
            Object obj=clazz.newInstance();
            if (obj instanceof IPlugin) {
                mIplugin= (IPlugin) obj;
                //赋予插件apk上下文
                mIplugin.attach(this);
                Bundle bundle=new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                mIplugin.onCreate(bundle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk!=null?mPluginApk.mResource:super.getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk!=null?mPluginApk.mDexClassLoader:super.getClassLoader();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk!=null?mPluginApk.assetManager: super.getAssets();
    }
}
