package com.user.pluginlib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PluginAcitivty extends AppCompatActivity implements IPlugin {
    int FROM=FROM_INTERNAL;
    private Activity mproxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
       this.mproxyActivity=proxyActivity;
    }

    @Override
    public void onCreate(Bundle bundle) {
        if (bundle !=null) {
            FROM=bundle.getInt("FROM");
        }
        if (FROM==FROM_INTERNAL) {

            super.onCreate(bundle);
            mproxyActivity=this;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (FROM == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        } else {
            mproxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (FROM == FROM_INTERNAL) {
            return super.findViewById(id);
        } else {
           return mproxyActivity.findViewById(id);
        }
    }

    @Override
    public void onStart() {
        if (FROM==FROM_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (FROM==FROM_INTERNAL) {
        super.onResume();}
    }

    @Override
    public void onPause() {
        if (FROM==FROM_INTERNAL) {
        super.onPause();}
    }

    @Override
    public void onStop() {
        if (FROM==FROM_INTERNAL) {
        super.onStop();}
    }

    @Override
    public void onDestry() {
        if (FROM==FROM_INTERNAL) {super.onDestroy();}

    }

    @Override
    public void onRestart() {
        if (FROM==FROM_INTERNAL) {
        super.onRestart();}
    }

    @Override
    public void onActivityResult(int requestData, int resultData, Intent data) {
        if (FROM==FROM_INTERNAL) {
            super.onActivityResult(requestData,resultData,data);
        }

    }

    @Override
    public synchronized ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
        return null;
    }
}
