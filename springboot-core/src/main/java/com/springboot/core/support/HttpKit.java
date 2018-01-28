package com.springboot.core.support;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2018/1/16.
 */
public class HttpKit {
    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取HttpServletResponse
     * @return
     */
    public static HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }
}
