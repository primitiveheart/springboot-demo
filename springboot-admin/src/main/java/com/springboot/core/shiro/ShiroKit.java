package com.springboot.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by admin on 2018/1/17.
 */
public class ShiroKit {
    private static final String NAMES_DELIMETER = ",";

    /**
     * 加密的方式
     */
    private static final String hashAlgorithmName = "MD5";

    /**
     * 循环的次数
     */
    private static final int hashIterations = 1024;

    /**
     * 获取封装的ShiroUser
     * @return
     */
    public static ShiroUser getUser(){
        if(isGuset()){
            return null;
        }else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    /**
     * 验证当前用户是否未“访客”，即未认证（包含未记住）的用户，用user搭配使用
     * @return
     */
    public static boolean isGuset(){
        return !isUser();
    }

    /**
     * 认证通过或以记住的用户，与guest搭配使用
     * @return
     */
    public static boolean isUser(){
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 获取当前用户的subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
}
