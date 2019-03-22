package com.Simba.activity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;

import com.Simba.Utils.CrashHandler;
import com.Simba.Utils.UtilsLog;
import com.user.baselibrary.AppConfig;
import com.user.baselibrary.IApp;

import java.io.File;

public  class MainApp extends Application implements IApp  {
    private static MainApp app;
    public static MainApp getInstance() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app= (MainApp) this.getApplicationContext();
        //系统崩溃抓录
//        CrashHandler crashHandler=CrashHandler.getInstance();
//        crashHandler.init(this);
        UtilsLog.log("zhm","oncreate===");
        initialized(this);

    }

    @Override
    public void initialized(Application application) {
        for (String compnent : AppConfig.COMPNENTS) {
            try {
                Class<?> clazz=Class.forName(compnent);
                Object obj=clazz.newInstance();
                if (obj instanceof IApp) {
                    ((IApp) obj).initialized(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public synchronized boolean canLoadUnsafeResources() {
        return false;
    }

    @Override
    public synchronized int checkUriPermission(Uri uri, int pid, int uid, int modeFlags, IBinder callerToken) {
        return 0;
    }

    @Override
    public Context createCredentialProtectedStorageContext() {
        return null;
    }

    @Override
    public synchronized android.view.DisplayAdjustments getDisplayAdjustments(int displayId) {
        return null;
    }

    @Override
    public File getPreloadsFileCache() {
        return null;
    }

    @Override
    public boolean isCredentialProtectedStorage() {
        return false;
    }

    @Override
    public synchronized void reloadSharedPreferences() {

    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission, Bundle options) {

    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, Bundle options) {

    }

    @Override
    public synchronized void sendBroadcastAsUserMultiplePermissions(Intent intent, UserHandle user, String[] receiverPermissions) {

    }

    @Override
    public synchronized void sendBroadcastMultiplePermissions(Intent intent, String[] receiverPermissions) {

    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission, Bundle options, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

    }

    @Override
    public synchronized void sendStickyBroadcastAsUser(Intent intent, UserHandle user, Bundle options) {

    }

    @Override
    public synchronized ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
        return null;
    }

    @Override
    public synchronized boolean stopServiceAsUser(Intent service, UserHandle user) {
        return false;
    }

    @Override
    public synchronized void updateDisplay(int displayId) {

    }
}
