package com.springboot.core.base.tips;

/**
 * 返回给前台的提示（最终转化为json）
 * Created by admin on 2018/1/16.
 */
public abstract class Tip {
    protected int code;
    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
