package com.user.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {
    public PackageInfo mPackageInfo;
    public Resources mResource;
    public DexClassLoader mDexClassLoader;
    public AssetManager assetManager;
    public PluginApk(PackageInfo mPackageInfo,Resources mResource,DexClassLoader mDexClassLoader) {
        this.mPackageInfo=mPackageInfo;
        this.mResource=mResource;
        this.mDexClassLoader=mDexClassLoader;
        assetManager=mResource.getAssets();

    }
}
