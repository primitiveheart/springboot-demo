package com.springboot.core.constant;

/**
 * Created by admin on 2018/1/17.
 */
public enum IsMenu {

    YES(1, "是"),
    NO(0, "不是");

    int code;
    String messge;

    IsMenu(int code, String messge){
        this.code = code;
        this.messge = messge;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public static String valueOf(Integer status){
        if (status == null){
            return "";
        }else {
            for (IsMenu s : IsMenu.values()){
                if(s.getCode() == status){
                    return s.getMessge();
                }
            }
            return  "";
        }
    }
}
