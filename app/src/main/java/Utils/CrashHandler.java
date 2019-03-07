package Utils;
/*
当发生crash时，系统回到，将日志存储sdcard或者上传服务器
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}
