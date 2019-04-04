package com.user.pluginapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.user.pluginlib.PluginAcitivty;

public class MainActivity extends PluginAcitivty {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
