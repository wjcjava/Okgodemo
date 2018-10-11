package com.example.wangjunchao.myapplication.api;



/**
 * 作者： jl
 *
 */

public interface HttpCallBack {

    /**
     * 返回成功
     * @param action
     * @param res
     */
    void onSuccess(int action, String res);

    /**
     * 是否显示对话框
     */
    void showLoadingDialog();

    /**
     * 显示错误信息
     * @param s
     */
    void showErrorMessage(String s);

}
