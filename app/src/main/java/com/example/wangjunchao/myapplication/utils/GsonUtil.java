package com.example.wangjunchao.myapplication.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * gson--json转换工具类
 * 作者： jl
 * 日期： 2017/2/23.
 */

public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 对象转换成json字符串
     *
     * @param object
     * @return
     */
    public static String toStr(Object object) {
        return gson.toJson(object);
    }

    /**
     * json转换成 obj
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T toObj(String jsonString, Class<T> cls) {

        T t = gson.fromJson(jsonString, cls);
        return t;
    }

   /**
     * json转换成list
     *
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String jsonString, Class<T[]> type) {
        T[] list = gson.fromJson(jsonString,type);
        return Arrays.asList(list);
    }

    /**
     * json转换成map
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> toMap(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     * json转换成List里面包含map的情况
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> toListMap(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }

    /**
     * json转换成 map里面包含list的情况
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> toMapList(String jsonString, Class<T> cls) {
        return gson.fromJson(jsonString, new TypeToken<Map<String, List<T>>>() {
        }.getType());
    }

}
