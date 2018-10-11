package com.example.wangjunchao.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * 作者： jl
 * 日期： 2017/2/23.
 */

public class T {

    public static Context context;

    public static void show(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void show(Integer text) {
        Toast.makeText(context, String.valueOf(text), Toast.LENGTH_SHORT).show();
    }

    public static void show(Object text) {
        Toast.makeText(context, text.toString(), Toast.LENGTH_SHORT).show();
    }

}
