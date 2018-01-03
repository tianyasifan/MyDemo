package com.txt;

import android.util.Log;

import com.txt.mydemo.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by linpu on 17-2-7.
 */
public final class LogUtils {

    private final static String LOGTAG = "LogUtils";

    private final static boolean DEBUG = BuildConfig.DEBUG;

    private static boolean propDebug = false;

    /*应用启动的时候初始化*/
    public static void initPropDebug(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec("getprop " + "com.meizu.intl.news.debug");
                    InputStreamReader ir = new InputStreamReader(process.getInputStream());
                    BufferedReader input = new BufferedReader(ir);
                    propDebug =  Boolean.parseBoolean(input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        Log.v(tag, msg, t);
    }

    public static void v(String tag, String format, Object... args) {
        Log.v(tag, format(format, args));
    }



    public static void d(String tag, String msg) {
        if(DEBUG || propDebug)
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        Log.d(tag, msg, t);
    }


    public static void d(String tag, String format, Object... args) {
        Log.d(tag, format(format, args));
    }



    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        Log.i(tag, msg, t);
    }

    public static void i(String tag, String format, Object... args) {
        Log.i(tag, format(format, args));
    }




    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        Log.w(tag, msg, t);
    }


    public static void w(String tag, String format, Object... args) {
        Log.w(tag, format(format, args));
    }

    /**
     * 自己测试建议使用这种,可以自动打印包名+类名+方法+行数 (注意:发布后还要打印的不要使用这个)
     * @param tag
     * @param msg
     * @param level
     */
    public static void logD(String tag, String msg, int level){
        if(DEBUG){
            Log.d(tag,buildCallerMsg(msg,level));
        }
    }

    /**
     * 自己测试建议使用这种,可以自动打印包名+类名+方法+行数 (注意:发布后还要打印的不要使用这个)
     * @param tag
     * @param msg
     * @param fullStack
     */
    public static void log(String tag, String msg, boolean fullStack){
        if(DEBUG){
            Log.d(tag,buildCallerMsg(msg,fullStack));
        }
    }

    /**
     * 自己测试建议使用这种,可以自动打印包名+类名+方法+行数 (注意:发布后还要打印的不要使用这个)
     * @param msg
     */
    public static void logD(String msg) {
        if (!DEBUG) {
            return;
        }

        Log.d(LOGTAG, buildCallerMsg(msg));
    }

    /**
     * 自己测试建议使用这种,可以自动打印包名+类名+方法+行数 (注意:发布后还要打印的不要使用这个)
     * @param msg
     */
    public static void logD(String msg, Throwable t) {
        if (!DEBUG) {
            return;
        }
        Log.d(LOGTAG, buildCallerMsg(msg), t);
    }



    /**
     * 自己测试建议使用这种,可以自动打印包名+类名+方法+行数 (注意:发布后还要打印的不要使用这个)
     * @param format
     */
    public static void logD(String format , Object... args) {
        if (!DEBUG) {
            return;
        }

        Log.d(LOGTAG, buildCallerMsg(format(format, args)));
    }


    /**
     * format string of args (see {@link String#format(String, Object...)})
     *
     * e.g: format("current init value: %d", value);
     * @param format
     * @param args
     * @return
     */
    private static String format(String format, Object... args) {
        String value = format;
        if(args != null && args.length > 0) {
            try {
                value = String.format(format, args);
            } catch (Exception e) {
                value = format;
            }
        }
        return value;
    }


    /**
     * 注意该函数是耗时操作，在DBUG性能优化相关开发时不要使用logD方式输出日志。
     * @param msg
     * @return
     */
    private static String buildCallerMsg(String msg) {
        return buildCallerMsg(msg,2);
    }

    /**
     * 注意该函数是耗时操作，在DBUG性能优化相关开发时不要使用logD方式输出日志。
     * @param msg
     * @param stackLevel
     * @return
     */
    private static String buildCallerMsg(String msg, int stackLevel){
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[stackLevel];

        return new StringBuilder().append(caller.getClassName()).append(".")
                .append(caller.getMethodName()).append("() [line:").append(caller.getLineNumber())
                .append("] : ").append(msg).toString();
    }
    /**
     * 注意该函数是耗时操作，在DBUG性能优化相关开发时不要使用logD方式输出日志。
     * @param msg
     * @param fullStack
     * @return
     */
    private static String buildCallerMsg(String msg, boolean fullStack){
        if(fullStack){
            StackTraceElement[] callers = new Throwable().fillInStackTrace().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append(msg);
            sb.append("\n");
            for (int i = 0 ;i<callers.length;i++){
                StackTraceElement caller = callers[i];
                sb.append(caller.getClassName()).append(".")
                        .append(caller.getMethodName()).append("() [line:").append(caller.getLineNumber())
                        .append("] : ");
                sb.append("\n");
            }
            return sb.toString();
        }
        else {
            return  buildCallerMsg(msg,2);
        }

    }

    /**
     * 检测是否开启调试模式，debug编译的apk或是在手机上使用adb shell setprop com.meizu.intl.news.debug true开启了调试模式
     * @return
     */
    public static boolean isDebug(){
        return DEBUG || propDebug;
    }

}
