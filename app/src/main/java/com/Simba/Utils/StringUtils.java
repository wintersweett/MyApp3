package com.Simba.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    /**
     * 模拟将assets下的文件 拷贝到app的缓存目录
     * @param
     * @return
     */
    public static String copyAssetsAndWrite(Context context,String fileName) {
        File cacheDir=context.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File outFile=new File(cacheDir,fileName);
        if (!outFile.exists()) {
            try {
                boolean res=outFile.createNewFile();
                if (res) {
                    InputStream in=context.getAssets().open(fileName);
                    FileOutputStream outputStream=new FileOutputStream(outFile);
                    byte[] buffer=new byte[in.available()];
                    int bytecount;
                    while ((bytecount = in.read(buffer)) != -1) {
                        outputStream.write(buffer,0,bytecount);
                    }
                    outputStream.flush();
                    in.close();
                    outputStream.close();
                    Toast.makeText(context,"下载成功",Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context,"文件已存在",Toast.LENGTH_SHORT).show();
            return outFile.getAbsolutePath();

        }
        return null;

    }


//判断权限是否开启，true未开启，false开启了
    public static boolean lackPermision(Context mContext,String[] strings) {
        for (String permission:strings) {
            if (lackOfPermission(mContext, permission)) {
                return true;

            }

        }
        return false;

    }
    public static boolean lackOfPermission(Context mContext,String permission) {
        return ContextCompat.checkSelfPermission(mContext,permission)== PackageManager.PERMISSION_DENIED;

    }

    /**
     * 将文本内容写入SD卡文件中
     * @param data 文本内容
     * @param pathName 文件路径名称（不含SD卡路径） 例： /Soufun_Agent/
     * @param fileName 文件名称（含后缀名）   例：  crash.log
     * @param maxFileLength 文件长度（单位：kb，无长度限制请传 -1）   例：  64
     */
    public static synchronized void writeToSdcard(String data,String pathName,String fileName,int maxFileLength) {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=sf.format(date);
        data="时间-"+str
                +data+"\r\n";

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            UtilsLog.i("zhm","sdcard is not available right now");
            return;
        }
        String sdCardPath=Environment.getExternalStorageDirectory().getPath();
        String filePath=sdCardPath+"/"+pathName;
        File path=new File(filePath);
        File file=new File(filePath+fileName);
        UtilsLog.i("zhm","path==="+path);
        UtilsLog.i("zhm","file==="+file);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (file!=null && file.exists() && maxFileLength>0){
            if (file.length()+data.length() >maxFileLength *1024) {
                file.delete();
            }
        }
        try {
        if (!file.exists()) {
                file.createNewFile();
            UtilsLog.i("zhm","createFile=="+file);

        }
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        randomAccessFile.seek(file.length());
        randomAccessFile.write(data.getBytes());
        UtilsLog.i("zhm","getbytes==="+data.getBytes());
        randomAccessFile.close();

        } catch (IOException e) {
            UtilsLog.log("zhm",e.getMessage());
            e.printStackTrace();
        }



    }



}
