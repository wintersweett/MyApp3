package com.user.module1;

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
import android.util.Log;

import com.user.baselibrary.IApp;
import com.user.baselibrary.service.ServiceFactory;

import java.io.File;

/**
 * 当为application时 走oncreate 传入的是AppA
 * 当作为library时，走initialized，MainApp被传入进来
 */

public  class AppA extends Application implements IApp {
    private static  Application app;
    public static Application getInstance() {
        return  app;
    }



    @Override

    public  void initialized(Application application) {
        app=  application;
        Log.i("zhm","AppA==");
        ServiceFactory.getInstance().setiServiceModuleA(new LoginService());
    }
//当module1作为一个application时，会走oncreate方法；当作为library时，不走oncreate，走initialized
    @Override
    public void onCreate() {
        super.onCreate();
        initialized(this);

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
