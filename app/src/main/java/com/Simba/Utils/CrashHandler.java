package com.Simba.Utils;
/*
当发生crash时，系统回到，将日志存储sdcard或者上传服务器
 */

import android.content.Context;
import android.os.Environment;
import android.os.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG="crashHandler";
    private static final Boolean DEBUG=true;
    private static final String PATH= Environment.getExternalStorageDirectory().getPath()+"/crash/log";
    private static final String FILE_NAME="crash";
    private static final String FILE_NAME_SUFFIX=".trace";
    private static CrashHandler sInstance=new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private CrashHandler() {

    }
    public static CrashHandler getInstance() {
        return sInstance;
    }
    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
        mContext=context.getApplicationContext();


    }

    //如果系统有未捕获的异常，将自动调用uncaughtException
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {

            //导出异常到sdcard
            dumpToSdcard(e);
            //上传服务器
        } catch (Exception e1) {
            e1.printStackTrace();

        }


        //如果系统有默认的用系统默认的去结束程序，如果没有自己结束
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t,e);
        } else {
            Process.killProcess(Process.myPid());
        }

    }

    private void dumpToSdcard(Throwable e) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        File dir=new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current=System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file=new File(dir+FILE_NAME+time+FILE_NAME_SUFFIX);
        try {
//            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));
//            pw.print(e);
//            pw.print(time);
          //  pw.close();
            RandomAccessFile raf=new RandomAccessFile(file,"rw");
            raf.seek(file.length());
            raf.write(e.getMessage().getBytes(),0,1024);
            raf.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
