package com.example.wangjunchao.myapplication.app;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDexApplication;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.example.wangjunchao.myapplication.R;
import com.example.wangjunchao.myapplication.utils.L;
import com.example.wangjunchao.myapplication.utils.T;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;


/**
 * Created by Administrator on 2017/11/22.
 *
 * jl
 */

public class App extends MultiDexApplication {
    private static final String TAG = App.class.getName();
    Context context;

    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    private Handler handler;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        T.context = getApplicationContext();
        HttpHeaders headers = new HttpHeaders();
        HttpParams params = new HttpParams();
        OkGo.init(this);
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    .debug(L.TAG, Level.INFO, true)
                    .setConnectTimeout(15 * 1000)  //全局的连接超时时间
                    .setReadTimeOut(15 * 1000)     //全局的读取超时时间
                    .setWriteTimeOut(15 * 1000)    //全局的写入超时时间
                    .setCacheMode(CacheMode.NO_CACHE)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    .setRetryCount(3)
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效
                    .setCertificates()                                  //方法一：信任所有证书,不安全有风险
                    .addCommonHeaders(headers)  //设置全局公共头
                    .getOkHttpClientBuilder().authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic("acme", "acmesecret");
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}