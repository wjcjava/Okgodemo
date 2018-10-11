package com.example.wangjunchao.myapplication.bean;

/**
 * pream：
 * 作者：wangjunchao on 2018/10/11 13:13
 * 邮箱：2931748796@qq.com
 */
public class CityBean {


    /**
     * status : 500
     * message : 接口一个星期前已经提示要停用,请更换新的接口,参照文档链接:https://www.sojson.com/blog/305.html , 有问题请加QQ群:608222884
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
