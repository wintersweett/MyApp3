package com.user.module1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.user.baselibrary.service.IServiceModuleA;

public class LoginService implements IServiceModuleA {


    @Override
    public void lauch(Context context, String targetClass) {
        Intent intent=new Intent(context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    public Fragment UserInfoFragment(FragmentManager fragmentManager, int viewId, Bundle bundle) {
        UserInfoFragment userInfoFragment=new UserInfoFragment();
        userInfoFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(viewId,userInfoFragment).commit();
        return userInfoFragment;
    }
}
