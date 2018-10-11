package com.example.wangjunchao.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wangjunchao.myapplication.api.ACTION;
import com.example.wangjunchao.myapplication.api.HttpCallBack;
import com.example.wangjunchao.myapplication.api.HttpUtils;
import com.example.wangjunchao.myapplication.bean.CityBean;
import com.example.wangjunchao.myapplication.utils.GsonUtil;
import com.example.wangjunchao.myapplication.utils.T;
import com.lzy.okgo.cache.CacheMode;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements HttpCallBack {

    private CityBean cityBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDate();
            }
        });

    }
    private void initDate() {
        HashMap<String, String> params = new HashMap<>();
        params.put("city","%E5%8C%97%E4%BA%AC");
        HttpUtils.doPost(ACTION.REGIST, params, CacheMode.REQUEST_FAILED_READ_CACHE, true, this);
    }

    @Override
    public void onSuccess(int action, String res) {
        cityBean = GsonUtil.toObj(res, CityBean.class);
            if(cityBean.getStatus()==500){
                T.show("请求成功"+cityBean.getMessage());
            }
    }
    @Override
    public void showLoadingDialog() {

    }
    @Override
    public void showErrorMessage(String s) {
        T.show("请求失败");
    }
}
