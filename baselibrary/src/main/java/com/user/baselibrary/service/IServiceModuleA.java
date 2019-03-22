package com.user.baselibrary.service;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

public interface IServiceModuleA {
    void lauch(Context context,String claasTo);
    Fragment UserInfoFragment(FragmentManager fragmentManager, int viewId, Bundle bundle);
}
