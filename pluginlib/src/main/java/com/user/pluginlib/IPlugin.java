package com.user.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IPlugin {
    int FROM_INTERNAL=0;
    int FROM_EXTERNAL=1;
    /**
     * 赋予插件apk上下文
     */
    void attach(Activity proxyActivity);
    void onCreate(Bundle bundle);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestry();
    void onRestart();
    void onActivityResult(int ruquestData, int resultData, Intent data);
}
