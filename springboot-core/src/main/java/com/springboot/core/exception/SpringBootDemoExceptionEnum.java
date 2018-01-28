package com.springboot.core.exception;

/**
 * Created by admin on 2018/1/16.
 */
public enum  SpringBootDemoExceptionEnum implements ExceptionEnum {
    WRITE_ERROR(500, "渲染界面错误"),
    /**
     * 文件上传
    * */
    FILE_READING_ERROR(400, "FILE_READING_ERROR"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND"),
    /**
     * 错误的请求
     * **/
    REQUEST_NULL(400,"请求有错误"),
    SERVER_ERROR(500,"服务器异常");

    private Integer code;

    private String message;

    SpringBootDemoExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
