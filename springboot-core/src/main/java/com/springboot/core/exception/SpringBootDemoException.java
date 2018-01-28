package com.springboot.core.exception;

/**
 * Created by admin on 2018/1/16.
 */
public class SpringBootDemoException extends RuntimeException {
    private Integer code;
    private String message;

    public SpringBootDemoException(SpringBootDemoExceptionEnum springBootDemoExceptionEnum){
        this.code = springBootDemoExceptionEnum.getCode();
        this.message = springBootDemoExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
