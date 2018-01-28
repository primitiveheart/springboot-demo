package com.springboot.core.base.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.springboot.core.base.tips.SuccessTip;
import com.springboot.core.base.wrapper.BaseControllerWrapper;
import com.springboot.core.page.PageInfoBT;
import com.springboot.core.support.HttpKit;
import com.springboot.core.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2018/1/16.
 */
public class BaseController {
    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletResponse getHttpServletResponse(){
        return HttpKit.getResponse();
    }

    protected HttpServletRequest getHttpServletRequest(){
        return HttpKit.getRequest();
    }

    protected HttpSession getHttpSession(){
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getHttpSession(boolean flag){
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name){
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value){
         HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokeCount(){
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    /**
     * 把service层的分页信息，封装为bootstrap table的分页
     * @param page
     * @param <T>
     * @return
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page){
        return new PageInfoBT<T>(page);
    }


    /**
     * 包装list，让list有额外属性
     * @param wrapper
     * @return
     */
    protected Object warpObject(BaseControllerWrapper wrapper){
        return wrapper.warp();
    }

    /**
     * 删除cookie
     * @param cookieName
     */
    protected void deleteCookieByName(String cookieName){
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台的文件流
     * @param fileName
     * @param filePath
     * @return
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath){
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes){
        String dFileName = null;
        try{
            dFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        }catch (UnsupportedEncodingException e){

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",dFileName);

        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

}
