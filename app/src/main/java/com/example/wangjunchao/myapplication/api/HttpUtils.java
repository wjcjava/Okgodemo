package com.example.wangjunchao.myapplication.api;


import android.app.Activity;



import com.example.wangjunchao.myapplication.utils.L;
import com.example.wangjunchao.myapplication.utils.T;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;


import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 作者： jl
 */

public class HttpUtils {

    public static Activity mactivity;

    public HttpUtils(Activity activity) {
        this.mactivity = activity;
    }

    public static void doGet(final int action, Map<String, String> params, CacheMode cacheMode, final boolean showLoadingDialog, final HttpCallBack httpCallBack) {
        String url = getGetUrl(action, params);
        OkGo.get(url)
                .cacheKey(getUrl(action).toString())
                .tag(getUrl(action).toString())
                .cacheMode(cacheMode)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (showLoadingDialog)
                            httpCallBack.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        try {
                         /*   JSONObject jsonObject = new JSONObject(s);
                            int state = jsonObject.getInt("state");
                            String res = jsonObject.getString("res");
                            //失败
                            if (state != 200) {
                                httpCallBack.showErrorMessage(res);
                                return;
                            }
*/
                            //成功
                            httpCallBack.onSuccess(action, s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (response != null) {
                            int httpCode = response.code();
                            switch (httpCode) {
                                case 400:
                                    L.e("请求的语法出错");
                                    T.show("请求的语法出错");
                                    break;
                                case 401:
                                    L.e("未授权");
                                    T.show("未授权");
                                    break;
                                case 403:
                                    L.e("请求被拒绝");
                                    T.show("请求被拒绝");
                                    break;
                                case 404:
                                    L.e("请求的接口不存在");
                                    T.show("请求的接口不存在");
                                    break;
                                case 408:
                                    L.e("请求超时");
                                    T.show("请求超时");
                                    break;
                                case 500:
                                    L.e("服务器错误");
                                    T.show("服务器错误");
                                    break;
                                case 503:
                                    L.e("服务器停机或正在维护");
                                    T.show("服务器停机或正在维护");
                                    break;
                            }
                            L.e(e.getMessage());
                        }
                    }

                });
    }

    public static void doPost(final int action, HashMap<String, String> params, CacheMode cacheMode, final boolean showLoadingDialog, final HttpCallBack httpCallBack) {
        String url = getUrl(action).toString();

        JSONObject jsonObject = new JSONObject(params);

        OkGo.post(url)
                .cacheKey(String.valueOf(action))
                .tag(String.valueOf(action))
                .cacheMode(cacheMode)
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (showLoadingDialog)
                            httpCallBack.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        httpCallBack.onSuccess(action, s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (response == null)
                            return;
                        int httpCode = response.code();
                        switch (httpCode) {
                            case 400:
                                T.show("请求的语法出错");
                                break;
                            case 401:
                                T.show("未授权");
                                break;
                            case 403:
                                T.show("请求被拒绝");
                                break;
                            case 404:
                                T.show("请求的接口不存在");
                                break;
                            case 408:
                                T.show("请求超时");
                                break;
                            case 500:
                                T.show("服务器错误");
                                break;
                            case 503:
                                T.show("服务器停机或正在维护");
                                break;
                        }
                        L.e(e.getMessage());
                    }

                });
    }

    public static void uploadHeadOrBack(final int action, String filePath, final UploadCallBack uploadCallBack) {
        String url = getUrl(action).toString();
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(filePath));
        OkGo.post(url)
                .cacheKey(String.valueOf(action))
                .isMultipart(true)
                .tag(String.valueOf(action))
                .addFileParams("file", files)
//                .params("file", new File(filePath))
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        L.e("url:" + request.getUrl());
                        L.e("method:" + request.getMethod());
                        L.e("params:" + request.getParams().toString());
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("result:" + s);
                        try {
                           /* JSONObject jsonObject = new JSONObject(s);
                            int state = jsonObject.getInt("state");
                            String res = jsonObject.getString("res");
                            //失败
                            if (state != 200) {
                                uploadCallBack.showErrorMessage(res);
                                return;
                            }*/

                            //成功
                            uploadCallBack.onSuccess(action, s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.upProgress(currentSize, totalSize, progress, networkSpeed);
                        uploadCallBack.onProgress(currentSize, totalSize, progress, networkSpeed);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (response == null)
                            return;
                        int httpCode = response.code();
                        switch (httpCode) {
                            case 400:
                                T.show("请求的语法出错");
                                break;
                            case 401:
                                T.show("未授权");
                                break;
                            case 403:
                                T.show("请求被拒绝");
                                break;
                            case 404:
                                T.show("请求的接口不存在");
                                break;
                            case 408:
                                T.show("请求超时");
                                break;
                            case 500:
                                T.show("服务器错误");
                                break;
                            case 503:
                                T.show("服务器停机或正在维护");
                                break;
                        }
                        L.e(e.getMessage());
                    }

                });
    }

    public interface UploadCallBack {
        void onSuccess(int action, String res);

        void showErrorMessage(String s);

        void onProgress(long currentSize, long totalSize, float progress, long networkSpeed);
    }


    /**
     * 拼接get请求的url地址
     *
     * @param action
     * @param params
     * @return
     */
    public static String getGetUrl(int action, Map<String, String> params) {

        StringBuilder url = getUrl(action);

        if (params == null || params.isEmpty()) {
            return url.toString();
        }

        url.append("?");

        for (String key : params.keySet()) {
            url.append(key);
            url.append("=");
            url.append(params.get(key));
            url.append("&");
        }
        return url.toString();
    }

    public static StringBuilder getUrl(int Action) {
        StringBuilder url = new StringBuilder(UrlConstants.BASE_URL);
        String actionUrl = "";
        switch (Action) {
            //注册
            case ACTION.REGIST:
                actionUrl = UrlConstants.REGIST;
                break;

        }
        url.append(actionUrl);
        return url;
    }

}
