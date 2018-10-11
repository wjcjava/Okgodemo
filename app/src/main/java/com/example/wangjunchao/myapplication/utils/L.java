package com.example.wangjunchao.myapplication.utils;

import android.util.Log;

/**
 * 日志工具类
 * Created by Administrator on 2017/2/23.
 */

public class L {

    public static String TAG = "StarGo";

    public static boolean isDebug = true;

    private L() {

    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }
}
