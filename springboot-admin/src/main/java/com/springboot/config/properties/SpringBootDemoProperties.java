package com.springboot.config.properties;

import com.springboot.core.util.ToolUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

/**
 * springbootdemo项目的配置
 * Created by admin on 2018/1/17.
 */
@Component
@ConfigurationProperties(prefix = SpringBootDemoProperties.PREFIX)
public class SpringBootDemoProperties {
    public final static String PREFIX = "springbootdemo";
    private Boolean kaptchaOpen = false;
    private Boolean swaggerOpen = false;
    private String fileUploadPath;
    private Boolean haveCreatePath = false;
    private Boolean springSessionOpen  = false;
    //session失效时间(默认是30，单位：秒)
    private Integer sessionInvalidateTime = 30 * 60;
    //session验证失效是时间(默认是15，单位：秒)
    private Integer sessionValidateInterval = 15 * 60;


    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Boolean getSwaggerOpen() {
        return swaggerOpen;
    }

    public void setSwaggerOpen(Boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }

    public String getFileUploadPath() {
        //如何没有写文件上传路径，则保存到临时目录
        if(ToolUtil.isEmpty(fileUploadPath)){
            return ToolUtil.getTempPath();
        }else {
            //判断有没有结尾符，没有得加上
            if(!fileUploadPath.equals(File.separator)){
                fileUploadPath = fileUploadPath + File.separator;
            }
            //判断目录是否存在，不存在得加上
            if(haveCreatePath == false){
                File file = new File(fileUploadPath);
                file.mkdirs();
                haveCreatePath = true;
            }

            return fileUploadPath;
        }
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public Boolean getHaveCreatePath() {
        return haveCreatePath;
    }

    public void setHaveCreatePath(Boolean haveCreatePath) {
        this.haveCreatePath = haveCreatePath;
    }

    public Boolean getSpringSessionOpen() {
        return springSessionOpen;
    }

    public void setSpringSessionOpen(Boolean springSessionOpen) {
        this.springSessionOpen = springSessionOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidateInterval() {
        return sessionValidateInterval;
    }

    public void setSessionValidateInterval(Integer sessionValidateInterval) {
        this.sessionValidateInterval = sessionValidateInterval;
    }
}
