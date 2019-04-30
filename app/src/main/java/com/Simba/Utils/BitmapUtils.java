package com.Simba.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {
    /**
     * https://www.jianshu.com/writer#/notebooks/36010235/notes/45920718
     * @param resources
     * @param resId
     * @param reqHeight
     * @param reqWidth
     * @return
     */

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId,int reqHeight, int reqWidth) {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(resources,resId,options);
        options.inSampleSize=calculateInSampleSize(options,reqHeight,reqWidth);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }

    /**
     * 计算采样率inSampleSize
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
        int height=options.outHeight; //原图片尺寸
        int width=options.outWidth;
        int inSampleSize=1;
        if (height>reqHeight|| width>reqWidth) {
            int halfHeight=height/2;
            int halfWidth=width/2;
            while (halfHeight / inSampleSize >= reqHeight && (halfWidth / inSampleSize >= reqWidth)) {
                inSampleSize*=2;
            }

        }

        return inSampleSize ;
    }


}
