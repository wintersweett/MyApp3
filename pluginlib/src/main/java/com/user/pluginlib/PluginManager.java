package com.user.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    public static PluginManager sInstance = new PluginManager();

    public static PluginManager getsInstance() {
        return sInstance;
    }

    private PluginManager() {

    }
    private Context mContext;
    private PluginApk mPluginApk;

    public PluginApk getmPluginApk() {
        return mPluginApk;
    }

    public void setmPluginApk(PluginApk mPluginApk) {
        this.mPluginApk = mPluginApk;
    }

    public void init(Context context) {
        mContext=context.getApplicationContext();//单利模式的生命周期与应用的生命周期一样长
    }
    public void loadApk(String path) {
        PackageInfo packageInfo=mContext.getPackageManager().getPackageArchiveInfo(path,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if (packageInfo==null) {
            return;
        }
        DexClassLoader dexClassLoader=createDexClassLoader(path);
        AssetManager assetManager=createAssetManager(path);
        Resources mResources=createResource(assetManager);
        mPluginApk=new PluginApk(packageInfo,mResources,dexClassLoader);
        setmPluginApk(mPluginApk);

    }

    private Resources createResource(AssetManager assetManager) {
        Resources resources=mContext.getResources();
        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    private AssetManager createAssetManager(String path) {
        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            Method method=AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager,path);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建访问插件apk的classloader对象
     * @param path
     * @return
     */

    private DexClassLoader createDexClassLoader(String path) {
        File file=mContext.getDir("odex",Context.MODE_PRIVATE);
        return new DexClassLoader(path,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

}
