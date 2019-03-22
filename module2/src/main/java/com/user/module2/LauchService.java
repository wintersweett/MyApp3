package com.user.module2;

import android.content.Context;
import android.content.Intent;

import com.user.baselibrary.service.IServiceModuleB;

public class LauchService implements IServiceModuleB {
    @Override
    public void lauch(Context context, int userId) {
        Intent intent=new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
